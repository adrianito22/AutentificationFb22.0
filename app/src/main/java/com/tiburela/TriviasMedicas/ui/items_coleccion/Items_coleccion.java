package com.tiburela.TriviasMedicas.ui.items_coleccion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tiburela.TriviasMedicas.Activities.Juego_Partida;
import com.tiburela.TriviasMedicas.R;

public class Items_coleccion extends Fragment {

    static int indice_contador=0;

    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    ImageView[] items_imagenes;



    public int[] items_drawables ={
            R.drawable.item_bandita,
            R.drawable.item_botiquin,
            R.drawable.item_estetoscopio,
            R.drawable.item_inyeccion,
            R.drawable.item_microscopio,
            R.drawable.item_pastilla_redonda,
            R.drawable.item_pildora,
            R.drawable.item_probeta2,
            R.drawable.item_probeta3,



    };



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Items_coleccion() {
        // Required empty public constructor
    }

    
    public static Items_coleccion newInstance(String param1, String param2) {
        Items_coleccion fragment = new Items_coleccion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_tarjetas, container, false);

        img1= view.findViewById(R.id.img1);
        img2= view.findViewById(R.id.img2);
        img3=view. findViewById(R.id.img3);
        img4=view. findViewById(R.id.img4);
        img5=view. findViewById(R.id.img5);
        img6= view.findViewById(R.id.img6);
        img7= view.findViewById(R.id.img7);
        img8= view.findViewById(R.id.img8);
        img9=view. findViewById(R.id.img9);





        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        genera_index_array(getActivity());
        desbloquea_Nuevo_item(getActivity());

    }



    @SuppressLint("UseCompatLoadingForDrawables")
    public void desbloquea_Nuevo_item(Context mContext){  //metodo que muestra nuevo item...

        items_imagenes = new ImageView[9];
        items_imagenes[0]=img1;
        items_imagenes[1]=img2;
        items_imagenes[2]=img3;
        items_imagenes[3]=img4;
        items_imagenes[4]=img5;
        items_imagenes[5]=img6;
        items_imagenes[6]=img7;
        items_imagenes[7]=img8;
        items_imagenes[8]=img9;



        Log.i("debloquea", "numero int: "+items_drawables[2]);


        //obtenemos el valor de share.
        SharedPreferences obj_prefe = mContext.getSharedPreferences("ITEMS_ARCHIVO", Context.MODE_PRIVATE);
        indice_contador  =obj_prefe.getInt("index_imagen",-1); //activar mas tarde

        if(indice_contador>=0){


       for(int indice=0; indice<indice_contador+1 ; indice++ ){

           int img= items_drawables[indice];



           items_imagenes[indice].setImageResource(img);



           //mostramos imagen/s

           Log.d("debloquea", "desbloquea_Nuevo_item: "+ indice);


       }




        }

    }

    
    
    
    

    
    static public void genera_index_array (Context mContext){ //este metodo sera suado en el activity targetas

        SharedPreferences obj_prefer = mContext.getSharedPreferences("ITEMS_ARCHIVO", Context.MODE_PRIVATE);
        indice_contador  =obj_prefer.getInt("index_imagen",-1);

     //   Log.i("debloquea", "numero int: "+items_drawables[2]);


        // ACTUALIZA VALOR DE SHAREPREFRENCES
       //verificar que unicamente aumente si viene de activity juego partida......

        if(mContext instanceof Juego_Partida ){ //si el contexto actual es juego partida
            indice_contador++;

            SharedPreferences.Editor editor = obj_prefer.edit();
            editor.putInt("index_imagen", indice_contador);
            editor.apply();
        }




    } 
    
    
    
}