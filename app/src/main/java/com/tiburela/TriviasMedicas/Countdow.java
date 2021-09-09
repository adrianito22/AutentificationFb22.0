package com.tiburela.TriviasMedicas;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;

public class Countdow extends AppCompatActivity {
    LottieAnimationView anima_countdown;

    SoundPool countdown_sound;
    int count_down;

   // MediaPlayer mp3ready_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdow);

        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        anima_countdown=findViewById(R.id.coin_collection_anim);

        anima_countdown.addAnimatorListener(new Animator.AnimatorListener() {






            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("Animation:","start");

                anima_countdown.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("Animation:","end");
                anima_countdown.setVisibility(View.GONE);

              //  anima_countdown.setVisibility(View.VISIBLE);

                IraJuego();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e("Animation:","cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e("Animation:","repeat");
            }
        });




    }

  public void   IraJuego()
    {
        Intent inn =new Intent(this, Juego_Partida.class );
        startActivity(inn);

    }


    public void   IraJuego2()
    {
        Intent inn =new Intent(this, Juego_Partida.class );
        startActivity(inn);

    }





}