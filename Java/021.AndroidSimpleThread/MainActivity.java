package com.example.androidsimplethread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int frontValue=0, backValue=0;
    private TextView txtFront, txtBack;
    private Button btnIncrese;
    private Handler txtBackHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFront = findViewById(R.id.txtFront);
        txtBack = findViewById(R.id.txtBack);
        btnIncrese = findViewById(R.id.btnIncrese);

        btnIncrese.setOnClickListener(view -> {
            frontValue++;
            txtFront.setText("FrontValue : " + frontValue);
            //txtBack.setText("BackValue : " + backValue);
        });

        //API 30(Android 11)부터 Handler 생성자를 통한 기본생성자 사용이 deprecate 되었음.
        txtBackHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    txtBack.setText("BackValue : " + backValue);
                }
            }
        };

        BackThread bthead = new BackThread();
        bthead.setDaemon(true);
        bthead.start();
    }

    class BackThread extends Thread {
        @Override
        public void run() {
            super.run();
            while(true){
                backValue++;
                //txtBack.setText("BackValue : " + backValue);
                txtBackHandler.sendEmptyMessage(0);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }//end of run
    }
}
