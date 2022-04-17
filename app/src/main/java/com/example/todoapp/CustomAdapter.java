package com.example.todoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList todo_id,todo_task,todo_time,todo_note;

    CustomAdapter(Activity activity,Context context,ArrayList todo_id,ArrayList todo_task,ArrayList todo_time,ArrayList todo_note){
        this.activity=activity;
        this.context=context;
        this.todo_id=todo_id;
        this.todo_task=todo_task;
        this.todo_time=todo_time;
        this.todo_note=todo_note;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        holder.todo_id_txt.setText(String.valueOf(todo_id.get(position)));
        holder.todo_task_txt.setText(String.valueOf(todo_task.get(position)));
        holder.todo_time_txt.setText(String.valueOf(todo_time.get(position)));
        holder.todo_note_txt.setText(String.valueOf(todo_note.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(todo_id.get(position)));
                intent.putExtra("task",String.valueOf(todo_task.get(position)));
                intent.putExtra("time",String.valueOf(todo_time.get(position)));
                intent.putExtra("note",String.valueOf(todo_note.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return todo_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView todo_id_txt,todo_task_txt,todo_time_txt,todo_note_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            todo_id_txt=itemView.findViewById(R.id.todo_id_txt);
            todo_task_txt=itemView.findViewById(R.id.todo_task_txt);
            todo_time_txt=itemView.findViewById(R.id.todo_time_txt);
            todo_note_txt=itemView.findViewById(R.id.todo_note_txt);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
