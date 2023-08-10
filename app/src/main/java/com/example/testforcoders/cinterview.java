package com.example.testforcoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import android.view.MotionEvent;
import android.widget.ToggleButton;

public class cinterview extends AppCompatActivity implements View.OnTouchListener  {
    int nextq=0,currentprogress=0;
    int mBaseDistance;
    float mRatio=1.0f;
    float mBaseRatio;
    final static float step=200;
    TextView ans,listofq,qtitle,anstitle;
    LinearLayout mainLayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinterview);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference CquestionRef=rootRef.child("cq").child("questionofc");
        DatabaseReference CanswerRef=rootRef.child("cq").child("answerofq");
        List<String> cquestions=new ArrayList<>();
        List<String> canswers=new ArrayList<>();
        listofq = (TextView) findViewById(R.id.listofq);
        Button next=(Button) findViewById(R.id.nextq);
        ans =(TextView)findViewById(R.id.answer);
        Button prev=(Button)findViewById(R.id.previousbtn);
        ProgressBar progressBar=findViewById(R.id.progressBar);

        qtitle=findViewById(R.id.listofq);
        anstitle=findViewById(R.id.answer);


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
    public boolean onTouchEvent(MotionEvent motionEvent) {
     if(motionEvent.getPointerCount()==2){
         int action=motionEvent.getAction();
         int pureaction=action & motionEvent.getActionMasked();
         if(pureaction==motionEvent.ACTION_POINTER_DOWN){
mBaseDistance=getDistance(motionEvent);
mBaseRatio=mRatio;
         }
         else{
float delta=(getDistance(motionEvent)-mBaseDistance)/step;
float multi=(float)( Math.pow(2,delta));
mRatio=Math.min(1024.0f,Math.max(0.1f,multi * mBaseRatio));
ans.setTextSize(mRatio+13);
         }

     }

        return true;
    }
int getDistance(MotionEvent motionEvent){
        int dx=(int)(motionEvent.getX(0)-motionEvent.getX(1));
    int dy=(int)(motionEvent.getY(0)-motionEvent.getY(1));
    return   (int)(Math.sqrt(dx * dx +dy * dy));
}
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

    // Set the mode to day mode


}