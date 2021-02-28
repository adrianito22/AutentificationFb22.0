package com.tiburela.TriviasMedicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.tiburela.TriviasMedicas.utils.IntroPref;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView ;
    Animation fadeInAnimation;
    private IntroPref introPrefer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

         imageView = findViewById(R.id.imageView);
         fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
         imageView.startAnimation(fadeInAnimation); //



        introPrefer = new IntroPref(this);
        if (!introPrefer.isFirstTimeLaunch()) {
            Toast.makeText(this, "ya se inicio antes", Toast.LENGTH_SHORT).show();

            new Handler() .postDelayed(() -> {
                Intent intent=new Intent (SplashScreen.this, com.tiburela.TriviasMedicas.Home.class);
                startActivity(intent);
                finish();
            },2000);


        }
        else{
            Toast.makeText(this, "nose inicia aun", Toast.LENGTH_SHORT).show();

            new Handler() .postDelayed(() -> {
                Intent intent=new Intent (SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            },2000);



        }






    }

}