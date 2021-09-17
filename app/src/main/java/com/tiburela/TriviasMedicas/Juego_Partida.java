package com.tiburela.TriviasMedicas;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.tiburela.TriviasMedicas.callbacks.SampleCallback;
import com.tiburela.TriviasMedicas.ui.items_coleccion.Items_coleccion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import dialogos.Dialogo_fragmento;
import dialogos.Game_over_dialog;
import dialogos.Item_desbloqueado;
import dialogos.NewLevel_dialogFragment;

public class Juego_Partida extends AppCompatActivity  implements SampleCallback {
    SharedPreferences mysharedpreference ;

boolean cierra_autom =false;


    LottieAnimationView lotie_coin_collection;

boolean se_presiono50y50bt=true;

    final int NUMERO_ITEMS=9; //EN TOTAL HAY SOLO 9 ITEMS..


    boolean mostramos_item_debloqueado =false;

    SharedPreferences mysharedpreferences;
    ArrayList<String> lista_array;

    int numero_de_items_contador=0;
    final  int NUMERO_OK_RESPUESTAS_ITEMS=10 ; //cada 1 pregunta correctas desbloquea un item .
    int respuestas_correctas_contador=0;


    public  int index_question_no_correct=0; //aqui guardaermos el index de la pregunta no conestada

public  boolean     respondiomal= false;




    private static final long COUNTDOWN_IN_MILLIS = 30000;
    Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;
    Configuraciones config_obj= new Configuraciones();

    int contado_prueba=0;


    String array_recev_preguntas_opcs[];

    Button answerBtn;
    private TextView countLabel, questionLabel,lif;
    private String rightAnswer;
    SoundPool selec_sonico;
    int selecionar_sonido;
    SoundPool sp;
    int sonido_de_Reproduccion;
    TextView monedicas;
    SoundPool sp_correct;
    int sonido_correct;
    SoundPool timefinish;
    int sonido_timefinish;

    float monedas;
    int moneditas;

    private int rightAnswerCount ;
    private int quizCount = 1;
    private int lifes=3;
    private int answer_incorrect;

    private final int NPREGUNTAS=95;// aqui va el numero de preguntas totales..

    String liftext;
    String pregunta;
    String texto_respuesta1;
    String texto_respuesta2;
    String respuesta_correct;
    String btnText;

    boolean respuestacorrecta;
    boolean selecbutton;

    int nivel=1;
    int score=0;
    int preguntas_partida;
    int repuesta_correct_partida;
    int score_local;
String monedica_string;
    private TextView textViewCountDown;//
    static final private int QUIZ_COUNT = 5;

    NewLevel_dialogFragment dfragmentLevel= new NewLevel_dialogFragment(Juego_Partida.getInstance());

    Item_desbloqueado item_desbloqueado_obj= new Item_desbloqueado();



    Game_over_dialog fragmentsi=new Game_over_dialog();

    Bundle bundle = new Bundle();
    LottieAnimationView stars_animation;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;
    Animation animation1;
    Animation slide_up;
    Animation animacion;
    private int quizCorrect;
    TextView monedastxt;
    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    ArrayList<ArrayList<String>> quizArray2 = new ArrayList<>();



    private static Juego_Partida instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        this.instance = this;






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_partida);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       //configuramos la pantalla
     //   getSupportActionBar().hide();



        // Hide Status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AnimationUtils.loadAnimation( this, R.anim.bounce_dos);
        AnimationUtils.loadAnimation( this, R.anim.slide_up);
        AnimationUtils.loadAnimation( this, R.anim.animacion);

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonido_de_Reproduccion = sp.load(this, R.raw.wroung_answer, 1);

        sp_correct = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonido_correct=sp_correct.load(this, R.raw.correct_answer, 1);

        timefinish= new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonido_timefinish =timefinish.load(this, R.raw.time_finish, 1);



        selec_sonico= new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        selecionar_sonido=selec_sonico.load(this, R.raw.selec_sound, 1);



        slide_up= AnimationUtils.loadAnimation( this, R.anim.slide_up);
        animation1=   AnimationUtils.loadAnimation( this, R.anim.bounce_dos);
        animacion = AnimationUtils.loadAnimation( this, R.anim.animacion);



        countLabel = findViewById(R.id.textView4);
        questionLabel = findViewById(R.id.texto_uno);
        answerBtn1 = findViewById(R.id.btn_continuar_juego);
        answerBtn2 = findViewById(R.id.btn_empezarjuego);
        answerBtn3 = findViewById(R.id.btn_puntuar_app);
        answerBtn4 = findViewById(R.id.button4);
        lif=findViewById(R.id.lifes);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        monedastxt=findViewById(R.id.coin);

        lotie_coin_collection=findViewById(R.id.coin_collection_anim);


        // SharedPreferences.Editor editor = sharedpreferences.edit();

// Receive quizCategory from StartActivity.
        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);
        Log.v("CATEGORY", quizCategory + "");


        showNextQuiz();
    }

    public void showNextQuiz() {





        ///CREO QUE HAY QUE BORRAR ESTO
       /*
        answerBtn1.setVisibility(Button.VISIBLE);
        answerBtn2.setVisibility(Button.VISIBLE);
        answerBtn3.setVisibility(Button.VISIBLE);
        answerBtn4.setVisibility(Button.VISIBLE);
*/
        //llamamos la metodo que resetea
        resetea_Ui();

        mostramos_item_debloqueado =false;




        SharedPreferences prfs = getSharedPreferences("VIDAS", Context.MODE_PRIVATE);
        lifes  = prfs.getInt("life",3);
        lif.setText(String.valueOf(lifes));


        //crear sharePreferences
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
       int  valor_sharep_indice  =sharedpreferences.getInt("HOLA",0);

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

        valor_sharep_indice++;

        if(   valor_sharep_indice >= config_obj.enviatamanode_array(getApplicationContext())-1){
            valor_sharep_indice=0;
        }

   //     ArrayList<String> quiz2 = quizArray2.get(contado_prueba);//



        // ACTUALIZA VALOR DE SHAREPREFRENCES
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("HOLA", valor_sharep_indice);
        editor.apply();


        questionLabel.setText(lista_array.get(0));
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


        // Remove "Country" from quiz and Shuffle choices.///estas son las opciones o boton
        //quiz.remove(4);  //esta creo que es opcion..

        lista_array.remove(0);


        // Set choices.
        answerBtn1.setText(lista_array.get(0));
        answerBtn2.setText(lista_array.get(1));
        answerBtn3.setText(lista_array.get(2));
        answerBtn4.setText(lista_array.get(3));

        // Remove this quiz from quizArray.// quizArray.remove(randomNum);

    }


    public void marcaPregunta(View view ){

        selec_sonico.play(selecionar_sonido, 1,1, 1, 0, 0);
        answerBtn= findViewById(view.getId());
        btnText = answerBtn.getText().toString();




            selecbutton=true;
            switch(view.getId())
            {

                case R.id.button4:
                    answerBtn4.setBackgroundResource(R.drawable.shape_lila);
                    answerBtn4.setTextColor(Color.parseColor("#ffffff"));
                    // answerBtn4.startAnimation(animation1);

                    answerBtn1.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn2.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn3.setBackgroundResource(R.drawable.shape_verde);


                 //   answerBtn4.setBackgroundResource(R.mipmap.boton_a);
                 //  answerBtn4.setTextColor(Color.parseColor("#000000"));
                    answerBtn3.setTextColor(Color.parseColor("#000000"));
                    answerBtn2 .setTextColor(Color.parseColor("#000000"));
                    answerBtn1.setTextColor(Color.parseColor("#000000"));









                    break;
                case R.id.btn_empezarjuego:
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
                case R.id.btn_continuar_juego:
                    answerBtn1.setBackgroundResource(R.drawable.shape_lila);
                    answerBtn1.setTextColor(Color.parseColor("#ffffff"));



                  //  answerBtn1.setBackgroundResource(R.mipmap.boton_c);
                       answerBtn2.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn3.setBackgroundResource(R.drawable.shape_verde);


                    answerBtn4.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn4.setTextColor(Color.parseColor("#000000"));
                    answerBtn3.setTextColor(Color.parseColor("#000000"));
                      answerBtn2 .setTextColor(Color.parseColor("#000000"));
                  //  answerBtn1.setTextColor(Color.parseColor("#000000"));







                    break;
                case R.id.btn_puntuar_app:
                    answerBtn3.setBackgroundResource(R.drawable.shape_lila);
                    answerBtn3.setTextColor(Color.parseColor("#ffffff"));




                    answerBtn1.setBackgroundResource(R.drawable.shape_verde);
                       answerBtn2.setBackgroundResource(R.drawable.shape_verde);


                    answerBtn4.setBackgroundResource(R.drawable.shape_verde);
                    answerBtn4.setTextColor(Color.parseColor("#000000"));
                     answerBtn2 .setTextColor(Color.parseColor("#000000"));
                    answerBtn1.setTextColor(Color.parseColor("#000000"));


                    break;
            }



    }





    public void checkAnswer(View view) {
        answerBtn1.clearAnimation();



        if(selecbutton==true)
        {

            if (btnText.equals(rightAnswer)) {

                sp_correct.play(sonido_correct, 1,1, 1, 0, 0);
                 //reproduce sonido del objeto
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
                cargar_animacion();


//comprobamos para desbloquee items

                int resto2=quizCorrect % NUMERO_OK_RESPUESTAS_ITEMS; //VERIFICAMOS EL RESTO
                if (resto2==0){ //SI EL RESTO ES 0 ES MULTIPLO DE NUMERO_OK...


                   numero_de_items_contador =mysharedpreferences.getInt("numero_item",0);

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

                // ACTUALIZA VALOR DE SHAREPREFRENCES
                editor.putInt("life",lifes);
                editor.apply();



                sp.play(sonido_de_Reproduccion, 1,1, 1, 0, 0);


                answer_incorrect++;


                envidata_yhabrefragment();


              //tomamos el index de la respuesta que no contesto bien...para mostrala mas adelante....

                //le pasamos que la respuesta contestada es incorrecta...

           ///     comprobamos que la ultima

       //       getIndex_question_no_correct() ; //asigamos esos valores si reponde mal

                respondiomal=true;
              
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

            Toast.makeText(this, "por favor selecione una opcion  ", Toast.LENGTH_SHORT).show();
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
                timefinish.play(sonido_timefinish, 1,1, 1, 0, 0);
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





    public void esperarTime(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                showNextQuiz();
            }
        }, milisegundos);
    }



    private void pausetimer(){
        countDownTimer.cancel();


    }

    @Override
    public void onStop() {
        super.onStop();
        pausetimer();

        Toast.makeText(getApplicationContext(), "se ejecuta on stop", Toast.LENGTH_SHORT).show();

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



    public void compartirf2(View vista) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "El mejor blog de android http://javaheros.blogspot.pe/");
        intent.setPackage("com.facebook.katana");
        startActivity(intent);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }




    public void destruir_soundpool() {
        if (sp != null)
            sp.release();
    }



public void fianlizaractivity(){

        finish();
}


    public void AudioSoundPool(){
        sp.play(sonido_de_Reproduccion, 1,1, 1, 0, 0);
    }



    public void onBackPressed (){
        Intent inn = new Intent(getApplicationContext(), com.tiburela.TriviasMedicas.MainActivity2dos.class);
        startActivity(inn);


    }


public void cincuentay50(View vista){ //mostramos una opcion correcta e incorrecta...


if(se_presiono50y50bt){


    Toast.makeText(this, "se pulso comodin", Toast.LENGTH_SHORT).show();
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




//getter para obtener el valor de la variable y despues recuperarla em copnfiguraciones class
    public int getIndex_question_no_correct() {
        return index_question_no_correct;
    }


    public boolean isRespondiomal() {
        return respondiomal;
    }






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



   public void resetea_Ui(){

        se_presiono50y50bt=true; //volvemos activar esta opcion.


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


   }


////por qui verificar si la venta se cerro


    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(), "se ejecuta on pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "se ejecuta on resume", Toast.LENGTH_SHORT).show();

       //llamos est metodo y obtenemos su valor..







    }


    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(getApplicationContext(), "se ejecuta on restart", Toast.LENGTH_SHORT).show();

        // MainActivity2dos. isDialogo_cerrado(); //llamos est metodo y obtenemos su valor..




    }


    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(), "se ejecuta on restart", Toast.LENGTH_SHORT).show();

        // MainActivity2dos. isDialogo_cerrado(); //llamos est metodo y obtenemos su valor..

    }


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
      //  eviadata_abrefragment_level();
        //   callback.cuando_cierra();

    }

    ///mostramos el dialog fragment continuar



}