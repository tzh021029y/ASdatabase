package com.example.asdatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

public class MyContentProvider extends ContentProvider {
    private MyDataBase2 MyDataBase2;
    public MyContentProvider() {

    }
    @Override
    public String getType(Uri uri) {
        return "1";
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        getContext().getContentResolver().insert(uri,values);
        return MyDataBase2.Daoinsert(values);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public boolean onCreate() {
        MyDataBase2=new MyDataBase2(this.getContext());
        return false;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        return MyDataBase2.query(projection,selection,selectionArgs,sortOrder);
    }
}