package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appizona.yehiahd.fastsave.FastSave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {
    EditText name,email,pass,uname;
    Button sub;
    RadioGroup radioGroup;
    RadioButton male,femal;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    String gender="";
    TextView reg,log;
    CardView logcard,regcard;
    ImageView logimg,regimg;

    //login
    EditText lemail,lpass;
    Button  lsub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        uname = findViewById(R.id.uname);
        male = findViewById(R.id.male);
        femal = findViewById(R.id.fmale);


        sub = findViewById(R.id.sub);
        lsub = findViewById(R.id.lsub);

        reg = findViewById(R.id.regg);
        log = findViewById(R.id.logg);

        lemail = findViewById(R.id.lemail);
        lpass = findViewById(R.id.lpass);

        logimg =findViewById(R.id.logimg);
        regimg=findViewById(R.id.regimg);

        logcard =findViewById(R.id.logcardView);
        regcard=findViewById(R.id.regcardView);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logimg.setVisibility(View.VISIBLE);
                logcard.setVisibility(View.VISIBLE);
                lsub.setVisibility(View.VISIBLE);

                regimg.setVisibility(View.INVISIBLE);
                regcard.setVisibility(View.INVISIBLE);
                sub.setVisibility(View.INVISIBLE);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regimg.setVisibility(View.VISIBLE);
                regcard.setVisibility(View.VISIBLE);
                sub.setVisibility(View.VISIBLE);

                logimg.setVisibility(View.INVISIBLE);
                logcard.setVisibility(View.INVISIBLE);
                lsub.setVisibility(View.INVISIBLE);
            }
        });

        lsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String semail = lemail.getText().toString();
                final String password = lpass.getText().toString();

                FastSave.getInstance().saveString("lemail",semail);

                if (TextUtils.isEmpty(semail)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(semail,password)
                        .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    if (password.length() < 6) {
                                        pass.setError("minimum 6");
                                    } else {
                                        Toast.makeText(Main2Activity.this, "fail", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(Main2Activity.this, "suc", Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                //Login();
            }
        });
        //  databaseReference = FirebaseDatabase.getInstance().getReference().child("sd");
        firebaseAuth = FirebaseAuth.getInstance();
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String sname = name.getText().toString();
                final String suname = uname.getText().toString();
                final String semail = email.getText().toString();
                final String spass = pass.getText().toString();

                FastSave.getInstance().saveString("remail",semail);
                FastSave.getInstance().saveString("rname",sname);

                if (male.isChecked()) {
                    gender = "male";
                }
                if (femal.isChecked()) {
                    gender = "female";
                }
                if (sname.isEmpty()) {
                    name.setError("enter name");
                }
                if (suname.isEmpty()) {
                    uname.setError("enter uname");

                }  if (semail.isEmpty()) {
                    email.setError("enter email");
                }
                if (spass.isEmpty()) {
                    pass.setError("enter pass");
                }
                firebaseAuth.createUserWithEmailAndPassword(semail, spass).
                        addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Student info = new Student(sname, suname, semail, gender);
                                    String user_id = firebaseAuth.getCurrentUser().getUid();
                                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("User").child("Reg").child(user_id);
                                    current_user_db.setValue(info);
                                    Toast.makeText(Main2Activity.this, "User Registration Successfully", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Main2Activity.this, "user aleredy register", Toast.LENGTH_LONG).show();
                                }
                                //  progressDialog.dismiss();
                            }
                        });
            }
        });
    }
}
