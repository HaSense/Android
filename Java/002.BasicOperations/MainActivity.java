package com.example.simplecalculaotr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etV1, etV2;
    private TextView txtResult;
    private Button btnAdd, btnMultiple, btnDivide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //API26부터(8.0 Oreo) 반환타입이 제너릭으로 변환됨 따라서 casting이 필요없어짐
        etV1 = findViewById(R.id.etV1);
        etV2 = findViewById(R.id.etV2);
        txtResult = findViewById(R.id.txtResult);
        btnAdd = findViewById(R.id.btnAdd);
        btnMultiple = findViewById(R.id.btnMultiple);
        btnDivide = findViewById(R.id.btnDivide);

        setButtonListeners();

    }
    private void setButtonListeners() {
        btnAdd.setOnClickListener(view -> {
            int v1 = Integer.parseInt(etV1.getText().toString());
            int v2 = Integer.parseInt(etV2.getText().toString());
            int result = v1 + v2;
            //txtResult.setText(result) --> 에러는 아니지만 안드로이드에서 문자열이 올 곳에 정수가 오면 버그로 런타임 에러가 발생한다. Integer.toString(result)등으로 해결가능
            txtResult.setText("계산결과 : " + result);
        });

        btnMultiple.setOnClickListener(view -> {
            int v1 = Integer.parseInt(etV1.getText().toString());
            int v2 = Integer.parseInt(etV2.getText().toString());
            int result = v1 * v2;
            txtResult.setText("계산결과 : " + result);
        });

        btnDivide.setOnClickListener(view -> {
            int v1 = Integer.parseInt(etV1.getText().toString());
            int v2 = Integer.parseInt(etV2.getText().toString());
            if (v2 != 0) { // 0으로 나누는 것을 방지
                int result = v1 / v2;
                txtResult.setText("계산결과 : " + result);
            } else {
                txtResult.setText("에러: 0으로 나누면 안되요.");
            }
        });
    }
}
