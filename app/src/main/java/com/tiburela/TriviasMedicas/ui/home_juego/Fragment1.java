package com.tiburela.TriviasMedicas.ui.home_juego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tiburela.TriviasMedicas.R;
import com.tiburela.TriviasMedicas.control_intro_e_inidcaciones.IntroSlider;

import static android.content.Context.MODE_PRIVATE;


public class Fragment1 extends Fragment {

    Button btn_empezarjuego;
    Button btn_continuar_juego;
    Button btn_puntuar_app;


    MediaPlayer mp3_selec_sound;
    int scorelab;
    //int quizcorrect;
    int nivel;
    TextView puntaje;
    TextView monedastxt;
    TextView nivel_texto;
   Button bt_mejores_p;
    public Fragment1() {


    }

    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);

        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_1, container, false);
        puntaje=  view.findViewById(R.id.textView47); //de esta forma tambien accedo a findview id...etc..
       // puntaje=  this.getActivity().findViewById(R.id.textView47);
        monedastxt= view.findViewById(R.id.monedas);
        nivel_texto=view.findViewById(R.id.result);

        btn_puntuar_app=view.findViewById(R.id.btn_puntuar_app);
        btn_continuar_juego =view.findViewById(R.id.btn_continuar_juego);
        btn_empezarjuego=view.findViewById(R.id.btn_empezarjuego);

        actualiza_puntuacion();

        eventosBotones();


        return view;

    }

    public void startandContinuar(View vista){
        mp3_selec_sound.start();
        Intent inn =new Intent(getActivity(), IntroSlider.class );
        startActivity(inn);

    }

    @Override
    public void onStart() {
        super.onStart();

        // La actividad está a punto de hacerse visible.
        actualiza_puntuacion();
    }


public void actualiza_puntuacion(){
    SharedPreferences mysharedpreferences  = getActivity().getSharedPreferences("MyPREFERENCES", MODE_PRIVATE); //add key
  //  SharedPreferences.Editor prefsEditor = mysharedpreferences .edit();

    scorelab = mysharedpreferences.getInt("USERINFO_SCORE", 0);  //correct


    String puntaje_string=String.valueOf(scorelab);


    puntaje.setText(puntaje_string);
   // evento_boton();// evento boton
    nivel  =mysharedpreferences.getInt("NIVEL_NUMERO",0);

    float monedas_float=scorelab/15;
    int moneditas=Math.round(monedas_float);

    String nivel_string=String.valueOf(nivel);
    String score_text= String.valueOf(scorelab);
    String monedi=String.valueOf(moneditas);

    puntaje.setText(score_text);
    monedastxt.setText(monedi);
    nivel_texto.setText(nivel_string);

     }




    private void eventosBotones() {

        btn_empezarjuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  mp3_selec_sound.start();
                Intent inn =new Intent(getActivity(), IntroSlider.class );
                startActivity(inn);

            }
        });


      btn_continuar_juego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    mp3_selec_sound.start();
                Intent inn =new Intent(getActivity(), IntroSlider.class );
                startActivity(inn);

            }


        });


/*

        btn_puntuar_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("mensaj", "btn puntuar app");


            }


        });

     */



    }

}