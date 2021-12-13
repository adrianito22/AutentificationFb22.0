package com.tiburela.TriviasMedicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.tiburela.TriviasMedicas.control_intro_e_inidcaciones.IntroSlider;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView ;
    Animation fadeInAnimation;
 //   private IntroPref introPref;
   public  Intro_preferencias2 miprefers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

         imageView = findViewById(R.id.imageView);
         fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
         imageView.startAnimation(fadeInAnimation); //

        comprube_first_inicio();
    }


  public void  comprube_first_inicio(){

        Boolean isFirstTime;

        SharedPreferences app_preferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = app_preferences.edit();

        isFirstTime = app_preferences.getBoolean("isFirstTime", true);




      if (isFirstTime) {  //si es la primera vez que entra muestrale un intro slider
          //en este caso no le mostramos ...

//implement your first time logic

          editor.putBoolean("isFirstTime", false);
          editor.commit();

          new Handler() .postDelayed(() -> {

            //  Intent intent=new Intent (SplashScreen.this, Home_Tab.class);

              Intent intento=new Intent (SplashScreen.this, MainActivity2dos.class);
              startActivity(intento);
              finish();
          },2000);


      }else{
//app open directly

         // Toast.makeText(this, "ya se inicio antes", Toast.LENGTH_SHORT).show();

          new Handler() .postDelayed(() -> {
              Intent intent=new Intent (SplashScreen.this, MainActivity2dos.class);
              startActivity(intent);
              finish();
          },2000);




      }






  }



}