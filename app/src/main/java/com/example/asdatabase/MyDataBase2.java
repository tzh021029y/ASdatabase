package com.example.asdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
public class MyDataBase2 {
    private  Context context;
    private  SQLiteDatabase database;
    public MyDataBase2(Context context){
        this.context=context;
        Mysh2 dbhelper=new Mysh2(context,"db",null,1);
        database=dbhelper.getWritableDatabase();
        Log.d("qwe","getWritableDatabase");
    }
    public  Uri Daoinsert(ContentValues contentValues){
        Log.d("bbb", "Daoinsert: aaa");
        long rowid=database.insert("student",null,contentValues);
        Uri uri=Uri.parse("content://com.example.asdatabase.MyContentProvider/student");
        Uri inserturi= ContentUris.withAppendedId(uri,rowid);
        context.getContentResolver().notifyChange(inserturi,null);
        return inserturi;
    }
    public Cursor query(String[] whichone, String selection, String[] selectionArgs, String sortOrder) {
        Log.d("qwe","ok");
        Cursor cursor1 = database.query("student",whichone,selection,selectionArgs,
                null,null,sortOrder);

        return cursor1;
    }


}