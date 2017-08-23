package com.example.dell.w;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 *
 */

public class LoginActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        imageView=(ImageView)findViewById(R.id.loginimage);
        textView=(TextView)findViewById(R.id.logintext);
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String text = intent.getStringExtra("text");
        ImageLoader.getInstance().displayImage(image,imageView);
        textView.setText(text);
    }
}
