package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ViewAct extends AppCompatActivity {
    ImageView imageView;
    Button b1,b2;
    Toolbar toolbar;
    private Target target=new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
            try {
                // wallpaperManager.setResource(+imageURL);
                wallpaperManager.setBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        b1=findViewById(R.id.btn);
        imageView=findViewById(R.id.mg);
        toolbar=findViewById(R.id.vtool);
        b2=findViewById(R.id.btncancel);
        getSupportActionBar();
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewAct.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Intent i = this.getIntent();
        String name = i.getExtras().getString("NAME_KEY");
        String description = i.getExtras().getString("DESCRIPTION_KEY");
        final String imageURL = i.getExtras().getString("IMAGE_KEY");

        //SET RECEIVED DATA TO TEXTVIEWS AND IMAGEVIEWS
      //  nameDetailTextView.setText(name);
      //  descriptionDetailTextView.setText(description);
     //   dateDetailTextView.setText("DATE: " + getDateToday());
     //   categoryDetailTextView.setText("CATEGORY: " + getRandomCategory());

            Picasso.with(this)
                .load(imageURL)
                .placeholder(R.drawable.common_full_open_on_phone)
                .fit()
                .centerCrop()
                .into(imageView);

            b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewAct.this, "Image set success", Toast.LENGTH_SHORT).show();
                Picasso.with(ViewAct.this)
                        .load(imageURL)
                        .into(target);
            }
        });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(ViewAct.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
    }
}
