package com.example.sharedpreferencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.sharedpreferencetest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        view.setPadding(20, 20, 20, 20);
        setContentView(view);

        //Mode가 0이면 읽고 쓰기가 다 가능
        //MODE_WORLD_READABLE 읽기 공유
        //MODE_WORLD_WRITERBLE 쓰기 공유
        //첫번째 문자열은 파일명인데 생략시 액티비티 이름으로 만들어짐
        SharedPreferences spref = getSharedPreferences("SPREF", 0);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences spref = getSharedPreferences("SPREF", 0);
        SharedPreferences.Editor editor = spref.edit();

        String name = binding.edtName.getText().toString();
        int age = Integer.parseInt(binding.edtAge.getText().toString());

        editor.putString("Name", name);
        editor.putInt("Age", age);

        editor.commit();
    }
}
