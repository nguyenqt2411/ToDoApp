package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText task_input,time_input,note_input;
    Button savebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        task_input=findViewById(R.id.task_input);
        time_input=findViewById(R.id.time_input);
        note_input=findViewById(R.id.note_input);
        savebutton=findViewById(R.id.savebutton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDb=  new MyDatabaseHelper(AddActivity.this);
                myDb.addTask(task_input.getText().toString().trim(),
                        time_input.getText().toString().trim(),
                        note_input.getText().toString().trim());
            }
        });

    }


}