package com.example.testforcoders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class javainterview extends AppCompatActivity {
    int nextq=0,currentprogress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javainterview);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference javaquestionRef=rootRef.child("java").child("questionofjava");
        DatabaseReference javaanswerRef=rootRef.child("java").child("answerofjava");
        List<String> javaquestions=new ArrayList<>();
        List<String> javaanswers=new ArrayList<>();
        TextView listofq = (TextView) findViewById(R.id.listofq);
        Button next=(Button) findViewById(R.id.nextq);
        TextView ans =(TextView)findViewById(R.id.answer);
        Button prev=(Button)findViewById(R.id.previousbtn);
        ProgressBar progressBar=findViewById(R.id.progressBar);
        javaquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){
                    for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                        //cquestions.add(questionSnapshot.getKey().toString());
                        javaquestions.add(questionSnapshot.getValue().toString());

                    }
                    try{
                        listofq.setText(javaquestions.get(0).toString());
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
//initial answer
        javaanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                        javaanswers.add(answersnapshot.getValue().toString());

                    }
                    try
                    {
                        ans.setText(javaanswers.get(0).toString());
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                    }
                }

            }


        });

        next.setOnClickListener(v->{
            nextq++;
            currentprogress =currentprogress+5;
            progressBar.setProgress(currentprogress);
            progressBar.setMax(100);

            javaquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            //            cquestions.add(questionSnapshot.getKey().toString());
                            javaquestions.add(questionSnapshot.getValue().toString());
                        }
                        try {
                            listofq.setText(javaquestions.get(nextq).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            javaanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            //            cquestions.add(questionSnapshot.getKey().toString());
                            javaanswers.add(questionSnapshot.getValue().toString());
                        }
                        try {
                            ans.setText(javaanswers.get(nextq).toString());
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
            javaquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            //            cquestions.add(questionSnapshot.getKey().toString());
                            javaquestions.add(questionSnapshot.getValue().toString());
                        }
                        try {
                            listofq.setText(javaquestions.get(nextq).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            javaanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            //            cquestions.add(questionSnapshot.getKey().toString());
                            javaanswers.add(questionSnapshot.getValue().toString());
                        }
                        try {
                            ans.setText(javaanswers.get(nextq).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
        });

    }
}