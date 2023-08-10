package com.example.testforcoders;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText uname=findViewById(R.id.activity_main_usernameEditText);
        EditText upass=findViewById(R.id.activity_main_passwordEditText);
        Button loginbtn=findViewById(R.id.activity_main_loginButton);
        Button regi=findViewById(R.id.register);
        TextView forgottext=findViewById(R.id.forgot);
        String forgorid=forgottext.getText().toString();
        TextView help=findViewById(R.id.help);
        //checking internet connection
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
            //connection is avlilable
            FirebaseAuth mauth=FirebaseAuth.getInstance();

            loginbtn.setOnClickListener(v ->{
                String u_name=uname.getText().toString();
                String u_pass=upass.getText().toString();
                //checking for empty email
                if(Patterns.EMAIL_ADDRESS.matcher(u_name).matches()) {

                    mauth.signInWithEmailAndPassword(u_name,u_pass).addOnCompleteListener(task -> {
                        if(task.isSuccessful())
                        {    Toast.makeText(getApplicationContext(),"Succesfully log in ",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(),pageafterlogin.class);
                            i.putExtra("uid",u_name);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),
                                    "Please Check Your login Credentials",
                                    Toast.LENGTH_SHORT).show();
                        }

                    });

                }
                //if doesnot matches
                else{
                    uname.setError("Please enter a valid Email id");
                    uname.requestFocus();
                }

            });
            regi.setOnClickListener(v->{
                Intent int45=new Intent(getApplicationContext(),singnup.class);
                startActivity(int45);
            });
            forgottext.setOnClickListener(V->{
                Intent intfg=new Intent(getApplicationContext(),forgotpassword.class);
                intfg.putExtra("emaild",forgorid);
                startActivity(intfg);
            });
        }else{
            //no connection
            Toast toast = Toast.makeText(getApplicationContext(), "No Internet Connection",
                    Toast.LENGTH_LONG);
            toast.show();
        }
        //firebase auth object
help.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       /* String number="7666789660";
        String url = "https://api.whatsapp.com/send?phone="+number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        */
        Intent intent=new Intent(Intent.ACTION_SEND);
        String[] recipients={"courseforcoders@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
        intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
        intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(Intent.createChooser(intent, "Send mail"));
    }
});
    }
}