package com.example.multidatatestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecondAtivity extends AppCompatActivity {

    private Button btnReturn;
    private TextView txtV1, txtV2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Second 액티비티");

        Intent intent = getIntent();
        final int result = intent.getIntExtra("V1", 0) + intent.getIntExtra("V2", 0);

        btnReturn = findViewById(R.id.btnReturn);
        txtV1 = findViewById(R.id.txtV1);
        txtV2 = findViewById(R.id.txtV2);

        txtV1.setText(intent.getIntExtra("V1", 0)+"");
        txtV2.setText(intent.getIntExtra("V2", 0)+"");

        btnReturn.setOnClickListener(view -> {
            Intent retIntent = new Intent(getApplicationContext(), MainActivity.class);
            retIntent.putExtra("result", result);

            setResult(RESULT_OK, retIntent);
            finish();
        });
    }
}
