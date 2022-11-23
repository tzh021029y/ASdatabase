package com.example.asdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class databaseActivity extends AppCompatActivity {
    private MyDataBase2 MyDataBase2;
    private MySqliteHelper sqlLiteDBHelper;
    private Button button1, button2, button3, button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ContentValues contentValues= new ContentValues();
        contentValues.put("age",10);
        contentValues.put("name","otto");
        Log.d("ccc", "onCreate: "+contentValues);
//        MyDataBase2.Daoinsert(contentValues);
        button1 = findViewById(R.id.button_insert);
        button2 = findViewById(R.id.button_update);
        button3 = findViewById(R.id.button_delete);
        button4 = findViewById(R.id.button_query);
        Log.d("tzh", "DatabaseActivity onCreate");
        sqlLiteDBHelper = new MySqliteHelper(this, "J1syanDB", null, 1);
        SQLiteDatabase database = sqlLiteDBHelper.getWritableDatabase();
//insert
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tzh", "insert");
                ContentValues values1 = new ContentValues();
                values1.put("name", "tom");
                values1.put("age", 21);
                ContentValues values2 = new ContentValues();
                values2.put("name", "otto");
                values2.put("age", 20);
                ContentValues values3 = new ContentValues();
                values3.put("name", "amy");
                values3.put("age", 19);
                database.insert("student", null, values1);
                database.insert("student", null, values2);
                database.insert("student", null, values3);
            }
        });
// update
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values1 = new ContentValues();
                values1.put("age", 81);
                database.update("student", values1, "name=?", new
                        String[]{"otto"});
            }
        });
// delete
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.delete("student", "name=?", new
                        String[]{"amy"});
            }
        });
// query
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor1 = database.query("student", new
                                String[]{"name"},
                        "name=?", new String[]{"otto"},
                        null, null, null);
                Log.d("tzh", "cursor1.getCount:" + cursor1.getCount());
                Cursor cursor2 = database.rawQuery("select * from student where name=? or name=?", new String[]{"otto", "tom"});
                Cursor cursor3 = database.rawQuery("select * from student where name=? and age=?", new String[]{"amy", "20"});
                Log.d("tzh", "cursor2.getCount:" + cursor2.getCount());
                Log.d("tzh", "cursor2.getPosition:" + cursor2.getPosition());
                while (cursor2.moveToNext()) {
                    @SuppressLint("Range") Integer id =
                            cursor2.getInt(cursor2.getColumnIndex("id"));
                    @SuppressLint("Range") String name =
                            cursor2.getString(cursor2.getColumnIndex("name"));
                    Log.d("xzl", "res:" + id + "-" + name);
                }
                cursor2.close();
            }
        });
    }

}


