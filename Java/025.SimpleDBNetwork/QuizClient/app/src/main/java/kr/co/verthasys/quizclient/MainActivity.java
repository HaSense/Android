package kr.co.verthasys.quizclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextView questionText;
    private TextView resultText;
    private TextInputEditText usernameInput;
    private RadioGroup answerGroup;
    private RadioButton optionA;
    private RadioButton optionB;
    private RadioButton optionC;
    private RadioButton optionD;
    private MaterialButton refreshButton;
    private MaterialButton submitButton;
    private ProgressBar progressBar;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler mainHandler;
    private QuizQuestion currentQuestion;
    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainHandler = new Handler(Looper.getMainLooper());
        baseUrl = getString(R.string.base_url);

        bindViews();
        setupListeners();
        fetchQuestion();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdownNow();
    }

    private void bindViews() {
        questionText = findViewById(R.id.questionText);
        resultText = findViewById(R.id.resultText);
        usernameInput = findViewById(R.id.usernameInput);
        answerGroup = findViewById(R.id.answerGroup);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        refreshButton = findViewById(R.id.refreshButton);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progress);
        resultText.setText(R.string.result_placeholder);

        optionA.setTag("A");
        optionB.setTag("B");
        optionC.setTag("C");
        optionD.setTag("D");
    }

    private void setupListeners() {
        refreshButton.setOnClickListener(v -> fetchQuestion());
        submitButton.setOnClickListener(v -> submitAnswer());
    }

    private void fetchQuestion() {
        setLoading(true);
        resultText.setText(R.string.result_placeholder);
        answerGroup.clearCheck();

        executor.execute(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(baseUrl + "/api/questions");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                String response = readResponse(connection);
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new IOException("Server error: " + connection.getResponseCode());
                }

                JSONArray data = new JSONArray(response);
                if (data.length() == 0) {
                    throw new IOException("No questions registered");
                }

                int index = new Random().nextInt(data.length());
                JSONObject rawQuestion = data.getJSONObject(index);
                QuizQuestion question = QuizQuestion.fromJson(rawQuestion);

                mainHandler.post(() -> showQuestion(question));
            } catch (IOException | JSONException e) {
                mainHandler.post(() -> showError(e.getMessage()));
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        });
    }

    private void submitAnswer() {
        if (currentQuestion == null) {
            showToast(getString(R.string.message_no_question));
            return;
        }

        String username = Objects.requireNonNull(usernameInput.getText()).toString().trim();
        if (TextUtils.isEmpty(username)) {
            showToast(getString(R.string.message_missing_username));
            return;
        }

        int selectedId = answerGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            showToast(getString(R.string.message_select_answer));
            return;
        }

        View selected = findViewById(selectedId);
        Object tag = selected.getTag();
        if (!(tag instanceof String)) {
            showToast(getString(R.string.message_select_answer));
            return;
        }

        String answer = ((String) tag).toUpperCase(Locale.US);
        setLoading(true);

        executor.execute(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(baseUrl + "/api/submissions");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");

                JSONObject payload = new JSONObject();
                payload.put("username", username);
                payload.put("question_id", currentQuestion.id);
                payload.put("answer", answer);

                try (OutputStream os = connection.getOutputStream()) {
                    os.write(payload.toString().getBytes(StandardCharsets.UTF_8));
                }

                String response = readResponse(connection);
                if (connection.getResponseCode() >= 400) {
                    throw new IOException("Submit failed: " + response);
                }

                SubmissionResult result = SubmissionResult.fromJson(new JSONObject(response));
                mainHandler.post(() -> showResult(result));
            } catch (IOException | JSONException e) {
                mainHandler.post(() -> showError(e.getMessage()));
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        });
    }

    private void showQuestion(QuizQuestion question) {
        setLoading(false);
        currentQuestion = question;
        questionText.setText(question.text);
        optionA.setText(formatOption("A", question.optionA));
        optionB.setText(formatOption("B", question.optionB));
        optionC.setText(formatOption("C", question.optionC));
        optionD.setText(formatOption("D", question.optionD));
    }

    private void showResult(SubmissionResult result) {
        setLoading(false);
        if (result.correct) {
            resultText.setText(getString(R.string.result_correct, getString(R.string.app_name)));
        } else {
            resultText.setText(getString(R.string.result_wrong, result.correctAnswer));
        }
    }

    private void showError(String error) {
        setLoading(false);
        resultText.setText(getString(R.string.error_template, error));
    }

    private void setLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        submitButton.setEnabled(!loading);
        refreshButton.setEnabled(!loading);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private static String formatOption(String tag, String value) {
        return tag + ". " + value;
    }

    private static String readResponse(HttpURLConnection connection) throws IOException {
        InputStream stream;
        if (connection.getResponseCode() >= 400) {
            stream = connection.getErrorStream();
        } else {
            stream = connection.getInputStream();
        }
        if (stream == null) {
            return "";
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        }
    }

    private static class QuizQuestion {
        final int id;
        final String text;
        final String optionA;
        final String optionB;
        final String optionC;
        final String optionD;

        QuizQuestion(int id, String text, String optionA, String optionB, String optionC, String optionD) {
            this.id = id;
            this.text = text;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
        }

        static QuizQuestion fromJson(JSONObject jsonObject) throws JSONException {
            return new QuizQuestion(
                    jsonObject.getInt("id"),
                    jsonObject.getString("text"),
                    jsonObject.getString("option_a"),
                    jsonObject.getString("option_b"),
                    jsonObject.getString("option_c"),
                    jsonObject.getString("option_d")
            );
        }
    }

    private static class SubmissionResult {
        final boolean correct;
        final String correctAnswer;

        SubmissionResult(boolean correct, String correctAnswer) {
            this.correct = correct;
            this.correctAnswer = correctAnswer;
        }

        static SubmissionResult fromJson(JSONObject jsonObject) throws JSONException {
            return new SubmissionResult(
                    jsonObject.getBoolean("correct"),
                    jsonObject.getString("correct_answer")
            );
        }
    }
}
