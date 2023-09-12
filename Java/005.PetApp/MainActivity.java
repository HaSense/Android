package com.example.petapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView text1, text2;
    private CheckBox chkAgree;
    private RadioGroup rGroup1;
    private RadioButton rdoDog, rdoCat, rdoRabbit;
    //private RadioButton radioArray[] = new RadioButton[3];
    private Button btnOk;
    private ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("애완동물 사진 보기");

        text1 = findViewById(R.id.Text1);
        chkAgree = findViewById(R.id.ChkAgree);

        text2 = findViewById(R.id.Text2);
        rGroup1 = findViewById(R.id.Rgroup1);
        rdoDog = findViewById(R.id.RdoDog);
        rdoCat = findViewById(R.id.RdoCat);
        rdoRabbit = findViewById(R.id.RdoRabbit);

        btnOk = findViewById(R.id.BtnOk);
        imgPet = findViewById(R.id.ImgPet);

        //체크박스 이벤트처리
        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 체크되면 모두 보이도록 설정
                if (chkAgree.isChecked() == true) {
                    text2.setVisibility(android.view.View.VISIBLE);
                    rGroup1.setVisibility(android.view.View.VISIBLE);
                    imgPet.setVisibility(android.view.View.VISIBLE);
                    btnOk.setVisibility(android.view.View.VISIBLE);
                    imgPet.setVisibility(android.view.View.VISIBLE);
                } else {
                    text2.setVisibility(android.view.View.INVISIBLE);
                    rGroup1.setVisibility(android.view.View.INVISIBLE);
                    imgPet.setVisibility(android.view.View.INVISIBLE);
                    btnOk.setVisibility(android.view.View.INVISIBLE);
                    imgPet.setVisibility(android.view.View.INVISIBLE);
                }
            }
        });

        // 라디오버튼을 클릭할 때, 이미지 뷰를 변경시킴 ==> 배열로 처리함.
//        final int draw[] = {R.drawable.dog,
//                R.drawable.cat, R.drawable.rabbit};
//
//        for (int i = 0; i < radioArray.length; i++) {
//            final int index; // 주의! 꼭 필요함.
//            index = i;
//            radioArray[index].setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    imgPet.setImageResource(draw[index]);
//                }
//            });

            //버트이벤트 처리
            btnOk.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public void onClick(View view) {
                    int checkedId = rGroup1.getCheckedRadioButtonId();
                    if (checkedId == R.id.RdoDog) {
                        imgPet.setImageResource(R.drawable.dog);
                    } else if (checkedId == R.id.RdoCat) {
                        imgPet.setImageResource(R.drawable.cat);
                    } else if (checkedId == R.id.RdoRabbit) {
                        imgPet.setImageResource(R.drawable.rabbit);
                    } else {
                        Toast.makeText(getApplicationContext(), "동물을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

}
