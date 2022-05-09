package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullImageActivity extends AppCompatActivity {
    private ImageView iv_full_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        iv_full_image=findViewById(R.id.iv_full_image);
        String image=getIntent().getStringExtra("image");
        Glide.with(this).load(image).into(iv_full_image);
    }
}