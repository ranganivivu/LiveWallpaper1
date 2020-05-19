package com.example.myapplication;

import android.app.Application;

import com.appizona.yehiahd.fastsave.FastSave;

public class Deno extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FastSave.init(getApplicationContext());
    }
}
