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

public class pythoninterview extends AppCompatActivity {
    int nextq=0,currentprogress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pythoninterview);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference pythonquestionRef=rootRef.child("python").child("questionofpython");
        DatabaseReference pythonanswerRef=rootRef.child("python").child("answerofpython");
        List<String> pythonquestions=new ArrayList<>();
        List<String> pythonanswers=new ArrayList<>();
        TextView listofq = (TextView) findViewById(R.id.listofq);
        Button next=(Button) findViewById(R.id.nextq);
        TextView ans =(TextView)findViewById(R.id.answer);
        Button prev=(Button)findViewById(R.id.previousbtn);
        ProgressBar progressBar=findViewById(R.id.progressBar);

        //Initial question
        pythonquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){
                    for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                        //cquestions.add(questionSnapshot.getKey().toString());
                        pythonquestions.add(questionSnapshot.getValue().toString());

                    }
                    try{
                        listofq.setText(pythonquestions.get(0).toString());
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
//initial answer
        pythonanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    for(DataSnapshot answersnapshot:task.getResult().getChildren()){
                        pythonanswers.add(answersnapshot.getValue().toString());

                    }
                    try
                    {
                        ans.setText(pythonanswers.get(0).toString());
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                    }
                }

            }


        });

        //next question
        next.setOnClickListener(v->{
            nextq++;
            currentprogress =currentprogress+5;
            progressBar.setProgress(currentprogress);
            progressBar.setMax(100);
            pythonquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            //            cquestions.add(questionSnapshot.getKey().toString());
                            pythonquestions.add(questionSnapshot.getValue().toString());
                        }
                        try {
                            listofq.setText(pythonquestions.get(nextq).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            pythonanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            //            cquestions.add(questionSnapshot.getKey().toString());
                            pythonanswers.add(questionSnapshot.getValue().toString());
                        }
                        try {
                            ans.setText(pythonanswers.get(nextq).toString());
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
            pythonquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            //            cquestions.add(questionSnapshot.getKey().toString());
                            pythonquestions.add(questionSnapshot.getValue().toString());
                        }
                        try {
                            listofq.setText(pythonquestions.get(nextq).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            pythonanswerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if(task.isSuccessful()){
                        for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                            //            cquestions.add(questionSnapshot.getKey().toString());
                            pythonanswers.add(questionSnapshot.getValue().toString());
                        }
                        try {
                            ans.setText(pythonanswers.get(nextq).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
        });


    }
}