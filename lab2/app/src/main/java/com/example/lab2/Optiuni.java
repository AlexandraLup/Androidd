package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnClickListener;

public class Optiuni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optiuni);

        ImageView image = (ImageView) findViewById(R.id.call);
        ImageView fileUpload = (ImageView) findViewById(R.id.fileUpload);

        image.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                Intent intent1 = new Intent(Intent.ACTION_SEND, Uri.fromParts("sms", "0741621236", null));
                intent1.setType("text/plain");
                startActivity(intent1);
            }

        });

        fileUpload.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                Intent intent2 = new Intent(Optiuni.this, FileUploadActivity.class);
                startActivity(intent2);
            }

        });
    }
}
