package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appizona.yehiahd.fastsave.FastSave;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    TextView t12,hname,hemail;
    ImageView imageView,teacherDetailImageView;
    LinearLayout lin;

    NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.v1);
        tabLayout=findViewById(R.id.tab);
        t12=findViewById(R.id.t12);
        imageView=findViewById(R.id.im);
        lin=findViewById(R.id.lin);


        final NavigationView navigationView = findViewById(R.id.nv);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.reg:
                        Intent intent=new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_dashboard:
                        Intent inten=new Intent(MainActivity.this, MainActivity.class);
                        startActivity(inten);
                        break;
                    case R.id.log:
                        FastSave.getInstance().clearSession();
                        Intent intent2=new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.setVisibility(View.VISIBLE);
            }
        });

        View headerview = navigationView.getHeaderView(0);
        ImageView profilename = (ImageView) headerview.findViewById(R.id.teacherDetailImageView);

        TextView nmae = (TextView) headerview.findViewById(R.id.hname);
        TextView email = (TextView) headerview.findViewById(R.id.hemail);

        final String emailr=  FastSave.getInstance().getString("remail","");
        final String namer=  FastSave.getInstance().getString("rname","");

        nmae.setText(namer);
        email.setText(emailr);

        if (emailr.isEmpty()){
            final String lmailr=  FastSave.getInstance().getString("lemail","");
            email.setText(emailr);
            Log.d("d",lmailr);
        }else {

        }

        profilename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.setVisibility(View.GONE);
            }
        });

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
