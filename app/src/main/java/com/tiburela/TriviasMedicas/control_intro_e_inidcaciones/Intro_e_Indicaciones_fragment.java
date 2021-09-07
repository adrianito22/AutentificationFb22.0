package com.tiburela.TriviasMedicas.control_intro_e_inidcaciones;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tiburela.TriviasMedicas.R;

public class Intro_e_Indicaciones_fragment extends Fragment {
Button boton1;
Button boton2;
Button boton3;



    public static Intro_e_Indicaciones_fragment newInstance(String param1, String param2) {
        Intro_e_Indicaciones_fragment fragment = new Intro_e_Indicaciones_fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public Intro_e_Indicaciones_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       //cargamos una vista dependiendo

               View view = inflater.inflate(R.layout.vista_indicaciones1, container, false);

               boton1= view.findViewById(R.id.boton_siguiente1);
        boton2= view.findViewById(R.id.boton_siguiente1);

        boton3= view.findViewById(R.id.boton_siguiente1);

        eventosBotones();

           return view;



    }




    private void eventosBotones() {
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               //cuando hace click en nextbutton  sale ottro imageview en pantalla y otro boton..
                //e imagview 1 y boton1 se oculta...



            }
        });


        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //cuando hace click en nextbutton  sale ottro imageview en pantalla y otro boton..
                //e imagview 1 y boton1 se oculta...



            }
        });



        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //cuando hace click en nextbutton  sale ottro imageview en pantalla y otro boton..
                //e imagview 1 y boton1 se oculta...



            }
        });


    }


}