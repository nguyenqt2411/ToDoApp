package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper myDb;
    ArrayList<String> todo_id,todo_task,todo_time,todo_note;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycleView);
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);

            }
        });
        myDb=new MyDatabaseHelper(MainActivity.this);
        todo_id=new ArrayList<>();
        todo_task=new ArrayList<>();
        todo_time=new ArrayList<>();
        todo_note=new ArrayList<>();

        storeDataInArray();
        customAdapter=new CustomAdapter(MainActivity.this,todo_id,todo_task,todo_time,todo_note);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }
    void storeDataInArray(){
        Cursor cursor=myDb.readALldata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()) {
                todo_id.add(cursor.getString(0));
                todo_task.add(cursor.getString(1));
                todo_time.add(cursor.getString(2));
                todo_note.add(cursor.getString(3));
            }
        }
    }
}