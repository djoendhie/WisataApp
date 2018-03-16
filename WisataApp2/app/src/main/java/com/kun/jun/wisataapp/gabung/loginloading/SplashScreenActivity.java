package com.kun.jun.wisataapp.gabung.loginloading;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.kun.jun.wisataapp.MainActivity;
import com.kun.jun.wisataapp.R;

import java.util.Locale;

public class SplashScreenActivity extends AppCompatActivity {

    public final int SPLASH_DISPLAY_LENGH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String lang = String.valueOf(Locale.ENGLISH);
                Locale locale = new Locale(lang);
                Locale.setDefault(locale);
                Configuration config = new Configuration ();
                config.locale = locale;

                SplashScreenActivity.this.getResources().updateConfiguration(config,
                        SplashScreenActivity.this.getResources().getDisplayMetrics()) ;
                //after three seconds, it will execute all of this code.
                //as such, we then want to redirect to the master-activity
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                //then we finish this class. Dispose of it as it is longer needed
                SplashScreenActivity.this.finish();

            }
        }, SPLASH_DISPLAY_LENGH);
    }
}