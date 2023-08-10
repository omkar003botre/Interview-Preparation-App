
package com.example.testforcoders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.mail.Message;
import  javax.mail.MessagingException;
import  javax.mail.PasswordAuthentication;
import  javax.mail.Session;
import  javax.mail.Transport;
import  javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class forgotpassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        /*
        verifybtn.setOnClickListener(V->
        {

        });
*/
        EditText uid=findViewById(R.id.uid);
        Button otpbtn=findViewById(R.id.sendotpbtn);
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        Button verifyotp=findViewById(R.id.verifyotpbtn);
        EditText validatebox=findViewById(R.id.validatebox);
        String validatebox1=validatebox.getText().toString();
        //random number generation
        Random random = new Random();
        int upperbound = 1000;
        int otp = random.nextInt(upperbound);
        final String sender = "courseforcoders@gmail.com";
        final String password = "kejmufzslcwbtkqs";
        String textotp = Integer.toString(otp);
        String msgtosend = textotp;
        String uid1 = uid.getText().toString();
        otpbtn.setOnClickListener(V-> {

            if (Patterns.EMAIL_ADDRESS.matcher(uid1).matches()) {
                mauth.fetchSignInMethodsForEmail(uid1).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        boolean newuser = task.getResult().getSignInMethods().isEmpty();
                        //if user is new
                        if (newuser) {
                            Toast.makeText(getApplicationContext(), "This email id is not register", Toast.LENGTH_SHORT).show();
                            uid.setError("This email id is not register");
                            uid.requestFocus();
                        }
                        else {
                            //sending an otp to mail

                            //otp mail send logic

                            //String recievermailid =uid1 ;
                            String msgtosendemail = "Your OTP For changing password is " + msgtosend;
                            Properties props = new Properties();
                            props.put("mail.smtp.auth", "true");
                            props.put("mail.smtp.starttls.enable", "true");
                            props.put("mail.smtp.host", "smtp.gmail.com");
                            props.put("mail.smtp.port", "587");
                            Session session = Session.getInstance(props,
                                    new javax.mail.Authenticator() {
                                        @Override
                                        protected PasswordAuthentication getPasswordAuthentication() {
                                            return new PasswordAuthentication(sender, password);
                                        }
                                    });
                            try {
                                Message message = new MimeMessage(session);
                                message.setFrom(new InternetAddress(sender));
                                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(uid.getText().toString()));
                                message.setSubject("Recover your account");
                                message.setText(msgtosendemail);
                                Transport.send(message);
                                Toast.makeText(getApplicationContext(), "OTP Has been send to your email id", Toast.LENGTH_SHORT).show();
                            } catch (MessagingException e) {
                                throw new RuntimeException(e);

                            }


                        }
                    }
                });
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

            }
            else{
                uid.setError("Please enter valid email id");
                uid.requestFocus();
            }

            });
        verifyotp.setOnClickListener(v->{
            //otp is correct
            if(validatebox1.equals(msgtosend)){
               Intent itx=new Intent(getApplicationContext(),validateotp.class);
               itx.putExtra("emailid",uid1);
               startActivity(itx);
            }
            else {
                validatebox.setError("Invalid OTP");
                validatebox.requestFocus();
            }
        });
    }
}
