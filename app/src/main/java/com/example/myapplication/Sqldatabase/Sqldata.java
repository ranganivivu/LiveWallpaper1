package com.example.myapplication.Sqldatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class Sqldata extends AppCompatActivity {
    EditText name,email;
    Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqldata);
    }
    public void oo(View view) {
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        sub=findViewById(R.id.save);

        String sname=name.getText().toString();
        String semail=email.getText().toString();
        Sqldataclass sqldata=new Sqldataclass(this);

        SQLiteDatabase data=sqldata.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",sname);
        contentValues.put("email",semail);
        data.insert("Student",null,contentValues);
        Toast.makeText(this, "suc", Toast.LENGTH_SHORT).show();
    }
}
