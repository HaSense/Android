package com.example.customadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Person> items = new ArrayList<Person>();
    private CustomAdapter adapter;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person md;
        md = new Person(R.drawable.ic_launcher_foreground, "홍길동");
        items.add(md);
        md = new Person(R.drawable.ic_launcher_foreground, "이순신");
        items.add(md);
        md = new Person(R.drawable.ic_launcher_foreground, "강감찬");
        items.add(md);

        list = findViewById(R.id.list);
        adapter = new CustomAdapter(getApplicationContext(), R.layout.custom, items);
        list.setAdapter(adapter);

    }
}
