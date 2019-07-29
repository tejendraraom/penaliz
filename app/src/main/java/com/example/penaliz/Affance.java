package com.example.penaliz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Affance extends AppCompatActivity {
    private Switch helmet, drivinglisince, insurence, emmission, trippleride, hit, rashdrive, overspeed, seatbelt, wrongroot;
    private Button cal,logout,refr,came,btnnn;
    int he = 0, dl = 0, in = 0, emm = 0, tr = 0, hi = 0, rd = 0, os = 0, sb = 0, wr = 0;
    private DatabaseReference mDatabaseRef;
    SharedPreferences loginpref,affencedatepref,regnopref;
    SharedPreferences.Editor editor,affencedate,aregno;
    private FirebaseAuth auth;
    private String s3,s4;
    private EditText et1;
    String s5,s6;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affance);



        refr = (Button)findViewById(R.id.refresh);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("abcd");
        final EditText mRegno = (EditText) findViewById(R.id.regno);
        final EditText mDate = (EditText) findViewById(R.id.date);
        final TextView mTotal = (TextView) findViewById(R.id.total);
        et1 = (EditText)findViewById(R.id.regno);
        came = (Button)findViewById(R.id.cam);
        Button upload = (Button) findViewById(R.id.upload);
        Button show = (Button) findViewById(R.id.showupload);
        Button logout=(Button)findViewById(R.id.logout);
        emmission = (Switch) findViewById(R.id.emmission);
        trippleride = (Switch) findViewById(R.id.triple_riding);
        helmet = (Switch) findViewById(R.id.helmet);
        drivinglisince = (Switch) findViewById(R.id.dl);
        insurence = (Switch) findViewById(R.id.insurence);
        hit = (Switch) findViewById(R.id.hit);
        btnnn = (Button)findViewById(R.id.loactionn);
        overspeed = (Switch) findViewById(R.id.overspeed);
        rashdrive = (Switch) findViewById(R.id.rashdrive);
        seatbelt = (Switch) findViewById(R.id.seatbelt);
        wrongroot = (Switch) findViewById(R.id.wrongroot);
        loginpref = getApplicationContext().getSharedPreferences("LoginPref", MODE_PRIVATE);
        editor = loginpref.edit();
        affencedatepref = getApplicationContext().getSharedPreferences("AffenceDatePref", MODE_PRIVATE);
        affencedate = affencedatepref.edit();
        regnopref = getApplicationContext().getSharedPreferences("RegnoPref", MODE_PRIVATE);
        aregno = regnopref.edit();
        refr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                mDate.setText(dateFormat.format(new Date()));

            }
        });

        Intent i = getIntent();
        String s2 = i.getStringExtra("message");
        if(s2 == null)
        {
            Toast.makeText(getApplicationContext(),"enter valid no",Toast.LENGTH_LONG).show();
        }
        else {
            s5 = s2.replaceAll("[\r\n]+", "");
            s6 = s5.replaceAll("\\s+", "");
            et1.setText(s2);
        }
       came.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(Affance.this,textrecognizer.class);
        startActivity(i);

    }
});

        btnnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Affance.this,loaction.class);
                startActivity(i);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = mDate.getText().toString();
                String regno = mRegno.getText().toString();
                String amt = mTotal.getText().toString();
                if (!TextUtils.isEmpty(date) && !TextUtils.isEmpty(regno)) {
                    String id=mDatabaseRef.push().getKey();
                    Traffic traffic=new Traffic(date,regno,amt);
                    mDatabaseRef.child(id).setValue(traffic);
                    Toast.makeText(Affance.this,"Data Uploaded",Toast.LENGTH_LONG).show();
                    affencedate.putString("affencedate",date);
                    affencedate.commit();
                    aregno.putString("regnumber",regno);
                    aregno.commit();
                }
                else {
                    Toast.makeText(Affance.this,"Enter fields", Toast.LENGTH_LONG).show();
                }

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Affance.this,FineList.class ));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(),"LOG-OUT Succcessful", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Affance.this,MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        /*helmet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                he = 5000;
            }
        });*/

        helmet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    he = 100;
                }
                else {
                    he=0;
                }

            }
        });



 /*       drivinglisince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl = 8000;
            }
        });*/

        drivinglisince.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    dl = 300;
                }
                else {
                    dl = 0;
                }
            }
        });

       /* insurence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = 2000;
            }
        });*/

        insurence.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    in=400;
                }
                else {
                    in=0;
                }
            }
        });

        /*emmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emm = 1000;
            }
        });*/

        emmission.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    emm=200;
                }
                else {
                    emm=0;
                }
            }
        });
        /*trippleride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tr = 3000;
            }
        });*/
        trippleride.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tr=200;
                }
                else {
                    tr=0;
                }
            }
        });
        hit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    hi=700;
                }
                else {
                    hi=0;
                }
            }
        });
        rashdrive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rd=400;
                }
                else {
                    rd=0;
                }
            }
        });
        overspeed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    os=300;
                }
                else {
                    os=0;
                }
            }
        });
        seatbelt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sb=200;
                }
                else {
                    sb=0;
                }
            }
        });
        wrongroot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    wr=100;
                }
                else {
                    wr=0;
                }
            }
        });
        cal = (Button) findViewById(R.id.cal);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                int a = he + dl + in + emm + tr + hi + rd + os + sb + wr;
                TextView mTotal = (TextView) findViewById(R.id.total);
                mTotal.setText("" + a);
                editor.putString("total", mTotal.getText().toString());
                Log.d("total",mTotal.getText().toString());
                editor.commit();
            }
        });

        Button sendemail=(Button)findViewById(R.id.sendmail);
        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Affance.this,Email_Send.class));
            }
        });

        Button came = (Button) findViewById(R.id.cam);
        //Goes to cam activity


        EditText et1 = (EditText) findViewById(R.id.regno);
        // Impliment to import striing
        Intent i = getIntent();
        String s2 = i.getStringExtra("message");
        et1.setText(s2);



    }
}
