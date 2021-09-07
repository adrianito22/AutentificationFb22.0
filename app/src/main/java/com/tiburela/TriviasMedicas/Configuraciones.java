package com.tiburela.TriviasMedicas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.yaml.snakeyaml.scanner.Constant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class Configuraciones  extends Application {

    Gson gson = new Gson();

    String response;


    String key = "Key";

    List<String> wordsArray;

    String randomWord;

    public static  List<String> stringsArray;
    String [] wordsArr;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public   String[] genera_pregunta_opciones(int index_array, Context context) {

        SharedPreferences   shref2=context.getSharedPreferences("MyPREFERENCESOS", Context.MODE_PRIVATE);


        //recuperamos preferencias
        response=shref2.getString(key  , "");
        wordsArray = gson.fromJson(response,
                new TypeToken<List<String>>(){}.getType());


        Log.i("sixer", "onCreatez: "+wordsArray.size());

        if(wordsArray==null ){ //si e; array es nullo ,

            //si este array es cero entonces guardalo en sahre prefrences
            wordsArr =context.getResources().getStringArray(R.array.paises_espanol);
            wordsArray = new ArrayList<String>(Arrays.asList(wordsArr));

            Log.i("sixer", "onCreate: "+wordsArray.size());


            //guardamos preferencias
            SharedPreferences.Editor editor;
            Gson gson2 = new Gson();
            String json = gson2.toJson(wordsArray);

            editor = shref2.edit();
            // editor.remove(key).commit(); //verificarlo mas tarde
            editor.putString(key, json);
            editor.commit();

        }



        Log.i("sixer", "onCreatex: "+wordsArray.size());



        ///si el usuario no respondio bien l;a pregunta anterior
        Juego_Partida juegoPartida = new Juego_Partida();

         if(juegoPartida.isRespondiomal()){   //si no rersponde bien
         int index_cambiado=  juegoPartida.getIndex_question_no_correct();  // To get

//meliminamos el index actual; y lo cambiamos de posicion


if(index_array+5<wordsArr.length){
    wordsArray.add(index_array+5,wordsArray.get(index_cambiado)); //anadimos la pregunta 5 espacios mas arriba
    wordsArray.remove(wordsArray.get(index_cambiado));  // removemos el index anterior




    //guardamos preferencias
    SharedPreferences.Editor editor;
    Gson gson2 = new Gson();
    String json = gson2.toJson(wordsArray);

    editor = shref2.edit();
    // editor.remove(key).commit(); //verificarlo mas tarde
    editor.putString(key, json);
    editor.commit();







} else{ //si supera el tamano entonces ponla la pregunta al final

    wordsArray.add(wordsArr.length-1,wordsArray.get(index_cambiado)); //anadimos la pregunta al final
    wordsArray.remove(wordsArray.get(index_cambiado));  // removemos el index anterior


    //guardamos preferencias
    SharedPreferences.Editor editor;
    Gson gson2 = new Gson();
    String json = gson2.toJson(wordsArray);

    editor = shref2.edit();
    // editor.remove(key).commit(); //verificarlo mas tarde
    editor.putString(key, json);
    editor.commit();




}

       }



        //si anterior pregunta no la contesto bien ,la movemos y la ponemos 5 index mas ...
        //siempre y cuando el index no supere el tamano del array .example posicion actual 98 ..movemos.5..mas...
        //y saldra un erros...pilas


        String wordStr = "";
        String pregunta="";
        String respuesta_correcta="";
        String opcion2="";
        String opcion3="";
        String opcion4="";


        Log.i("sixer", "onCreatexa: "+wordsArray.size());


        randomWord = wordsArray.get(index_array);


        wordStr = randomWord;

        Configuraciones.stringsArray = new ArrayList<String>();

        if (wordStr.contains(".")) {

            String[] one = wordStr.split(Pattern.quote("."));
            for (String item : one) {
                Configuraciones.stringsArray.add(item);
                String mipais1 = item;

            }
            pregunta= Configuraciones.stringsArray.get(0); //pregunta
            respuesta_correcta = Configuraciones.stringsArray.get(1); //opcion correcta
            opcion2=Configuraciones.stringsArray.get(2); //opcion2
            opcion3=Configuraciones.stringsArray.get(3); //opcion3
            opcion4=Configuraciones.stringsArray.get(4); //opcion4



            Log.i("cuenta letras", "cuenta letras : " );

        }
//muestra pais
        //   paisaqui.setText(Configs. stringsArray.get(0)); //aqui muestrael pais


        return new String [] {pregunta,respuesta_correcta,opcion2,opcion3,opcion4};

    }



   public int  enviatamanode_array ( Context mcontext){
       wordsArr =mcontext.getResources().getStringArray(R.array.paises_espanol);

       int tamanoarray=wordsArr.length;
        return tamanoarray;
   }






}
