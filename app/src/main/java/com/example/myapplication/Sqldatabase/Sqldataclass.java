package com.example.myapplication.Sqldatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Sqldataclass extends SQLiteOpenHelper {
    public static final String Dbname="vivu";
    public static final int Dbversion=1;

    public Sqldataclass(@Nullable Context context) {
        super(context,Dbname,null,Dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String datacet= "Create TABLE Student(name VERCHER,email VERCHER)";
        sqLiteDatabase.execSQL(datacet);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
