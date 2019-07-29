package com.example.penaliz;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Email_Send extends AppCompatActivity {
    private EditText email,sub,body;
    private Button send;
    SharedPreferences loginpref,affencedatepref,regnopref;
    String loginuserid,affencedate,aregno,eemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email__send);
        email=(EditText)findViewById(R.id.emailid);
        sub=(EditText)findViewById(R.id.sub);
        body=(EditText)findViewById(R.id.body);
        loginpref = getApplicationContext().getSharedPreferences("LoginPref", MODE_PRIVATE);
        loginuserid = loginpref.getString("total", null);
        affencedatepref = getApplicationContext().getSharedPreferences("AffenceDatePref", MODE_PRIVATE);
        affencedate = affencedatepref.getString("affencedate",null);
        regnopref = getApplicationContext().getSharedPreferences("RegnoPref", MODE_PRIVATE);
        aregno = regnopref.getString("regnumber",null);

        body.setText("The rider/owner/driver of vehical number "+aregno+" violated the traffic rules on date of "+affencedate+" Thus he/she paid penalty of "+loginuserid+"RS");
        eemail = email.getText().toString();
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";


        send=(Button)findViewById(R.id.send);


            send.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (eemail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]+")){
                        Toast.makeText(Email_Send.this,"Enter a valid Email",Toast.LENGTH_LONG).show();
                    }

                   /* {
                        Toast.makeText(Email_Send.this,"Enter a valid Email",Toast.LENGTH_LONG).show();
                    }*/
                   /* if (eemail.matches(emailPattern))
                        {
                            sendmail();
                    }*/
                    else{
                        sendmail();
                    }



                }
            });


    }
    private void sendmail(){

        String rec=email.getText().toString();
        String []recipents=rec.split(",");
        String mSub=sub.getText().toString();
        String mBody=body.getText().toString();
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipents);
        intent.putExtra(Intent.EXTRA_SUBJECT,mSub);
        intent.putExtra(Intent.EXTRA_TEXT,mBody);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an Email client"));
        }

}
