package com.example.testforcoders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 1;
    public static final String COLUMN_DATE = "COLUMN_DATE";
    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_TIME = "COLUMN_TIME";
    public TaskDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "description TEXT," +
                "COLUMN_DATE TEXT," +
                "COLUMN_TIME TEXT"+
                ")";
        db.execSQL(createTableSql);
    }
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 2) {
            // Add the new column to the existing table using ALTER TABLE
//            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_DATE + "TEXT");
//            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_TIME + "TEXT");
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
//        db.execSQL("DROP TABLE IF EXISTS tasks");
//        onCreate(db);
    }

    public void addTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", task.getName());
        values.put("description", task.getDescription());
        values.put("COLUMN_DATE", task.getdate());
        values.put("COLUMN_TIME", task.getTime());
        db.insert("tasks", null, values);
        db.close();
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tasks", null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("COLUMN_DATE"));
                String time = cursor.getString(cursor.getColumnIndexOrThrow("COLUMN_TIME"));
                taskList.add(new Task(name, description,date,time ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return taskList;
    }

}


