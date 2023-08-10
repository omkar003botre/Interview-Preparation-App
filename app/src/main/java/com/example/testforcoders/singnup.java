package com.example.testforcoders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

import org.jetbrains.annotations.NotNull;

public class singnup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singnup);
        Button regi=findViewById(R.id.crateac);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        regi.setOnClickListener(V->{

            //uname is for email id
            EditText uname=findViewById(R.id.activity_main_usernameEditText);
            String uname2=uname.getText().toString();
            EditText passcode=findViewById(R.id.pass);
            String passcode2=passcode.getText().toString();
            EditText confirmpass=findViewById(R.id.confirmpasscode);
            String confirmpass2=confirmpass.getText().toString();
            Dialog dialog;
            //checking for email syntax for email
            if(Patterns.EMAIL_ADDRESS.matcher(uname2).matches()){
            //checking for password pattern

                if(passcode2.matches(confirmpass2) && passcode2.length()>=8){
                    InputMethodManager imm=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                    //creating a dialoge box
                    dialog=new Dialog(singnup.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_wait);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    auth.fetchSignInMethodsForEmail(uname2).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                            boolean isnewuser=task.getResult().getSignInMethods().isEmpty();
                            if(isnewuser){
                                //if user is new
                                //creating an account with user
                                auth.createUserWithEmailAndPassword(uname2,passcode2)
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                            @Override
                                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                                                if(task.isSuccessful()){

                                                    Toast.makeText(getApplicationContext(),"Sucessfully created",Toast.LENGTH_SHORT).show();
                                                    FirebaseUser user=auth.getCurrentUser();
                                                    if(user!=null){
                                                        //after sucessful registraion
                                                        dialog.dismiss();
                                                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                                        startActivity(i);
                                                        finish();

                                                    }
                                                    else {
                                                        dialog.dismiss();
                                                        Toast.makeText(getApplicationContext(),"Authentication failed",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        });
                            }
                            else {
                                //user exsists
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Please try another email id",Toast.LENGTH_SHORT).show();
                                uname.setError("This Email id already exists");
                                uname.requestFocus();



                            }
                        }
                    });





            }
                //if password is < 8
                else if (passcode2.length() <8) {
                passcode.setError("Password must be of 8 character long");
                passcode.requestFocus();
            }
                //if password is not metched
            else{
           confirmpass.setError("Password doesn't match ");
           confirmpass.requestFocus();
            }

            }
            else{
                AccessibilityNodeInfo emailInput;
                uname.setError("Please enter a valid Email id");
                uname.requestFocus();
            }
        });


    }
}