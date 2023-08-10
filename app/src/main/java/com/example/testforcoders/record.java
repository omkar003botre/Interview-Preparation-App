package com.example.testforcoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;


public class record extends AppCompatActivity {
    int nextq=0;
    String ans;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    TextView scoretx;
    private ImageView iv_mic;
    private TextView tv_Speech_to_text,score,paragraph2EditText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Intent ko=getIntent();
        String cname=ko.getStringExtra("course");
        TextView listofq = (TextView) findViewById(R.id.listofq);
        Button next=(Button) findViewById(R.id.nextq);

        Button prev=(Button)findViewById(R.id.previousbtn);
        progressBar=findViewById(R.id.progressBar);
        scoretx=findViewById(R.id.score);
    //listofq.setText(cname);
        iv_mic = findViewById(R.id.iv_mic);
        tv_Speech_to_text = findViewById(R.id.textans);
        score=findViewById(R.id.score);

        if(cname.equals("c")){
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference CquestionRef=rootRef.child("cq").child("questionofc");
            DatabaseReference CanswerRef=rootRef.child("cq").child("answerofq");
            List<String> cquestions=new ArrayList<>();
            List<String> canswers=new ArrayList<>();

                        //Initial question
            CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                           // cquestions.add(questionSnapshot.getKey().toString());
                            cquestions.add(questionSnapshot.getValue().toString());

                        }
                        try{
                            listofq.setText(cquestions.get(0).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });
//initial answer
            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                           ans=(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

            //next question
            next.setOnClickListener(v->{
                nextq++;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=canswers.get(nextq).toString();
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            });

//previous question
            prev.setOnClickListener(v->{
                nextq--;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                  ans=(canswers.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                });
            });



            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            //ans.setText(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

        }

        //for cpp course
        else if(cname.equals("cpp")){
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference CquestionRef=rootRef.child("cpp").child("questionofcpp");
            DatabaseReference CanswerRef=rootRef.child("cpp").child("answerofcpp");
            List<String> cquestions=new ArrayList<>();
            List<String> canswers=new ArrayList<>();

            //Initial question
            CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            // cquestions.add(questionSnapshot.getKey().toString());
                            cquestions.add(questionSnapshot.getValue().toString());

                        }
                        try{
                            listofq.setText(cquestions.get(0).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });
//initial answer
            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            ans=(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

            //next question
            next.setOnClickListener(v->{
                nextq++;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=canswers.get(nextq).toString();
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            });

//previous question
            prev.setOnClickListener(v->{
                nextq--;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=(canswers.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                });
            });



            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            //ans.setText(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

        }

        //java course
        else if(cname.equals("java")){
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference CquestionRef=rootRef.child("java").child("questionofjava");
            DatabaseReference CanswerRef=rootRef.child("java").child("answerofjava");
            List<String> cquestions=new ArrayList<>();
            List<String> canswers=new ArrayList<>();

            //Initial question
            CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            // cquestions.add(questionSnapshot.getKey().toString());
                            cquestions.add(questionSnapshot.getValue().toString());

                        }
                        try{
                            listofq.setText(cquestions.get(0).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });
//initial answer
            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            ans=(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

            //next question
            next.setOnClickListener(v->{
                nextq++;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=canswers.get(nextq).toString();
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            });

//previous question
            prev.setOnClickListener(v->{
                nextq--;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=(canswers.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                });
            });



            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            //ans.setText(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

        }

//next course python
        else if(cname.equals("python")){
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference CquestionRef=rootRef.child("python").child("questionofpython");
            DatabaseReference CanswerRef=rootRef.child("python").child("answerofpython");
            List<String> cquestions=new ArrayList<>();
            List<String> canswers=new ArrayList<>();

            //Initial question
            CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            // cquestions.add(questionSnapshot.getKey().toString());
                            cquestions.add(questionSnapshot.getValue().toString());

                        }
                        try{
                            listofq.setText(cquestions.get(0).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });
//initial answer
            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            ans=(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

            //next question
            next.setOnClickListener(v->{
                nextq++;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=canswers.get(nextq).toString();
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            });

//previous question
            prev.setOnClickListener(v->{
                nextq--;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=(canswers.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                });
            });



            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            //ans.setText(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

        }

//course of js
        else if(cname.equals("js")) {
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference CquestionRef=rootRef.child("js").child("questionofjs");
            DatabaseReference CanswerRef=rootRef.child("js").child("answerofjs");
            List<String> cquestions=new ArrayList<>();
            List<String> canswers=new ArrayList<>();

            //Initial question
            CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            // cquestions.add(questionSnapshot.getKey().toString());
                            cquestions.add(questionSnapshot.getValue().toString());

                        }
                        try{
                            listofq.setText(cquestions.get(0).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });
//initial answer
            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            ans=(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

            //next question
            next.setOnClickListener(v->{
                nextq++;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=canswers.get(nextq).toString();
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            });

//previous question
            prev.setOnClickListener(v->{
                nextq--;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=(canswers.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                });
            });



            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            //ans.setText(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });
        }
//course for dbms
        else if(cname.equals("dbms")) {
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference CquestionRef=rootRef.child("dbms").child("questionofdbms");
            DatabaseReference CanswerRef=rootRef.child("dbms").child("answerofdbms");
            List<String> cquestions=new ArrayList<>();
            List<String> canswers=new ArrayList<>();

            //Initial question
            CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            // cquestions.add(questionSnapshot.getKey().toString());
                            cquestions.add(questionSnapshot.getValue().toString());

                        }
                        try{
                            listofq.setText(cquestions.get(0).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });
//initial answer
            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            ans=(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

            //next question
            next.setOnClickListener(v->{
                nextq++;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=canswers.get(nextq).toString();
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            });

//previous question
            prev.setOnClickListener(v->{
                nextq--;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=(canswers.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                });
            });



            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            //ans.setText(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });
        }

        //course for ds
        else if(cname.equals("ds")){
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference CquestionRef=rootRef.child("ds").child("questionofds");
            DatabaseReference CanswerRef=rootRef.child("ds").child("answerofds");
            List<String> cquestions=new ArrayList<>();
            List<String> canswers=new ArrayList<>();

            //Initial question
            CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            // cquestions.add(questionSnapshot.getKey().toString());
                            cquestions.add(questionSnapshot.getValue().toString());

                        }
                        try{
                            listofq.setText(cquestions.get(0).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });
//initial answer
            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            ans=(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

            //next question
            next.setOnClickListener(v->{
                nextq++;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=canswers.get(nextq).toString();
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            });

//previous question
            prev.setOnClickListener(v->{
                nextq--;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=(canswers.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                });
            });



            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            //ans.setText(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });


        }

        // course for php
        //course for ds
        else if(cname.equals("php")){
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference CquestionRef=rootRef.child("php").child("questionofphp");
            DatabaseReference CanswerRef=rootRef.child("php").child("answerofphp");
            List<String> cquestions=new ArrayList<>();
            List<String> canswers=new ArrayList<>();

            //Initial question
            CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            // cquestions.add(questionSnapshot.getKey().toString());
                            cquestions.add(questionSnapshot.getValue().toString());

                        }
                        try{
                            listofq.setText(cquestions.get(0).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });
//initial answer
            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            ans=(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });

            //next question
            next.setOnClickListener(v->{
                nextq++;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=canswers.get(nextq).toString();
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            });

//previous question
            prev.setOnClickListener(v->{
                nextq--;
                CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                cquestions.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                listofq.setText(cquestions.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){
                            for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                                //            cquestions.add(questionSnapshot.getKey().toString());
                                canswers.add(questionSnapshot.getValue().toString());
                            }
                            try {
                                ans=(canswers.get(nextq).toString());
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                });
            });



            CanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                            canswers.add(answersnapshot.getValue().toString());

                        }
                        try
                        {
                            //ans.setText(canswers.get(0).toString());
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            });


        }





        //code for text to speech
        iv_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e) {
                    Toast
                            .makeText(getApplicationContext(), " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                tv_Speech_to_text.setText(
                        Objects.requireNonNull(result).get(0));
                String paragraph1=tv_Speech_to_text.getText().toString();
                String paragraph2=ans;
                double similarity = calculateSimilarity(paragraph1, paragraph2);
                scoretx.setText(String.format("Your score is %.2f%% .", similarity * 100));
                progressBar.setMax(100);
                int currentprogress =(int) similarity*100;
                progressBar.setProgress(currentprogress);


            }
        }


    }
    private double calculateSimilarity(String paragraph1, String paragraph2) {
        String[] words1 = paragraph1.split("\\s+");
        String[] words2 = paragraph2.split("\\s+");
        Set<String> set1 = new HashSet<>(Arrays.asList(words1));
        Set<String> set2 = new HashSet<>(Arrays.asList(words2));
        Set<String> unionSet = new HashSet<>(set1);
        unionSet.addAll(set2);
        int intersectionCount = set1.size() + set2.size() - unionSet.size();
        return (double) intersectionCount / unionSet.size();
    }

}