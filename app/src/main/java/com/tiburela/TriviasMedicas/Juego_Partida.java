package com.tiburela.TriviasMedicas;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.tiburela.TriviasMedicas.Interface_callbacks.SampleCallback;
import com.tiburela.TriviasMedicas.ui.items_coleccion.Items_coleccion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import dialogos.Dialogo_fragmento;
import dialogos.Game_over_dialog;
import dialogos.Item_desbloqueado;
import dialogos.NewLevel_dialogFragment;

public class Juego_Partida extends AppCompatActivity  implements SampleCallback  {
    //cada 6 preguntas un anuncio...

    private static final String AD_UNIT_ID = "ca-app-pub-3117180621986741/3322088409";
    private static final String TAG = "Juego_Partida";
    private InterstitialAd interstitialAd;

    private int preguntas_partida_Ads=0;
    boolean isPause =false;
    private final int  NUMERO_PREGUNTAS_MIN = 50; //el numero de preguntas mininas que tenemos
    int [] songs;
    ImageView imageButton4;
    boolean cierra_autom =false;
    boolean mostramos_item_debloqueado =false;
    public  boolean     respondiomal= false;
    boolean respuestacorrecta;
    boolean selecbutton;
    boolean se_presiono50y50bt=true;

    int nivel_Juego=1;
  //  LottieAnimationView lotie_coin_collection;
    final int NUMERO_ITEMS=9; //EN TOTAL HAY SOLO 9 ITEMS..//poner aqui el numero de items que existen
    SharedPreferences mysharedpreferences;
    ArrayList<String> lista_array;
    int numero_de_items_contador=0;
    final  int NUMERO_OK_RESPUESTAS_ITEMS=10 ; //cada 1 pregunta correctas desbloquea un item .//ponerle 10
    public  int index_question_no_correct=0; //aqui guardaermos el index de la pregunta no conestada
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;
    Configuraciones config_obj= new Configuraciones();
    int contado_prueba=0;
    String array_recev_preguntas_opcs[];
    Button answerBtn;
    private TextView countLabel, questionLabel,lif;
    private String rightAnswer;
    private int quizCount = 1;
    private int lifes=3;
    String liftext;
    String pregunta;
    String texto_respuesta1;
    String texto_respuesta2;
    String respuesta_correct;
    String btnText;

    int score=0;
    int preguntas_partida;
    int repuesta_correct_partida;
    int score_local;
    private TextView textViewCountDown;//
    NewLevel_dialogFragment dfragmentLevel= new NewLevel_dialogFragment(Juego_Partida.getInstance());

    Item_desbloqueado item_desbloqueado_obj= new Item_desbloqueado();



    Game_over_dialog fragmentsi=new Game_over_dialog();

    Bundle bundle = new Bundle();
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    Animation animation1;
    Animation slide_up;
    Animation animacion;
    private int quizCorrect;
    TextView monedastxt;

    private static Juego_Partida instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_partida);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       //configuramos la pantalla
     //   getSupportActionBar().hide();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

                /*
                List<String> testDeviceIds = Arrays.asList("7B467FCEEBB22C374575734D16D49184");
                RequestConfiguration configuration =
                        new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
                MobileAds.setRequestConfiguration(configuration);
               */



            }
        });


        loadAd(); //cargamo s anuncio



     //   loadAd(); //cargamos anuncio
      //  showInterstitial(); //mostramos anuncio

        this.instance = this;

        // Hide Status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AnimationUtils.loadAnimation( this, R.anim.bounce_dos);
        AnimationUtils.loadAnimation( this, R.anim.slide_up);
        AnimationUtils.loadAnimation( this, R.anim.animacion);
//
//        wroung_answer,correct_answer,time_finish,selec_sound
        songs= new int[] {R.raw.wroung_answer,R.raw.correct_answer,R.raw.time_finish,R.raw.selec_sound,R.raw.swipe};



        slide_up= AnimationUtils.loadAnimation( this, R.anim.slide_up);
        animation1=   AnimationUtils.loadAnimation( this, R.anim.bounce_dos);
        animacion = AnimationUtils.loadAnimation( this, R.anim.animacion);


        countLabel = findViewById(R.id.textView4);
        questionLabel = findViewById(R.id.textView3);


        imageButton4= findViewById(R.id.backbtn);
        answerBtn1 = findViewById(R.id.btn_opcion1);
        answerBtn2 = findViewById(R.id.btn_opcion2);
        answerBtn3 = findViewById(R.id.btn_opcion3);
        answerBtn4 = findViewById(R.id.btn_opcion4);
        lif=findViewById(R.id.lifes);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        monedastxt=findViewById(R.id.coin);
        eventos();

   //     lotie_coin_collection=findViewById(R.id.coin_collection_anim);


        // SharedPreferences.Editor editor = sharedpreferences.edit();

// Receive quizCategory from StartActivity.
        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);
        Log.v("CATEGORY", quizCategory + "");


        showNextQuiz();
    }

    public void showNextQuiz() {


        preguntas_partida_Ads++;


        //  wroung_answer,correct_answer,time_finish,selec_sound,swipe

        reproducir_Sonido(4);

        resetea_Ui();

        mostramos_item_debloqueado =false;




        SharedPreferences prfs = getSharedPreferences("VIDAS", Context.MODE_PRIVATE);
        lifes  = prfs.getInt("life",3);
        lif.setText(String.valueOf(lifes));


        //crear sharePreferences
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        int  valor_sharep_indice  =sharedpreferences.getInt("HOLA",0);
        boolean movio_pregunta=sharedpreferences.getBoolean("movio_pregunta",false);



if(movio_pregunta){ //si movio la pregunta restale1;
    valor_sharep_indice=valor_sharep_indice-1;
}



            //si objetos creados es multiplo de 10...



//BORRAR DESPUES


        contado_prueba++;

        preguntas_partida++;


        answerBtn1.startAnimation(slide_up);
        answerBtn2.startAnimation(slide_up);
        answerBtn4.startAnimation(slide_up);
        answerBtn3.startAnimation(slide_up);
        answerBtn2.startAnimation(animacion);


        // Update quizCountLabel.
        countLabel.setText("Pregunta 20/" + quizCount);



        //desde aquu prueba
        array_recev_preguntas_opcs=config_obj.genera_pregunta_opciones( valor_sharep_indice,this);
        //CREAMOS UNA LISTA Y LE DAMOS UN VALOR

        lista_array = new ArrayList<String>(Arrays.asList(array_recev_preguntas_opcs));
        Log.i("arriba", "v1 "+  lista_array.get(0)); //PREGUNTA
        Log.i("arriba", "v2 "+  lista_array.get(1)); //OPCION 1 /CORRECTA

        Log.i("arriba", "v3 "+  lista_array.get(2)); //OPCION2

        Log.i("arriba", "v4 "+  lista_array.get(3)); //OPCION3

        Log.i("arriba", "v5 "+  lista_array.get(4)); //OPCION4
        //aqui finaliza prueba


        //creamos una lista y copiamos los varlores de lista_array



 index_question_no_correct=valor_sharep_indice;

        Log.i("banano", "el valor de index question no correct es "+ index_question_no_correct);

        Log.i("welcome","viene de pregunta repetida "+sharedpreferences.getBoolean("esrepetida",false));

        if(!sharedpreferences.getBoolean("esrepetida", false)){ //solo si es falso
    valor_sharep_indice++;

}else{
            Log.i("welcome","viene de pregunta repetida,else "+sharedpreferences.getBoolean("esrepetida",false));
        }

         SharedPreferences objeto_sharep=getSharedPreferences("MyPREFERENCESOS", Context.MODE_PRIVATE);


        if(valor_sharep_indice >= NUMERO_PREGUNTAS_MIN && valor_sharep_indice >= objeto_sharep.getInt("tamanoList",0)){
            valor_sharep_indice=0;
            //sinnifica que reseteamo el valor sahrepindice en 0....y debemos hacer algo en configuraciones

            SharedPreferences.Editor editor;
            editor = objeto_sharep.edit();
            editor.putBoolean("reseteamos",true);
            editor.apply();
            Log.i("sopa", "emos reseteado en juego partida");


        }


     /*
        SharedPreferences sharedpreferences2 = getSharedPreferences("MyPREFERENCESOS", Context.MODE_PRIVATE);
        int  objetos_creados_list  =sharedpreferences.getInt("valor_contador",0) % 5;

        if (objetos_creados_list==0){ //si es multiplo de 5 lo que sigue despues del %
            valor_sharep_indice=0;

        }
*/


   //     ArrayList<String> quiz2 = quizArray2.get(contado_prueba);//

        // ACTUALIZA VALOR DE SHAREPREFRENCES
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("HOLA", valor_sharep_indice);
        editor.putInt("INDEX_NO_CORRECT", index_question_no_correct);

        editor.apply();


        questionLabel.setText(lista_array.get(0));

        //redimensionamos texto
        Redimensiona.redimensionatexto(questionLabel, questionLabel.getText().toString(),getApplicationContext());



        questionLabel .startAnimation(animacion);

        pregunta=questionLabel.getText().toString();
        // opcion 2  pregunta=quiz.get(0);

        timeLeftInMillis= COUNTDOWN_IN_MILLIS;

        startCountDown();

        rightAnswer = lista_array.get(1);
        respuesta_correct=rightAnswer;
        resetBackgtound();


        //aqui variable de respuesta explicacion.

        //  answer_explain = quiz.get(5);//Explicacion no va ahora



        lista_array.remove(0);
        // Remove "Country" from quiz and Shuffle choices.///estas son las opciones o boton
        //quiz.remove(4);  //esta creo que es opcion..

        Collections.shuffle(lista_array);

        // Set choices.
        answerBtn1.setText(lista_array.get(0));
        answerBtn2.setText(lista_array.get(1));
        answerBtn3.setText(lista_array.get(2));
        answerBtn4.setText(lista_array.get(3));

        // Remove this quiz from quizArray.// quizArray.remove(randomNum);

    }


    public void marcaPregunta(View view ){

        //reproduce sonido del objeto
        //  wroung_answer,correct_answer,time_finish,selec_sound,swipe

        reproducir_Sonido(3);



        answerBtn= findViewById(view.getId());
        btnText = answerBtn.getText().toString();


            selecbutton=true;
            switch(view.getId())
            {

                case R.id.btn_opcion4:
                    answerBtn4.setBackgroundResource(R.drawable.shape_lila);
                    answerBtn4.setTextColor(Color.parseColor("#ffffff"));
                    // answerBtn4.startAnimation(animation1);

                    answerBtn1.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn2.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn3.setBackgroundResource(R.drawable.shape_verde);


                    answerBtn3.setTextColor(Color.parseColor("#000000"));
                    answerBtn2 .setTextColor(Color.parseColor("#000000"));
                    answerBtn1.setTextColor(Color.parseColor("#000000"));





                    break;
                case R.id.btn_opcion2:
                    answerBtn2.setBackgroundResource(R.drawable.shape_lila);
                    answerBtn2.setTextColor(Color.parseColor("#ffffff"));


                    answerBtn1.setBackgroundResource(R.drawable.shape_verde);
                 //   answerBtn2.setBackgroundResource(R.mipmap.boton_b);
                    answerBtn3.setBackgroundResource(R.drawable.shape_verde);


                    answerBtn4.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn4.setTextColor(Color.parseColor("#000000"));
                    answerBtn3.setTextColor(Color.parseColor("#000000"));
                  //  answerBtn2 .setTextColor(Color.parseColor("#000000"));
                    answerBtn1.setTextColor(Color.parseColor("#000000"));



                    break;
                case R.id.btn_opcion3:


                    answerBtn3.setBackgroundResource(R.drawable.shape_lila);
                    answerBtn3.setTextColor(Color.parseColor("#ffffff"));


                    answerBtn1.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn1.setTextColor(Color.parseColor("#000000"));


                  //  answerBtn1.setBackgroundResource(R.mipmap.boton_c);
                       answerBtn2.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn2 .setTextColor(Color.parseColor("#000000"));



                    answerBtn4.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn4.setTextColor(Color.parseColor("#000000"));
                  //  answerBtn1.setTextColor(Color.parseColor("#000000"));




                    break;
                case R.id.btn_opcion1:

                    answerBtn1.setBackgroundResource(R.drawable.shape_lila);
                    answerBtn1.setTextColor(Color.parseColor("#ffffff"));






                    answerBtn3.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn3.setTextColor(Color.parseColor("#000000"));



                    answerBtn2.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn2 .setTextColor(Color.parseColor("#000000"));


                    answerBtn4.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn4.setTextColor(Color.parseColor("#000000"));


                    break;
            }



    }





    public void checkAnswer(View view) {
        answerBtn1.clearAnimation();


        if(selecbutton==true)
        {

            if (btnText.equals(rightAnswer)) {

                 //reproduce sonido del objeto
                //  wroung_answer,correct_answer,time_finish,selec_sound,swipe

                reproducir_Sonido(1);



                respuestacorrecta=true;
                texto_respuesta1="Respuesta incorrecta" ;
                texto_respuesta2="La respuesta correcta es" ;


                countDownTimer.cancel();


                // almacena valor del puntaje para luego recuperarlo  en home activity
                /////////////////////////////////////////////////////////////////////////////////
                mysharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = mysharedpreferences.edit();
                ////////////////////////////////////////////////////////////////////////////////////

               //oobtenemos el numero de respuestas correctas
                quizCorrect =mysharedpreferences.getInt("respuestas_correctas",0);


                score =mysharedpreferences.getInt("USERINFO_SCORE",0);

                repuesta_correct_partida++;
                quizCorrect++;
                score= score+25;
                score_local=score_local+25;

                respondiomal=false;

                editor.putBoolean("respondio_mal",respondiomal); //esta reanudar posiblemnte

                //actualizamos  el numero de respuestas correctas..
                editor.putInt("respuestas_correctas",quizCorrect); //esta reanudar posiblemnte
                editor.apply();



                editor.putInt("USERINFO_SCORE",score); //esta reanudar posiblemnte
                editor.apply();

                bundle.putInt("SCORETO", score);
////////////////////////// aWUI SCORE MONEDAS

                float monedasis_float=score/15;
                int moneditass=Math.round(monedasis_float);
                String monedis=String.valueOf(moneditass);
                monedastxt.setText(monedis);



                //mostramos animacion monedas
              //  cargar_animacion();


//comprobamos para desbloquee items


                //comprobamos


                int resto2=quizCorrect % NUMERO_OK_RESPUESTAS_ITEMS; //VERIFICAMOS EL RESTO
                if (resto2==0){ //SI EL RESTO ES 0 ES MULTIPLO DE NUMERO_OK...

                    numero_de_items_contador =mysharedpreferences.getInt("numero_item",0);


                    nivel_Juego= numero_de_items_contador;
                    editor.putInt("NIVEL_NUMERO",nivel_Juego); // esto actualiza el nivel



                    Log.i("itemc", "el numero de items contador es "+numero_de_items_contador);

if(numero_de_items_contador<NUMERO_ITEMS){

    //LLAMAMOS EL METODO AQUI

    mostramos_item_debloqueado =true;



}


                }


                if (mostramos_item_debloqueado){
                    //presentamos ventana de nuevo item desbloqueado...

                    eviadata_abredialog_item_debloqueado();

                    numero_de_items_contador++;

                    editor.putInt("numero_item",numero_de_items_contador); //
                    editor.apply();

                    //deblqouemos un nuevo


                    Items_coleccion.genera_index_array(Juego_Partida.this);
                    Toast.makeText(this, "nuevo item desbloqueado", Toast.LENGTH_SHORT).show();

                    //  Items_targetas.genera_index_array(Juego_Partida.this);


                    //reproduciomos animacion

                    //le pasamos  el valor a items targetas o ejecutamos el metodo y le sumamos uno...posiblente usemos el valor de sahre preferences..



                }

                else
                {


//borra esto abrimos fragment


                    MainActivity2dos objdos = new MainActivity2dos(this);
                    objdos.app_launched(this,Juego_Partida.this);

                    Log.d("caer", "el value is "+ objdos.seabrioventana);

if(objdos.seabrioventana){
cierra_autom =true;
    envidata_yhabrefragment(); //abrimos el dialog fragment pero con un valor boleano true;

}else{
    cierra_autom =false;

    envidata_yhabrefragment(); //le enviamos venatan normal


}





///

                    // Log.d("renos", "se cerro despues de 10 segundos ");
                   // Toast.makeText(this, "ok se cerro despues de 10 segundios", Toast.LENGTH_SHORT).show();
                }

            } else {  //si no contesto bien ...


                respuestacorrecta=false;
                countDownTimer.cancel();

                lifes=lifes-1;
                liftext=String.valueOf(lifes);
                lif.setText(liftext);

//escribimos share prefrences
                SharedPreferences preferences = getSharedPreferences("VIDAS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

               SharedPreferences mysharedpreferences2 = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor2 = mysharedpreferences2.edit();
                //////////////////////////////////////////////////////////////////



                // ACTUALIZA VALOR DE SHAREPREFRENCES
                respondiomal=true;
                editor2.putBoolean("respondio_mal",respondiomal); //esta reanudar posiblemnte
                editor2.apply();


                editor.putInt("life",lifes);
                editor.apply();


                //reproduce sonido del objeto
                //  wroung_answer,correct_answer,time_finish,selec_sound,swipe

                reproducir_Sonido(0);



                envidata_yhabrefragment();


              //tomamos el index de la respuesta que no contesto bien...para mostrala mas adelante....

                //le pasamos que la respuesta contestada es incorrecta...

           ///     comprobamos que la ultima

       //       getIndex_question_no_correct() ; //asigamos esos valores si reponde mal


       //       isRespondiomal();
          
          
            }

            if(lifes<=0){


                eviadata_abrefragment_gameover();
                repuesta_correct_partida=0;
                score_local =0;
                preguntas_partida=0;
                lifes=3;

               //creamos otro objeto share pa editar le valor..
                SharedPreferences preferencio = getSharedPreferences("VIDAS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencio.edit();
                editor.putInt("life",lifes);
                editor.apply();
            }

            else

            {

                quizCount++;
                //   esperarTime(2000);
                //    showNextQuiz();


            }

        }
        else{

            Toast.makeText(this, "por favor selecione una opcion", Toast.LENGTH_SHORT).show();
        }





        int resto2=preguntas_partida_Ads % 6; //VERIFICAMOS EL RESTO
        if (resto2==0) { //SI EL RESTO ES 0 ES MULTIPLO DE NUMERO_OK...

            showInterstitial(); //mostramos anuncio

            Log.i("mostrarads","yes si");

            //muestra anuncio aqui..

        }



    }


    private void resetBackgtound() {
        answerBtn1.setBackgroundResource(R.drawable.shape_verde);
        answerBtn2.setBackgroundResource(R.drawable.shape_verde);
        answerBtn3.setBackgroundResource(R.drawable.shape_verde);
        answerBtn4.setBackgroundResource(R.drawable.shape_verde);
        answerBtn4.setTextColor(Color.parseColor("#000000"));
        answerBtn3.setTextColor(Color.parseColor("#000000"));
        answerBtn2 .setTextColor(Color.parseColor("#000000"));
        answerBtn1.setTextColor(Color.parseColor("#000000"));

    }



    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();



            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;

                //  wroung_answer,correct_answer,time_finish,selec_sound,swipe
                reproducir_Sonido(2);

                isPause=false;
               updateCountDownText();
                // checkAnswer(); //noprobado todavia
                 showNextQuiz();
            }



        }.start();
    }
    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d", seconds);
        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);

        }
        else{

            textViewCountDown.setTextColor(Color.parseColor("#000000"));
        }
      //  if(timeLeftInMillis==0  ){
     ///       showNextQuiz();
     //   }


    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }




    @Override
    protected void onPause() {
        super.onPause();

        if(countDownTimer!=null){
            countDownTimer.cancel();
            isPause = true;

              }
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(isPause){
        startCountDown();
        }
    }

    //siguiente pregunta y cierra fragment
    public void fragmentol(View vistaotl) {
        // answerBtn.clearAnimation();
        selecbutton=false;
        showNextQuiz();
        FragmentManager prev = getSupportFragmentManager();
        Dialogo_fragmento previ = (Dialogo_fragmento) prev.findFragmentByTag("image_dialog");
        previ.cerrar_fragmento();

    }


    public void fragmentol2(View vistaot) {
        // answerBtn.clearAnimation();
        selecbutton=false;
        showNextQuiz();
        FragmentManager prev = getSupportFragmentManager();
        NewLevel_dialogFragment previ = (NewLevel_dialogFragment) prev.findFragmentByTag("Fragment");
        previ.cerrar_fragmentos();

    }



    public void cerrar_fragmentYnextquest(View vistaot) {
        // answerBtn.clearAnimation();
        selecbutton=false;
        showNextQuiz();
        FragmentManager prev = getSupportFragmentManager();
        Item_desbloqueado previ = (Item_desbloqueado) prev.findFragmentByTag("fragmento_item");
        previ.cerrar_fragmentos();

    }


    public void envidata_yhabrefragment(){

        Dialogo_fragmento dialogo_fragmento = new Dialogo_fragmento();


        bundle.putInt("SCORES", score); //actualizacion aqui..
        bundle.putString("CORRECT_ANSWER",   respuesta_correct );
        bundle.putString("PREGUNTA",   pregunta );
        bundle.putBoolean("BOLEAN_VALUE",respuestacorrecta);
        // bundle.putString("TEXT",   rightAnswer );
        bundle.putBoolean("BOLEAN_VENTANA",cierra_autom);

        dialogo_fragmento.setArguments(bundle);
        dialogo_fragmento.show(getSupportFragmentManager(),"image_dialog");




    }






    public void eviadata_abrefragment_level(){
      //  bundle.putInt("NIVEL", nivel);
      //  bundle.putInt("SCORE", score);
        dfragmentLevel.setArguments(bundle);
        dfragmentLevel.show(getSupportFragmentManager(),"Fragment");
        Log.i("bebo", "se ejecuto este fragment");

    }



    public void eviadata_abredialog_item_debloqueado(){

        if(item_desbloqueado_obj.isAdded())
        {
            return;
        }

        bundle.putInt("ITEM_INDICE", numero_de_items_contador);
        item_desbloqueado_obj.setArguments(bundle);
        item_desbloqueado_obj.show(getSupportFragmentManager(),"fragmento_item");



    }






    public void eviadata_abrefragment_gameover(){

        bundle.putInt("SCORE_PARTIDA",score_local);
        bundle.putInt("COUNTQUESTION_LOCAL", preguntas_partida);
        bundle.putInt("RIGHT_ANSWER_COUNT",repuesta_correct_partida );

       fragmentsi.setArguments(bundle);
       fragmentsi.show(getSupportFragmentManager(),"Fragmover");

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }







public void fianlizaractivity(){

        finish();
}





    public void onBackPressed (){
        Intent inn = new Intent(getApplicationContext(), com.tiburela.TriviasMedicas.MainActivity2dos.class);
        startActivity(inn);


    }



    private void eventos() {

       ImageView imageButn4;
       imageButn4=findViewById(R.id.imageButn4);


        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); //reactivar mas tarde

            }
        });

        imageButn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // showInterstitial(); //mostramos anuncio

                onBackPressed();

            }
        });



    }






    public void cincuentay50(View vista){ //mostramos una opcion correcta e incorrecta...


if(se_presiono50y50bt){


   // Toast.makeText(this, "se pulso comodin", Toast.LENGTH_SHORT).show();
    ArrayList<Button> buttonlista= new ArrayList<Button>();

    buttonlista.add( answerBtn1);
    buttonlista.add(answerBtn2);
    buttonlista.add( answerBtn3);
    buttonlista.add( answerBtn4);


    answerBtn3.clearAnimation();
    answerBtn1.clearAnimation();
    answerBtn2.clearAnimation();
    answerBtn4.clearAnimation();



    int indice_contrado=-1;

    for(int indice=0; indice<buttonlista.size(); indice++){

        indice_contrado++;
        if( buttonlista.get(indice).getText().toString().equals(rightAnswer)){

            break;
        }

    }


    //generemoa una opcion incorrecta Y OCULTAMOS LAS DEMAS OPCIONES

    buttonlista.remove( indice_contrado);

    Collections.shuffle(buttonlista);
    //  buttonlista.get(0); // mostramos esta..

    // buttonlista.get(1).setVisibility(View.GONE); // ocultamos
    // buttonlista.get(2).setVisibility(View.GONE);



    //ocultamos texto
    buttonlista.get(1).setText(""); // ocultamos
    buttonlista.get(2).setText("");

    //ocultamos dibujable a la izquierda
    buttonlista.get(1).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    buttonlista.get(2).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

    //desabilitamos botones
    buttonlista.get(1).setEnabled(false);
    buttonlista.get(2).setEnabled(false);

    // no puede presionarlo dos veces

    se_presiono50y50bt=false;  //lo activamos en el metodo resetea

}





}









/*
    public void cargar_animacion() {
        lotie_coin_collection.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("Animation:", "start");

                lotie_coin_collection.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("Animation:", "end");
                lotie_coin_collection.setVisibility(View.GONE);

                // img1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e("Animation:", "cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e("Animation:", "repeat");
            }
        });

    }

*/

   public void resetea_Ui(){

       try {
           se_presiono50y50bt=true; //volvemos activar esta opcion.
           //cambiamos el tamano del texto
           Redimensiona.reseteatexto(questionLabel,Juego_Partida.this);



           //vuelve activar botones

           answerBtn1.setEnabled(true);
           answerBtn2.setEnabled(true);
           answerBtn3.setEnabled(true);
           answerBtn4.setEnabled(true);


           //muestra nuevmante el drawable en la izquierda..
           answerBtn1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dentrodeboton_shape, 0, 0, 0);
           answerBtn2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.botonesob, 0, 0, 0);
           answerBtn3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.betunc, 0, 0, 0);
           answerBtn4.setCompoundDrawablesWithIntrinsicBounds(R.drawable.betund, 0, 0, 0);
       } catch (Exception e) {
           e.printStackTrace();
       }



   }


////por qui verificar si la venta se cerro




    public static Juego_Partida getInstance() {
        return instance;
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent.getStringExtra("methodName").equals("showNextQuiz")){
            showNextQuiz();



        }
    }
    @Override
    public void cuando_cierra() {
       showNextQuiz();








    }



    //  wroung_answer,correct_answer,time_finish,selec_sound

    public void reproducir_Sonido(int valor) {
    MediaPlayer mp = MediaPlayer.create(getBaseContext(), songs[valor]);
    mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }});

}



    public void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                AD_UNIT_ID,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        Juego_Partida.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                       // Toast.makeText(Juego_Partida.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        Juego_Partida.this.interstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        Juego_Partida.this.interstitialAd = null;
                                        Log.d("TAG", "The ad failed to show.");
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        Log.d("TAG", "The ad was shown.");
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        interstitialAd = null;

                        String error =
                                String.format("domain: %s, code: %d, message: %s",
                                        loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                    //    Toast.makeText(Juego_Partida.this, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT)
                      //          .show();

                        Log.i("holazz",error);

                    }
                });
    }



    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null) {
            interstitialAd.show(this);
        } else {
         //   Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

}