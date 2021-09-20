package dialogos;

import android.animation.Animator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.tiburela.TriviasMedicas.R;

import java.util.Timer;
import java.util.TimerTask;

public class Dialogo_fragmento extends DialogFragment {
    TextView txt_muestra_respue,txt_muestra_pregunt;
    Button btn_siguiente_quiz;
    private View rootView;


    TextView txt_muestra_respue2;
    String muestra_respuesta,muestra_pregunta;

    boolean boolean_recibe_respuesta;
    boolean recibe_auto_close=false;
    ImageView ilayout;


    // Activity MainActivity2dos;
    int muestra_score;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dialog2, container, false);
        findViewsByIds();
        texto_Respuesta();

        //   preCargar();
    //    eventos();

        return rootView;

    }



    private void findViewsByIds() {
        txt_muestra_pregunt= rootView.findViewById(R.id.texto_uno);
        txt_muestra_respue= rootView.findViewById(R.id.textView8);

        btn_siguiente_quiz=rootView.findViewById(R.id.btn_siguiente_quiz);
        //  txt_pregunta=v.findViewById(R.id.txt_mspue);
        //   btn_next=v.findViewById(R.id.button13);
        ilayout=rootView.findViewById(R.id.imageView6);
        txt_muestra_respue2=rootView.findViewById(R.id.respuestas2);
        txt_muestra_pregunt.setText(muestra_pregunta);




    }


/*
    private void eventos() {
        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    */


    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.dimAmount = 0;
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER);
        getDialog().getWindow().setAttributes(layoutParams);


        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(android.content.DialogInterface dialog,
                                 int keyCode, android.view.KeyEvent event) {
                if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
                    // To dismiss the fragment when the back-button is pressed.
                    Log.i("verde", "no cerrar");
                    // dismiss();
                    return true;
                }
                // Otherwise, do nothing else
                else
                    return false;
            }
        });


    }


    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.i("verde", "no cerrar");

        // Add you codition
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //   setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme); //posiblemente volver activar


        if (getArguments() != null ){
            Bundle bundle = getArguments();
            muestra_respuesta = bundle.getString("CORRECT_ANSWER","");
            muestra_pregunta = bundle.getString("PREGUNTA","");
            boolean_recibe_respuesta=bundle.getBoolean("BOLEAN_VALUE",false);
            recibe_auto_close=bundle.getBoolean("BOLEAN_VENTANA",false);
            muestra_score=  bundle.getInt("SCORE",0);


            bundle.putInt("SCORELAB",muestra_score);
        }

    }


    public void texto_Respuesta(){
        if(boolean_recibe_respuesta ==true) {

            txt_muestra_respue2.setTextColor(Color.parseColor("#008f39"));
            txt_muestra_respue.setText("Respuesta correcta ");
            txt_muestra_respue2.setText("Hurra!");
            cargar_animacion_monedas2();


            btn_siguiente_quiz.setEnabled(true);

//cuando entra aqui la ventana se cierra..
            //MOSTRAMOS EL DIALOGO AQUI...

            if(recibe_auto_close){ //cerramos automaticamente


                cierra_5segundos_desact_b();


            }



        }
        else{

            txt_muestra_respue2.setTextColor(Color.parseColor("#fd1532"));
            txt_muestra_respue.setText("La respuesta correcta es: "+muestra_respuesta);
            txt_muestra_respue2.setText( "Opcion incorrecta");
            ilayout.setImageDrawable(getResources().getDrawable(R.drawable.tiburon_enojado2));


        }

    }


    public void cerrar_fragmento(){

        dismiss();
    }



    public void cargar_animacion_monedas2() {
        LottieAnimationView coin_collect2;

        coin_collect2 =rootView.findViewById(R.id.coin_collection_anim);

        coin_collect2.playAnimation();


        coin_collect2.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("Animation:", "start");

                coin_collect2.setVisibility(View.VISIBLE);

                Log.i("anim", "animacion empieza");

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("Animation:", "end");
                coin_collect2.setVisibility(View.GONE);

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




    public void cierra_5segundos_desact_b(){

        btn_siguiente_quiz.setEnabled(false);


        //cerramos el dialgo despues de 10 segundos
        new Timer().schedule(new TimerTask() {
            public void run() {
                dismiss();
                Log.d("renose", "se cerro despues de 10 segundos");


                ///vamos a un metodo de la activity  que muestra continuar..ventana que no se puede cerrar.

                dismiss();


            }}, 3000);//t


    }


    @Override
    public int getTheme() {
        return R.style.DialogTheme;
    }




}
