package com.example.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="ToDo.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="my_todo";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_TASK="todo_task";
    private static final String COLUMN_TIME="todo_time";
    private static final String COLUMN_NOTE="todo_note";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query= "CREATE TABLE "+TABLE_NAME+
                        " ("+COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TASK+" TEXT, "+
                        COLUMN_TIME+" TEXT, "+
                        COLUMN_NOTE+" TEXT);";
        db.execSQL(Query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        onCreate(db);

    }
    void addTask(String task, String time, String note)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_TASK,task);
        cv.put(COLUMN_TIME,time);
        cv.put(COLUMN_NOTE,note);

        long result=db.insert(TABLE_NAME,null,cv);
        if(result==-1)
        {
            Toast.makeText(context, "Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully",Toast.LENGTH_SHORT).show();
        }

    }
    Cursor readALldata()
    {
        String query="SELECT *FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null)
        {
            cursor =db.rawQuery(query,null);
        }
        return cursor;
    }
    void updateData(String row_id,String task, String time, String note){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TASK, task);
        cv.put(COLUMN_TIME,time);
        cv.put(COLUMN_NOTE,note);
        long result=db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
        if(result==-1)
        {
            Toast.makeText(context, "Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Updated Successfully",Toast.LENGTH_SHORT).show();
        }
    }
}
