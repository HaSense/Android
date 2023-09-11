//사용 시 Java는 클래스이름과 파일이름이 같아야 합니다. MainActivity로 수정후 사용하세요.

package com.example.simplecalculaotr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etV1, etV2;
    private TextView txtResult;
    private Button btnAdd, btnMinus, btnMultiple, btnDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setButtonListeners();
    }

    private void initializeViews() {
        etV1 = findViewById(R.id.etV1);
        etV2 = findViewById(R.id.etV2);
        txtResult = findViewById(R.id.txtResult);
        btnAdd = findViewById(R.id.btnAdd);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiple = findViewById(R.id.btnMultiple);
        btnDivide = findViewById(R.id.btnDivide);
    }

    private void setButtonListeners() {
        btnAdd.setOnClickListener(view -> performOperation(Operation.ADD));
        btnMinus.setOnClickListener(view -> performOperation(Operation.MINUS));
        btnMultiple.setOnClickListener(view -> performOperation(Operation.MULTIPLY));
        btnDivide.setOnClickListener(view -> performOperation(Operation.DIVIDE));
    }

    private void performOperation(Operation operation) {
        try {
            int v1 = Integer.parseInt(etV1.getText().toString());
            int v2 = Integer.parseInt(etV2.getText().toString());

            int result;
            switch (operation) {
                case ADD:
                    result = v1 + v2;
                    break;
                case MINUS:
                    result = v1 - v2;
                    break;
                case MULTIPLY:
                    result = v1 * v2;
                    break;
                case DIVIDE:
                    if (v2 == 0) {
                        txtResult.setText("에러: 0으로 나누면 안되요.");
                        return;
                    }
                    result = v1 / v2;
                    break;
                default:
                    throw new IllegalArgumentException("지원하지 않는 연산입니다.");
            }
            txtResult.setText("계산결과 : " + Integer.toString(result));
        } catch (NumberFormatException e) {
            txtResult.setText("에러: 지원하지 않는 포맷입니다.");
        }
    }

    private enum Operation {
        ADD, MINUS, MULTIPLY, DIVIDE
    }
}
