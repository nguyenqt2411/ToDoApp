package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText task_input,time_input,note_input;
    Button update_button;
    String id,task,time,note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        task_input=findViewById(R.id.task_input2);
        time_input=findViewById(R.id.time_input2);
        note_input=findViewById(R.id.note_input2);
        update_button=findViewById(R.id.updatebutton);

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDb=new MyDatabaseHelper(UpdateActivity.this);
                task=task_input.getText().toString().trim();
                time=time_input.getText().toString().trim();
                note=note_input.getText().toString().trim();
                myDb.updateData(id,task,time,note);

            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")&&getIntent().hasExtra("task")&&getIntent().hasExtra("time")&& getIntent().hasExtra("note"))
        {
            id= getIntent().getStringExtra("id");
            task= getIntent().getStringExtra("task");
            time= getIntent().getStringExtra("time");
            note= getIntent().getStringExtra("note");

            task_input.setText(task);
            time_input.setText(time);
            note_input.setText(note);
        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
}