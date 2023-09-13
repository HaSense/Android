package com.example.multidatatestapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPlus;
    private EditText etV1, etV2;

    private final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    int plus = result.getData().getIntExtra("result", 0);
                    Toast.makeText(getApplicationContext(), "합계 : " + plus, Toast.LENGTH_SHORT).show();
                }
            });
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlus = findViewById(R.id.btnPlus);
        etV1 = findViewById(R.id.etV1);
        etV2 = findViewById(R.id.etV2);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondAtivity.class);
                intent.putExtra("V1", Integer.parseInt(etV1.getText().toString()));
                intent.putExtra("V2", Integer.parseInt(etV2.getText().toString()));
                //startActivityForResult(intent, 0);
                resultLauncher.launch(intent);
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(resultCode == RESULT_OK) {
//            int plus = data.getIntExtra("result", 0);
//            Toast.makeText(getApplicationContext(), "합계 : " + plus, Toast.LENGTH_SHORT).show();
//        }
//
//    }
}









