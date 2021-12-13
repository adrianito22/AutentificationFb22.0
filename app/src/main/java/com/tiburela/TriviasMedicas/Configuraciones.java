package com.tiburela.TriviasMedicas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class Configuraciones  extends Application {

    Gson gson = new Gson();

    private int numero_items_List=0;

    String response;
    private int valor_indice_Lista;

    String key = "Key";

    List<String> wordsArray;
    List<String> list_indices_modificados;
    SharedPreferences   objeto_sharep;
    SharedPreferences   shref2;
    String randomWord;

    public static  List<String> stringsArray;
    String [] wordsArr;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public   String[] genera_pregunta_opciones(int index_array, Context context) {

          objeto_sharep=context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
         shref2=context.getSharedPreferences("MyPREFERENCESOS", Context.MODE_PRIVATE);



        //recuperamos el del contador de list _indices


        try{  response=shref2.getString(key  , "");
            list_indices_modificados = gson.fromJson(response, new TypeToken<List<String>>(){}.getType());



        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }


        if(shref2.getBoolean("reseteamos",false)){//comprobamos que no hemos reseteado o llegando al tamano maximo del array......//recuperamos..

            //despues cmbiamos este valor a falso y guardamos

            SharedPreferences.Editor editor;
            editor = shref2.edit();
            editor.putBoolean("reseteamos",false);
            editor.apply();

            //rseteamos list....el de abajo se encargara de guardar
            list_indices_modificados.clear();
            Log.i("sopa", "emos reseteado en configuraciones,se ejecuto el if");

            Log.i("sopa", "el valor del indice contador es "+ list_indices_modificados);


        }





//verificando que no de un error..

        if(list_indices_modificados ==null || list_indices_modificados.size()<=0){

            Log.i("sopa", "es nulo o el tamano es <=0");

            Log.i("sixera", "ES NULO");
            list_indices_modificados =new ArrayList<String>(); //posibleente la movamos arri

            for(int indice=0; indice<enviatamanode_array(context); indice++){

                list_indices_modificados.add(String.valueOf(indice)); //anadimos la pregunta 5 espacios mas arriba

            }

            //guardamos preferencias
            SharedPreferences.Editor editor;
            Gson gson2 = new Gson();
            String json = gson2.toJson(list_indices_modificados);
            editor = shref2.edit();
            editor.putString(key, json);
            editor.commit();


        }


        if(wordsArray==null ){ //si e; array es nullo ,

            //si este array es cero entonces guardalo en sahre prefrences
            wordsArr =context.getResources().getStringArray(R.array.paises_espanol);
            wordsArray = new ArrayList<String>(Arrays.asList(wordsArr));

            Log.i("sixer", "onCreate: "+wordsArray.size());

        }



//recuperamos indice de pregunta que respondio mal
       int index_pregunta_nanterior_fail= objeto_sharep.getInt(   "INDEX_NO_CORRECT",0);
        Log.i("tamano", "respondimos mal la pregunta anterior? "+objeto_sharep.getBoolean("respondio_mal",false ));



        if(objeto_sharep.getBoolean("respondio_mal",false)) { //si repondio mal .pregunta anterior

            //recuperamos la pregunta anterior
            valor_indice_Lista= shref2.getInt("valor_indice_Lista",0);



            //anadimos la pregunta 5 espacios mas arriba //index donde colcamos /elemento que colocamos
            list_indices_modificados.add(index_pregunta_nanterior_fail+5,Integer.toString(valor_indice_Lista));//anteriomente era index_pregunta_nanterior_fail


            //guardamos el nuevo objeto en la lista
    SharedPreferences.Editor editor;
    Gson gson2 = new Gson();
    String json = gson2.toJson(list_indices_modificados);

    editor = shref2.edit();
    // editor.remove(key).commit(); //verificarlo mas tarde
    editor.putString(key, json);
    editor.apply();

            Log.i("tamano", "el valor del indice contador es "+ list_indices_modificados);

        }


              //esta parece incesario

             response=shref2.getString(key  , "");
             list_indices_modificados = gson.fromJson(response, new TypeToken<List<String>>(){}.getType());

             valor_indice_Lista=Integer.parseInt(list_indices_modificados.get(index_array));
                  //obtnemos el index
             randomWord = wordsArray.get(valor_indice_Lista); //usamos este indice


            //guardamos el indice de la ultima pregunta y el tamano del list
            SharedPreferences.Editor editor;
            editor = shref2.edit();
            editor.putInt("valor_indice_Lista",valor_indice_Lista);
            editor.putInt("tamanoList",list_indices_modificados.size());
            editor.apply();




        String wordStr = "";
        String pregunta="";
        String respuesta_correcta="";
        String opcion2="";
        String opcion3="";
        String opcion4="";


        Log.i("sixer", "onCreatexa: "+wordsArray.size());


      //  randomWord = wordsArray.get(index_array);


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
