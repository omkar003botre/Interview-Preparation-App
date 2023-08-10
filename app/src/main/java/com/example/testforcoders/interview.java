package com.example.testforcoders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class interview extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        //setContentView(R.layout.activity_interview);


        Button cinterbtn1=findViewById(R.id.cinterbtn);
        cinterbtn1.setOnClickListener(V -> {
            Intent itc=new Intent(interview.this,cinterview.class);
            startActivity(itc);
        });
        Button cppinterbtn=findViewById(R.id.cppinterbtn);
        cppinterbtn.setOnClickListener(v->{
            Intent itx=new Intent(getApplicationContext(),cppquestioninter.class);
            startActivity(itx);
        });
       Button javainterbtn=findViewById(R.id.javainterbtn);
       javainterbtn.setOnClickListener(v->{
           Intent javait=new Intent(getApplicationContext(),javainterview.class);
           startActivity(javait);
       });
      Button pythoninterbtn=findViewById(R.id.pythoninterbtn);
      pythoninterbtn.setOnClickListener(v->{
          Intent javait=new Intent(getApplicationContext(),pythoninterview.class);
          startActivity(javait);
      });

      Button jsinterbtn=findViewById(R.id.jsbtn);
      jsinterbtn.setOnClickListener(v->{
          Intent jsit=new Intent(getApplicationContext(),jsinterview.class);
          startActivity(jsit);
      });
      Button dbmsbtn=findViewById(R.id.dbmsinterbtn);
      dbmsbtn.setOnClickListener(v->{
          Intent dbit=new Intent(getApplicationContext(),dbmsinterview.class);
          startActivity(dbit);
      });
      Button dsbtn=findViewById(R.id.dsinterbtn);
      dsbtn.setOnClickListener(v->{
          Intent dsit=new Intent(getApplicationContext(),dsinterview.class);
          startActivity(dsit);
      });
      Button phpbtn=findViewById(R.id.phpinterbtn);
      phpbtn.setOnClickListener(v->{
          Intent phpit=new Intent(getApplicationContext(),phpinterview.class);
          startActivity(phpit);
      });
    }
}