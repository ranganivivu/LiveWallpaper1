package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.appizona.yehiahd.fastsave.FastSave;

public class Splascscreen extends AppCompatActivity {
    ImageView imageView;
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splascscreen);

        imageView=findViewById(R.id.img);

        final String demo=  FastSave.getInstance().getString("lemail","");
        Log.d("s",demo);


        Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.down);
        imageView.startAnimation(slide_down);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent=new Intent(Splascscreen.this, MainActivity.class);
                startActivity(intent);
              /*  if (demo==""){
                    Intent intent=new Intent(Splascscreen.this, Main2Activity.class);
                    startActivity(intent);
                }else {
                    Intent mainIntent = new Intent(Splascscreen.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }*/
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
