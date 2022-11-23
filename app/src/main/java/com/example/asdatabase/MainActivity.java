package com.example.asdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Uri.parse("content://com.example.asdatabase.MyContentProvider/student");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.
                    getColumnIndex("name"));
            @SuppressLint("Range") int age = cursor.getInt(cursor.
                    getColumnIndex("age"));
            @SuppressLint("Range") int id = cursor.getInt(cursor.
                    getColumnIndex("id"));
            Log.d("db", "id=" + id + "|name=" + name + "|age=" + age);

        }



    }
}