package com.example.listadddel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> Items;
    private ArrayAdapter<String> Adapter;
    private ListView listView;
    private Button btnAdd, btnDel;
    private EditText etText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Items= new ArrayList<String>();
        Items.add("첫번째");
        Items.add("두번째");
        Items.add("세번째");

        Adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                Items);
        listView = findViewById(R.id.lv01);
        listView.setAdapter(Adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String msg;
                msg = "선택된 Item : " + Items.get(position);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);

        btnAdd.setOnClickListener(view -> {
            etText = findViewById(R.id.etText);
            String text = etText.getText().toString();
            if(text.length() !=0){
                etText.setText("");
                Items.add(text);
                Adapter.notifyDataSetChanged();
            }
        });
        btnDel.setOnClickListener(view->{
            int id = listView.getCheckedItemPosition();
            if(id != ListView.INVALID_POSITION){
                Items.remove(id);
                Adapter.notifyDataSetChanged();
            }
        });
    }
}





