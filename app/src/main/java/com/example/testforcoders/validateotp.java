package com.example.testforcoders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class validateotp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validateotp);
        String emailid= getIntent().getStringExtra("emailid");
        String eid=emailid;

    }
}