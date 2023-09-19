package com.example.sdcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSIONS = 101;
    private EditText edtText;
    private Button btnSave, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText = findViewById(R.id.edtText);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);

        // 퍼미션 체크
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSIONS);
        }

        btnSave.setOnClickListener(v -> saveToSDCard(edtText.getText().toString()));

        btnRead.setOnClickListener(v -> edtText.setText(readFromSDCard()));
    }

    private void saveToSDCard(String data) {
        try {
            // 앱 전용 디렉토리에 "MyFiles" 폴더 생성
            File directory = new File(getExternalFilesDir(null) + "/MyFiles");
            directory.mkdirs();

            File file = new File(directory, "textfile.txt");
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.write(data);
            osw.close();

            Toast.makeText(getApplicationContext(), "파일이 성공적으로 저장되었습니다.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFromSDCard() {
        StringBuilder data = new StringBuilder();
        try {
            // 앱 전용 디렉토리에서 "MyFiles" 폴더 접근
            File directory = new File(getExternalFilesDir(null) + "/MyFiles");
            File file = new File(directory, "textfile.txt");
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fIn);

            char[] inputBuffer = new char[100];
            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                data.append(readString);
            }
            isr.close();
            Toast.makeText(this, "성공적으로 읽어 왔습니다.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "퍼미션 허용됨", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "퍼미션 거부됨", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
