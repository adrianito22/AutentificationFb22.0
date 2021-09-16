package dialogos;

import android.animation.Animator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.tiburela.TriviasMedicas.Juego_Partida;
import com.tiburela.TriviasMedicas.MainActivity;
import com.tiburela.TriviasMedicas.MainActivity2dos;
import com.tiburela.TriviasMedicas.R;


public class Dialogo_fragmento extends DialogFragment{
    TextView txt_muestra_respue,txt_muestra_pregunt;

    TextView txt_muestra_respue2;
    String muestra_respuesta,muestra_pregunta;

    boolean boolean_recibe_respuesta;
    ImageView imageButton4;
    ImageView ilayout;

  LottieAnimationView lotie_coin_collection;

// Activity MainActivity2dos;
    int muestra_score;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme);


        if (getArguments() != null ){
            Bundle bundle = getArguments();
            muestra_respuesta = bundle.getString("CORRECT_ANSWER","");
            muestra_pregunta = bundle.getString("PREGUNTA","");
            boolean_recibe_respuesta=bundle.getBoolean("BOLEAN_VALUE",false);

          muestra_score=  bundle.getInt("SCORE",0);


            bundle.putInt("SCORELAB",muestra_score);
        }

    }



    public Dialogo_fragmento() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        return crearDialogote();
    }


    private AlertDialog crearDialogote(){


        //ontextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.fullscreenalert );
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity() , R.style.FullScreenDialog);//
        //setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme);


        //AlertDialog.Builder builder= new AlertDialog.Builder( ctw );

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialog2, null);
        builder.setView(v);




        // setStyle(DialogFragment., android.R.style.fullscreenalert);
        txt_muestra_pregunt= v.findViewById(R.id.textView3);
        txt_muestra_respue= v.findViewById(R.id.textView8);
      //  txt_pregunta=v.findViewById(R.id.txt_mspue);
   //   btn_next=v.findViewById(R.id.button13);
       ilayout=v.findViewById(R.id.imageView6);
     imageButton4=v.findViewById(R.id.imageButton4);
        txt_muestra_respue2=v.findViewById(R.id.respuestas2);
        txt_muestra_pregunt.setText(muestra_pregunta);


lotie_coin_collection =v.findViewById(R.id.coin_collection_anim);


texto_Respuesta();






       // txt_muestra_respue.setText("hola mundo");
  eventosBotones();

        //AQUI UN TOAST


        return builder.create();

    }












public void texto_Respuesta(){
  if(boolean_recibe_respuesta ==true)

  { txt_muestra_respue2.setTextColor(Color.parseColor("#008f39"));
      txt_muestra_respue.setText("Respuesta correcta ");
      txt_muestra_respue2.setText("Hurra!");
      cargar_animacion_monedas();



      //MOSTRAMOS EL DIALOGO AQUI...


  }
    else{

      txt_muestra_respue2.setTextColor(Color.parseColor("#fd1532"));
      txt_muestra_respue.setText("La respuesta correcta es: "+muestra_respuesta);
      txt_muestra_respue2.setText( "Opcion incorrecta");
      ilayout.setImageDrawable(getResources().getDrawable(R.drawable.tiburon_enojado2));


  }

}


    private void eventosBotones() {

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });


 /*     boton_compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }


        });
 */

    }






    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialogo_fragmento, container, false);
    }



*/

public void asdjkh(View vista) {

    ((Juego_Partida) getActivity()).showNextQuiz();
dismiss();


}
    public void cerrar_fragmento(){

        dismiss();
    }


    public void cargar_animacion_monedas() {
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



}






