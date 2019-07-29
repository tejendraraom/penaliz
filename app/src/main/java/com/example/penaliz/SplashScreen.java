package com.example.penaliz;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
private FirebaseAuth auth;
Animation charanim;
ImageView charplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        charplace = (ImageView)findViewById(R.id.im1);
        charanim = AnimationUtils.loadAnimation(this,R.anim.charanim);
        charplace.startAnimation(charanim);
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);

                    // After 5 seconds redirect to another intent
                    auth = FirebaseAuth.getInstance();
                    if (auth.getCurrentUser() != null) {
                        startActivity(new Intent(SplashScreen.this, Affance.class));
                        finish();
                    }else {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    }

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();
    }
}
