package com.example.dialogbasicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] fruits = new String[] { "사과", "딸기", "배"};
        Button btnDialogOpen = findViewById(R.id.btnDialogOpen);
        btnDialogOpen.setOnClickListener(view -> {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("제목입니다.");
            //dlg.setMessage("이곳이 내용입니다."); //메시지가 활성화 되면 dlg.setItems는 가려집니다.
            dlg.setIcon(R.mipmap.ic_launcher);
            dlg.setItems(fruits, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    btnDialogOpen.setText(fruits[i]);
                }
            });
            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(), "확인을 눌렀습니다",
                            Toast.LENGTH_SHORT).show();
                }
            });
            dlg.show();
        });
    }
}
