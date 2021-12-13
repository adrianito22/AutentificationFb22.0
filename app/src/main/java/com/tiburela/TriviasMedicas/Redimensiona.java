package com.tiburela.TriviasMedicas;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

public class Redimensiona {


public static void redimensionatexto(TextView textview,String pregunta,Context context){
    float tamano_screen_width=   determinar_sw(context); //  metodo para dterminar el tamano minimo de la pantalla
    Log.i("cracteres", "el ancho de patalla es "+ tamano_screen_width);

    //dependiendo del tamano de la pantalla minimo

    if(pregunta.length()>=100& tamano_screen_width>=411){
     Log.i("cracteres", "tiene mas de 100 caracteres ,redimensionaqmos");
     textview.setTextSize(16); //probamops con 10
     Log.i("cracteres", "se cambio el texto a "+ pregunta);

 }


}

static void reseteatexto(TextView textview ,Context context) {

    float tamano_screen_width=   determinar_sw(context); //  metodo para dterminar el tamano minimo de la pantalla

   if( tamano_screen_width>=411){
       textview.setTextSize(20);

   }



    }




    public static int dpaPxls(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }


   static  public float determinar_sw(Context context){

        DisplayMetrics dm=context.getResources().getDisplayMetrics();
        float scren_ancho=dm.widthPixels/dm.density;


        return scren_ancho;

    }//DENSIDAD MINIMA






}
