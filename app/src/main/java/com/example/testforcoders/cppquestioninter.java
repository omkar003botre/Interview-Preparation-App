package com.example.testforcoders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class cppquestioninter extends AppCompatActivity {
    int nextq=0,currentprogress=0;
    LinearLayout mainLayout;

    TextView ans,listofq,qtitle,anstitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cppquestioninter);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference CquestionRef=rootRef.child("cpp").child("questionofcpp");
        DatabaseReference CanswerRef=rootRef.child("cpp").child("answerofcpp");
        List<String> cquestions=new ArrayList<>();

        List<String> canswers=new ArrayList<>();
        listofq = (TextView) findViewById(R.id.listofq);
        Button next=(Button) findViewById(R.id.nextq);
        ans =(TextView)findViewById(R.id.answer);
        Button prev=(Button)findViewById(R.id.previousbtn);
        ProgressBar progressBar=findViewById(R.id.progressBar);

        qtitle=findViewById(R.id.listofq);
        anstitle=findViewById(R.id.answer);



        // Set the initial mode based on the toggle button state


        // Set a listener for the toggle button to toggle the mode when clickedmodeToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {



        //Initial question
        CquestionRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){
                    for(DataSnapshot questionSnapshot : task.getResult().getChildren()){
                        //cquestions.add(questionSnapshot.getKey().toString());
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
                        ans.setText(canswers.get(0).toString());
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
                            ans.setText(canswers.get(nextq).toString());
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
                            ans.setText(canswers.get(nextq).toString());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"This is the first question ",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
        });


    }



}