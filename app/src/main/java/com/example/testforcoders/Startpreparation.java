package com.example.testforcoders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Startpreparation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpreparation);

        Button ctbn=findViewById(R.id.cbtn);
        ctbn.setOnClickListener(v->{
            Intent ko=new Intent(getApplicationContext(),record.class);
            ko.putExtra("course","c");
            startActivity(ko);
        });
        Button cppbtn=findViewById(R.id.cppinterbtn);
        cppbtn.setOnClickListener(v->{
            Intent ko=new Intent(getApplicationContext(),record.class);
            ko.putExtra("course","cpp");
            startActivity(ko);
        });
        Button javabtn=findViewById(R.id.javainterbtn);
        javabtn.setOnClickListener(v->{
            Intent ko=new Intent(getApplicationContext(),record.class);
            ko.putExtra("course","java");
            startActivity(ko);
        });
        Button pythonbtn=findViewById(R.id.pythoninterbtn);
        pythonbtn.setOnClickListener(v->{
            Intent ko=new Intent(getApplicationContext(),record.class);
            ko.putExtra("course","python");
            startActivity(ko);
        });
        Button jsbtn=findViewById(R.id.jsbtn);
        jsbtn.setOnClickListener(v->{
            Intent ko=new Intent(getApplicationContext(),record.class);
            ko.putExtra("course","js");
            startActivity(ko);
        });
        Button dbms=findViewById(R.id.dbmsinterbtn);
        dbms.setOnClickListener(v->{
            Intent ko=new Intent(getApplicationContext(),record.class);
            ko.putExtra("course","dbms");
            startActivity(ko);
        });

     Button ds=findViewById(R.id.dsinterbtn);
     ds.setOnClickListener(v->{
         Intent ko=new Intent(getApplicationContext(),record.class);
         ko.putExtra("course","ds");
         startActivity(ko);
     });
   Button php=findViewById(R.id.phpinterbtn);
   php.setOnClickListener(v->{
       Intent ko=new Intent(getApplicationContext(),record.class);
       ko.putExtra("course","php");
       startActivity(ko);
   });
    }

}