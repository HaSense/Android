package com.example.workthread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Button btnUpload;
    private Handler uploadHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUpload = findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(view -> {
            AlertDialog builder = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("질문")
                    .setMessage("업로드 하시겠습니까?")
                    .setPositiveButton("예", (dialogInterface, i) -> {
                       //doUpload(); //업로드가 완료되기 까지 UI 멈춤이 일어난다.

                        //UI멈춤은 해결한것 처럼 보이나 메인스레드가 모두 일을 한다.
                        //mHandler.sendEmptyMessageDelayed(0, 10);
                        
                        //가장 간단히 실행
                        //new Thread(()->{
                       //    uploadHandler.sendEmptyMessage(0);
                       //}).start();
                        
                        // WorkThread를 사용하여 실행
                        Thread workThread = new Thread(new WorkThread());
                        workThread.start();

                    })
                    .setNegativeButton("아니오", null)
                    .show();
        });

        uploadHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what == 0){
                    doUpload();
                }
            }
        };

    }
    private void doUpload(){
        for (int i = 0; i < 30; i++) {
            try { Thread.sleep(100); }
            catch (InterruptedException e) {;}
        }
        Toast.makeText(getApplicationContext(),
                "업로드를 완료했습니다.",
                Toast.LENGTH_SHORT).show();
    }

    class WorkThread implements Runnable {
        @Override
        public void run() {
            uploadHandler.sendEmptyMessage(0);
        }
    }
}
