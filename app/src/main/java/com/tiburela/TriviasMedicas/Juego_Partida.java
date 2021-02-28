package com.tiburela.TriviasMedicas;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import dialogos.Dialogo_fragmento;
import dialogos.Game_over;
import dialogos.NewLevel_dialogFragment;

public class Juego_Partida extends AppCompatActivity {
    SharedPreferences mysharedpreference ;
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    Button answerBtn;
    private TextView countLabel, questionLabel,lif;
    private String rightAnswer;

    MediaPlayer mp3; //usamos la clase mediaplayer
    MediaPlayer mp3_selec_sound;
    MediaPlayer wroung_sound;
    MediaPlayer time_finish;

    private int rightAnswerCount ;
    private int quizCount = 1;
    private int lifes=5;
    private int numero_preguntas;
    private int answer_incorrect;

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

    private TextView textViewCountDown;//
    static final private int QUIZ_COUNT = 5;

   Dialogo_fragmento dialogo_fragmento = new Dialogo_fragmento();
    NewLevel_dialogFragment dfragmentLevel= new NewLevel_dialogFragment();
    Game_over fragmentGame_over= new  Game_over();



    Bundle bundle = new Bundle();
    LottieAnimationView stars_animation;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;
    Animation animation1;
    Animation slide_up;
    Animation animacion;
    int quizCorrect;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    // MATRIZ(ARRAY) BIDIMENSIONAL

    String[][] quizData ={
            // {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {"Que parte del cuerpo mantiene su tamaño desde el nacimiento? ", "Ojos", "Garganta", "Uñas", "Orejas"},
            {"¿Cuanta resolución tiene el ojo humano?", "576 megapíxeles","304 megapíxeles", "98 megapixeles", "99 megapixeles"},
            {" La laringe forma parte del aparato?", " Respiratorio", " Circulatorio", "Boca", "Digestivo"},
            {"Cuantas piezas dentales tiene un adulto?", "32", "24", "28", "31"},
            {" En que parte del cuerpo humano se encuentra la falanges", "Dedos", "Garganta", "Codos", "Piernas"},
            {"¿Cual de los siguientes huesos no corresponden ala columna vertebral?","Tabique","Omóplatos","Atlas","Sacro"},
            {"Cual de las siguientes opciones no es parte del ojo ?", "Yunque","Retina","Pupila","Cornea"},
            {"Que órgano ataca la enfermedad Cirrosis?", "Hígado", "Corazón", "Cerebro", "Pulmones"},
            {"Que parte del cuerpo mantiene su tamaño desde el nacimiento? ", "Ojos", "Garganta", "Uñas", "Orejas"},
            {"¿Cuanta resolución tiene el ojo humano?", "576 megapíxeles","304 megapíxeles", "98 megapixeles", "99 megapixeles"},
            {" La laringe forma parte del aparato?", " Respiratorio", " Circulatorio", "Boca", "Digestivo"},
            {"Cuantas piezas dentales tiene un adulto?", "32", "24", "28", "31"},
            {" En que parte del cuerpo humano se encuentra la falanges", "Dedos", "Garganta", "Codos", "Piernas"},
            {"¿Cual de los siguientes huesos no corresponden ala columna vertebral?","Tabique","Omóplatos","Atlas","Sacro"},
            {" Cual de las siguientes opciones no es parte del ojo ?", "Yunque","Retina","Pupila","Cornea"},
            {" Que órgano ataca la enfermedad Cirrosis?", "Hígado", "Corazón", "Cerebro", "Pulmones"},
            {"Cuántos huesos hay en el cuerpo humano?", "206", "214", "310", "200"},
            {"¿Cómo se llama el músculo  que no podemos mover?", "Musculo liso", "Musculo estriado", "Musculo esesqueletico","Ninguna de las anteriores"},
            {"El esqueleto humano tiene 206 huesos pero al nacer tiene?", "270 y 350 ", "206 y 270", "206","215"},
            {"¿El músculo más fuerte del cuerpo humano es?", "La lengua", "Abdomen", " Cuádricep ", "Tripses"},
            {"¿Cuando el organismo pierde el 1%de agua provoca?","Nerviosismo", "Sensación de sed", "Desmayos", "Alucinaciones"},
            {"Que parte del cuerpo mantiene su tamaño dese el nacimiento? ", "Ojos", "Garganta", "Uñas", "Orejas"},
            {"¿Cuanta resolución tiene el ojo humano?", "576 megapíxeles","304 megapíxeles", "98 megapixeles", "99 megapixeles"},
            {" La laringe forma parte del aparato?", " Respiratorio", " Circulatorio", "Boca", "Digestivo"},
            {"Cuantas piezas dentales tiene el adulto?", "32", "24", "28", "31"},
            {" ¿Que tipo de sangre transportan las arterias no pulmonares? ","Oxigenada", "Sin oxígeno", "De desechos", "Todas las anteriores"},
            {" ¿Cual de las opciones no es componente principal de la sangre?", "Flora","Glóbulos blancos","Globulos rojos","Plaquetas"},
            {" ¿Que función simple cumple el páncreas?", "Producir insulina","Producir Globulos","Producir Flora","Sintetizar Vitaminas"},
            {" Cual del sistema es responsable del 80% del calor que genera el cuerpo  humano?","Sistema circulatorio","Sistema digestivo","Sistema nervioso","Sistema muscular"},
            {" Que elemento indica que el crecimiento de la persona a terminado?", "La línea epifiseal"," La densidad ósea ","El porte","El tamaño"},
            {" ¿Qué función tienen las proteínas? ","Constructivas", "Energéticas"," Reguladora "," Anabolizantes"},
            {" ¿Dónde podemos encontrar proteínas?", "Legumbres","Agua","verdura","Fruta"},
            {" ¿Cuánta sangre tiene un ser humano aproximadamente?", "6 litros","2 litros","20 litros","62 litros"},
            {"Cómo llamamos al latido del corazón?", "Bombeo","propulsión","Latido","Impulso Cardiaco"},
            {"A qué aparato pertenece la Tráquea?", "Respiratorio","Digestivo","Circulatorio","Excretor"},
            {"Qué aparato se encarga de recoger oxígeno?","Respiratorio","Digestivo","Circulatorio","Excretor"},
            {"¿Cuál de estas es una enfermedad mental relacionada con la nutrición?"," Anorexia","Escorbuto","Raquitismo","Obesidad"},
            {"¿A qué aparato pertenecen las venas ?","Circulatorio","Respiratorio","Digestivo","Escretor"},
            {"¿Cual de las siguientes  proteínas le brinda ala piel su impermeabilidad?","Queratina","Elastina","Fibrinógeno","Globina"},
            {"¿Cuantos sistemas tiene el cuerpo humano?","10","4","14","13"},
            {"¿Que hormona esta esta relaciona con el ciclo diurno o reloj biológico?","Melatonina","Adrenalina","Testosterona","Serotonina"},
            {"¿Cual de los siguientes órganos posee una capa muscular capaz de contraerse?","Estomago","Esófago","Hígado","Cerebro "},
            {"¿Cual de las siguientes opciones esta compuesto por tejido conectivo?","La sangre","La epidermis ","Cerebro","Musculos"},
            {"¿Cual es el órgano interno más grande del cuerpo humano ?","Estómago","Hígado","Cerebro","Pulmones"},
            {"¿Cual es el peso de la piel de un adulto promedio?","3 libras","8 libras","10 libras","5 libras"},
            {"¿Cual es el músculo más grande del cuerpo humano?","Glúteo máximo ","Bíceps ","Cuádriceps ","Dorsal ancho"},
            {"¿Qué nos diferencia a los humanos del resto de especies?","pensar y hablar","Comer","Jugar","Beber"},
            {"¿En qué tres partes principales se divide el cuerpo humano?","Cabeza, tronco y extremidades","Extremidades , cuello y cabeza","Todas las anteriores","Tronco, cabeza y cuerpo"},
            {"¿Cuál es el órgano más largo del cuerpo humano?","La piel","Intestinos ","Pulmón ","Esófago"},
            {"Cuál es el elemento que forma parte del cuerpo humano?","Fósforo ","Carbón ","Nitrato ","Sulfato"},
            {"¿Cuál es la glándula más grande del cuerpo humano?","El hígado ","Glándula mamaria ","Glándula pineal ","Glándula sebácea "},
            {"Cuáles son los grupos sanguíneos según el sistema AB0?","A,B,AB Y O","AB,BO","OB","OA"},
            {"¿Cuál es el grupo sanguíneo más raro y escaso?","AB","O","A+","O+"},
            {"¿Cuál es el receptor universal de sangre?","AB+","B+","A+","O"},
            {"¿Cuál es el grupo sanguíneo donante universal de sangre?","O","A+","AB+","B+"},
            {"¿Cuál es el peso medio de un cerebro humano?","1.36kg","1.40kg","1.35kg","1.45kg"},
            {"¿Cuántas  cámaras que tiene el corazón?","4","2","6","7"},
            {"¿Cuánto pesa el corazón De un deportista ?","500g","300g","200g","400g"},
            {"¿Cuál es el músculo más largo del cuerpo humano?","Sartorio","Esquelético ","fémur ","Cardiaco "},
            {"Cuántos músculos hay en el cuerpo humano?","640 musculos","641 musculos","400 musculos","600 musculos"},
            {"Cuántos músculos necesitamos para enfadarnos ?","43","18","40","19 musculos"},
            {"De qué están compuestas nuestras uñas?","Queratina ","Proteína","Fósforo ","Azufre"},
            {"En que parte del cuerpo  se  encuentra  el  nombre más largo  del músculo ?","Cuello","Pierna","cabeza","Tronco"},
            {"¿Cuántos huesos hay en el cuerpo humano?","206 huesos","300 huesos","203 huesos","100 huesos"},
            {"¿Cuál es el hueso más pequeño del cuerpo humano?","Estribo","Yunque","Meñique","Martillo "},
            {"En qué parte del cuerpo está el mayor número de huesos?","Manos","Pies","cabeza","Oidos"},
            {"¿Qué es el ADN?","Siglas Desoxirribonucleico","Proteinas","Todas las anteriores ","Enfermedad "},
            {"Dónde está la glándula pituitaria?","Cerebro ","Corazon","Estómago ","Higado"},
            {"¿Cuántos músculos necesitamos para sonreír?","17 musculos","18 musculos","11 musculos","19 musculos"},
    };
//72-5  67

//110-115 posible bug


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp3_selec_sound  =MediaPlayer.create(this, R.raw.selec_sound);
      AnimationUtils.loadAnimation( this, R.anim.bounce_dos);
        AnimationUtils.loadAnimation( this, R.anim.slide_up);
        AnimationUtils.loadAnimation( this, R.anim.animacion);



        slide_up= AnimationUtils.loadAnimation( this, R.anim.slide_up);
        animation1=   AnimationUtils.loadAnimation( this, R.anim.bounce_dos);
        animacion = AnimationUtils.loadAnimation( this, R.anim.animacion);

        setContentView(R.layout.activity_juego_partida);
        countLabel = findViewById(R.id.textView4);
        questionLabel = findViewById(R.id.textView3);
        answerBtn1 = findViewById(R.id.button3);
        answerBtn2 = findViewById(R.id.button22);
        answerBtn3 = findViewById(R.id.button11);
        answerBtn4 = findViewById(R.id.button4);
        lif=findViewById(R.id.lifes);
        textViewCountDown = findViewById(R.id.text_view_countdown);

        // SharedPreferences.Editor editor = sharedpreferences.edit();

// Receive quizCategory from StartActivity.
        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);
        Log.v("CATEGORY", quizCategory + "");


        // Create quizArray from quizData.
        for (int i = 0; i < quizData.length; i++) {

            // Prepare array.
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); // Country
            tmpArray.add(quizData[i][1]); // Right Answer
            tmpArray.add(quizData[i][2]); // Choice1
            tmpArray.add(quizData[i][3]); // Choice2
            tmpArray.add(quizData[i][4]); // Choice3
         // tmpArray.add(quizData[i][5]); //texto de respuesta con explicacion
            // Add tmpArray to quizArray.
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {
      //crear sharePreferences
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        int valor_sharep_indice  =sharedpreferences.getInt("HOLA",0);


preguntas_partida++;


        {



        }

                answerBtn1.startAnimation(slide_up);
        answerBtn2.startAnimation(slide_up);
                answerBtn4.startAnimation(slide_up);
                answerBtn3.startAnimation(slide_up);
        answerBtn2.startAnimation(animacion);


        // Update quizCountLabel.
        countLabel.setText("20/" + quizCount);


        // Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(valor_sharep_indice);//
        // Set question and right answer.
        // Array format: {"question", "Right Answer", "Choice1", "Choice2", "Choice3"}

        valor_sharep_indice++;

      if(   valor_sharep_indice ==68){
          valor_sharep_indice=0;
      }






        // ACTUALIZA VALOR DE SHAREPREFRENCES

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("HOLA", valor_sharep_indice);
        editor.apply();




        questionLabel.setText(quiz.get(0));
          questionLabel .startAnimation(animacion);

        pregunta=questionLabel.getText().toString();
     // opcion 2  pregunta=quiz.get(0);

        timeLeftInMillis= COUNTDOWN_IN_MILLIS;

        startCountDown();

         rightAnswer = quiz.get(1);
         respuesta_correct=rightAnswer;
         resetBackgtound();


        //aqui variable de respuesta explicacion.

      //  answer_explain = quiz.get(5);//Explicacion no va ahora


        // Remove "Country" from quiz and Shuffle choices.///estas son las opciones o boton
      //quiz.remove(4);  //esta creo que es opcion..

        quiz.remove(0);

        Collections.shuffle(quiz);

        // Set choices.
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        // Remove this quiz from quizArray.// quizArray.remove(randomNum);

    }


    public void marcaPregunta(View view ){

  //    mp3_selec_sound  =MediaPlayer.create(this, R.raw.selec_sound);
mp3_selec_sound.start();
 answerBtn= findViewById(view.getId());
    //  Button answerBtn = findViewById(view.getId());
     // answerBtn.startAnimation(animation1);
         btnText = answerBtn.getText().toString();


if( selecbutton==false)
{

selecbutton=true;
    switch(view.getId())
    {

        case R.id.button4:
            answerBtn4.setBackgroundResource(R.drawable.a);

            answerBtn4.setTextColor(Color.parseColor("#ffffff"));
             // answerBtn4.startAnimation(animation1);

            break;
        case R.id.button22:
            answerBtn2.setBackgroundResource(R.drawable.b);
            answerBtn2.setTextColor(Color.parseColor("#ffffff"));


            break;
        case R.id.button3:
            answerBtn1.setBackgroundResource(R.drawable.c);
            answerBtn1.setTextColor(Color.parseColor("#ffffff"));


            break;
        case R.id.button11:
            answerBtn3.setBackgroundResource(R.drawable.d);
            answerBtn3.setTextColor(Color.parseColor("#ffffff"));

            break;
    }




}

    }


    public void checkAnswer(View view) {
        answerBtn1.clearAnimation();
if(selecbutton==true)
{

        if (btnText.equals(rightAnswer)) {

            mp3=MediaPlayer.create(this, R.raw.correct_answer);
            mp3.start(); //reproduce sonido del objeto
            respuestacorrecta=true;
            texto_respuesta1="Respuesta incorrecta" ;
            texto_respuesta2="La respuesta correcta es" ;


            countDownTimer.cancel();




            // almacena valor del puntaje para luego recuperarlo  en home activity
            /////////////////////////////////////////////////////////////////////////////////
            SharedPreferences mysharedpreferences = getSharedPreferences("MyPREFERENCESS", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = mysharedpreferences.edit();
        ////////////////////////////////////////////////////////////////////////////////////


            quizCorrect  =mysharedpreferences.getInt("QUIZ_CORRECT",0);
            score =mysharedpreferences.getInt("USERINFO_SCORE",0);


            repuesta_correct_partida++;
            quizCorrect++;
            score= score+25;
            score_local=score_local+25;
//////////////////////////////////////////
         //   prefsEditor.putInt("userinfo_score", score);
        //    prefsEditor.commit();


            //  prefsEditor.putInt("userinfo_score", score);
        //   prefsEditor.commit(); //REANUDAR POSIBLEMENTE
            ///////////////////////////////////////////////////
            // ACTUALIZA VALOR DE SHAREPREFRENCES

            editor.putInt("QUIZ_CORRECT",quizCorrect);
            editor.apply();
            editor.putInt("USERINFO_SCORE",score); //esta reanudar posiblemnte
            editor.apply();



         //   editor.putInt("SCORE_PARTIDA",score_local);
           // editor.apply();

            // compueba si es multiplo de 5


     int resto= quizCorrect % 4;




            if (resto==0){

                nivel  =mysharedpreferences.getInt("NIVEL_NUMERO",0);

    nivel++;

                editor.putInt("NIVEL_NUMERO",nivel);
                editor.apply();

  //  editor.putInt("HOLAS",quizCorrect);
  //  editor.apply();

                Toast.makeText(this, "nivel numero es "+nivel, Toast.LENGTH_SHORT).show();


    eviadata_abrefragment_level();

}

            else
                {
    envidata_yhabrefragment();
}

        } else {

            respuestacorrecta=false;
            countDownTimer.cancel();
            mysharedpreference = getSharedPreferences("MyPREFERENCESS", Context.MODE_PRIVATE);
            lifes  = mysharedpreference.getInt("life",5);
            lifes--;
            liftext=String.valueOf(lifes);
            lif.setText(liftext);


            // ACTUALIZA VALOR DE SHAREPREFRENCES
            SharedPreferences.Editor editor = mysharedpreference.edit();
            editor.putInt("life",lifes);
            editor.apply();

          wroung_sound  =MediaPlayer.create(this, R.raw.wroung_answer);
        wroung_sound.start(); //reproduce sonido del objeto

        envidata_yhabrefragment();

         answer_incorrect++;


        }

        if(lifes==0){

            lifes=5;



            SharedPreferences.Editor editor = mysharedpreference.edit();
            editor.putInt("life",lifes);
            editor.apply();


            eviadata_abrefragment_gameover();
            repuesta_correct_partida=0;
            score_local =0;
            preguntas_partida=0;
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
        answerBtn1.setBackgroundResource(R.mipmap.boton_c);
        answerBtn2.setBackgroundResource(R.mipmap.boton_b);
        answerBtn3.setBackgroundResource(R.mipmap.boton_d);
        answerBtn4.setBackgroundResource(R.mipmap.boton_a);
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
                timefinish_sound();
                updateCountDownText();
               // checkAnswer(); //noprobado todavia
               }





            }.start();
        }
            private void updateCountDownText() {
                int minutes = (int) (timeLeftInMillis / 1000) / 60;
                int seconds = (int) (timeLeftInMillis / 1000) % 60;
                String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                textViewCountDown.setText(timeFormatted);

                if (timeLeftInMillis < 10000) {
                    textViewCountDown.setTextColor(Color.RED);

                }
          if(timeLeftInMillis==0  ){  showNextQuiz();            }


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
                wroung_sound.start();
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
    }


//siguiente pregunta y cierra fragment
    public void fragmentol(View vistaotl) {
     // answerBtn.clearAnimation();
        selecbutton=false;
        showNextQuiz();
        FragmentManager prev = getSupportFragmentManager();
        Dialogo_fragmento previ = (Dialogo_fragmento) prev.findFragmentByTag("image dialog");
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



    public void envidata_yhabrefragment(){
    bundle.putInt("SCORES", score); //actualizacion aqui..
    bundle.putString("CORRECT_ANSWER",   respuesta_correct );
    bundle.putString("PREGUNTA",   pregunta );
  bundle.putBoolean("BOLEAN_VALUE",respuestacorrecta);
    // bundle.putString("TEXT",   rightAnswer );

    dialogo_fragmento.setArguments(bundle);
    dialogo_fragmento.show(getSupportFragmentManager(),"image dialog");          }


public void eviadata_abrefragment_level(){
        bundle.putInt("NIVEL", nivel);
        bundle.putInt("SCORE", score);

    dfragmentLevel.setArguments(bundle);
    dfragmentLevel.show(getSupportFragmentManager(),"Fragment");



   }

    public void eviadata_abrefragment_gameover(){

        bundle.putInt("SCORE_PARTIDA",score_local);
        bundle.putInt("COUNTQUESTION_LOCAL", preguntas_partida);
        bundle.putInt("RIGHT_ANSWER_COUNT",repuesta_correct_partida );

        fragmentGame_over.setArguments(bundle);
        fragmentGame_over.show(getSupportFragmentManager(),"Fragmentgame_over");

    }

    public void timefinish_sound() {
        time_finish = MediaPlayer.create(this, R.raw.time_finish);
        time_finish.start(); //reproduce sonido del objeto

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

public void unouno (View vistar) {

    CallbackManager callbackManager;

    //   PackageManager pm= getActivity().getPackageManager();


    callbackManager = CallbackManager.Factory.create();


    //  enviaMensajeWhatsApp("Mi mensaje es abcdef 1234567890");
    ShareLinkContent content = new ShareLinkContent.Builder()
            .setContentUrl(Uri.parse("https://www.facebook.com/452022598534168/posts/1197683830634704/?app=fbl"))
            .build();



        }




}