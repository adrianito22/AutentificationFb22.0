package com.tiburela.TriviasMedicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    MediaPlayer mp3_selec_sound;
int scorelab;
int quizcorrect;


TextView puntaje;
TextView monedastxt;

TextView respuestas_correctas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mp3_selec_sound  =MediaPlayer.create(this, R.raw.selec_sound);

      puntaje= findViewById(R.id.textView47);
      monedastxt= findViewById(R.id.textView21);
      respuestas_correctas=findViewById(R.id.result);



      /*


        SharedPreferences mysharedpreferences=getSharedPreferences("SCORE",MODE_PRIVATE);
        SharedPreferences.Editor prefeEditor =mysharedpreferences.edit();
                scorelab =mysharedpreferences.getInt("SCORE",0);
        Toast.makeText(this, "el valor del puntaje es "+scorelab, Toast.LENGTH_SHORT).show();
      //  Bundle bundle=geta

        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        int valor_sharep_indice  =sharedpreferences.getInt("HOLA",0);

     //   scorelab=  bundle.getInt("SCORELAB",0);



*/


//recupera el valor de shreprefrences de l a activity juego partida
        ///////////////////////////////////////////////////////////////////

        SharedPreferences mysharedpreferences  = getSharedPreferences("MyPREFERENCESS", MODE_PRIVATE); //add key
        SharedPreferences.Editor prefsEditor = mysharedpreferences .edit();


         scorelab = mysharedpreferences.getInt("USERINFO_SCORE", 0);

/////////////////////////////////////////////////////////////////////////////////////////////

        quizcorrect  =mysharedpreferences.getInt("QUIZ_CORRECT",0);



        float monedas=scorelab/15;
        int moneditas=Math.round(monedas);




        String quizcorrect_txt=String.valueOf(quizcorrect);
        String score_text= String.valueOf(scorelab);
        String monedi=String.valueOf(moneditas);

     puntaje.setText(score_text);
     monedastxt.setText(monedi);
     respuestas_correctas.setText(quizcorrect_txt+" /4000");



    }

   public void startandContinuar(View vista){
       mp3_selec_sound.start();
       Intent inn =new Intent(this, com.tiburela.TriviasMedicas.IntroSlider.class );
       startActivity(inn);

    }

    public void mejoresPuntuaciones(View vista){
        mp3_selec_sound.start();
        Intent in =new Intent(this, ProfileActivity.class );
        startActivity(in);



    }


}