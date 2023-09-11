package com.example.lifecycleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        Button btnBrowser = findViewById(R.id.button);

        btnBrowser.setOnClickListener(view-> {
            MainActivity.this.startActivity(intent);
        });

        Log.i("LifeCycle", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LifeCycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LifeCycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LifeCycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LifeCycle", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LifeCycle", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("LifeCycle", "onRestart");
    }
}
