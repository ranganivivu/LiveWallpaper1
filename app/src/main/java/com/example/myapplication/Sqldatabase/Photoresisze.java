package com.example.myapplication.Sqldatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.File;

public class Photoresisze extends AppCompatActivity {
    ImageView imageView;
    Button buttonCamera, buttonGallery ;
    File file;
    Uri uri;
    Intent CamIntent, GalIntent, CropIntent ;
    public  static final int RequestPermissionCode  = 1;
    DisplayMetrics displayMetrics ;
    int width, height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoresisze);
        imageView = (ImageView)findViewById(R.id.imageview);
        buttonCamera = (Button)findViewById(R.id.button2);
        buttonGallery = (Button)findViewById(R.id.button1);
      //  EnableRuntimePermission();
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickImageFromCamera() ;
            }
        });
        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetImageFromGallery();
            }
        });
    }
    public void ClickImageFromCamera() {
        CamIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory(),
                "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        uri = Uri.fromFile(file);
        CamIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
        CamIntent.putExtra("return-data", true);
        startActivityForResult(CamIntent, 0);
    }
    public void GetImageFromGallery(){
        GalIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            ImageCropFunction();
        }
        else if (requestCode == 2) {
            if (data != null) {
                uri = data.getData();
                ImageCropFunction();
            }
        }
        else if (requestCode == 1) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = bundle.getParcelable("data");
                imageView.setImageBitmap(bitmap);
            }
        }
    }
    public void ImageCropFunction() {
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");
            CropIntent.setDataAndType(uri, "image/*");
            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 180);
            CropIntent.putExtra("outputY", 180);
            CropIntent.putExtra("aspectX", 3);
            CropIntent.putExtra("aspectY", 4);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);
            startActivityForResult(CropIntent, 1);
        } catch (ActivityNotFoundException e) {
        }
    }
    //Image Crop Code End Here
    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(Photoresisze.this,
                Manifest.permission.CAMERA))
        {
            Toast.makeText(Photoresisze.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Photoresisze.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }
    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {
        switch (RC) {
            case RequestPermissionCode:
                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Photoresisze.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Photoresisze.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
