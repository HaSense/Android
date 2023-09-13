package com.example.newactivitytest;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNewActivity = findViewById(R.id.btnNewActivity);
        btnNewActivity.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            startActivity(intent);
        });

        RadioGroup rGroup1 = findViewById(R.id.rGroup1);
        RadioButton rdoSecond = findViewById(R.id.rdoSecond);
        RadioButton rdoThird = findViewById(R.id.rdoThird);

        rGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                int index = rGroup1.indexOfChild(findViewById(checkedId));
                switch (index)
                {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getApplicationContext(), ThirdActivity.class);
                        startActivity(intent2);
                        break;

                }
            }
        });

    }
}
