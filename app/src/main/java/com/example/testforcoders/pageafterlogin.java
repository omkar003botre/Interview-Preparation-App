package com.example.testforcoders;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pageafterlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageafterlogin);
        TextView t=findViewById(R.id.unameid1);
        Button taketest;
        Intent intent=getIntent();
        String uname=intent.getStringExtra("uid");
        t.setText(uname);

        //calling interview page
        Button interviewbtn=findViewById(R.id.interview);
        interviewbtn.setOnClickListener(view -> {
            Intent ith=new Intent(pageafterlogin.this,interview.class);
            startActivity(ith);
        });
        Button prep=findViewById(R.id.prep);
        prep.setOnClickListener(v->{
        Intent jk=new Intent(getApplicationContext(),Startpreparation.class);
        startActivity(jk);


        });
        Button todobtn=findViewById(R.id.todo);
        todobtn.setOnClickListener(v->{
            Intent td=new Intent(getApplicationContext(),interviewprep.class);
            startActivity(td);

        });

    }
}