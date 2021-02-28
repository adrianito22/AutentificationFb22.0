package dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tiburela.TriviasMedicas.Juego_Partida;
import com.tiburela.TriviasMedicas.R;


public class Dialogo_fragmento extends DialogFragment{
    Button boton_compartir;
    TextView txt_pregunta;
    TextView txt_muestra_respue;
    TextView txt_muestra_pregunt;
    Button btn_next;
    //String respuesta;
   TextView txt_muestra_respue2;
    String muestra_respuesta;
    String muestra_pregunta;
    boolean boolean_recibe_respuesta;
    ImageView imageButton4;
    LinearLayout ilayout;

    int muestra_score;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






        if (getArguments() != null ){
            Bundle bundle = getArguments();
            muestra_respuesta = bundle.getString("CORRECT_ANSWER","");
            muestra_pregunta = bundle.getString("PREGUNTA","");
            boolean_recibe_respuesta=bundle.getBoolean("BOLEAN_VALUE",false);

          muestra_score=  bundle.getInt("SCORE",0);


            bundle.putInt("SCORELAB",muestra_score);

            // yourLayout.setBackgroundResource(resid);

            //score= getArguments().getInt("SCORE",0);
            //Toast.makeText(getContext(),"el valor del  SCORE es "+score, Toast.LENGTH_LONG).show();

           // txt_muestra_respue.setText("hola mundo");
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

        // Toast.makeText(getContext(),"el valor del  SCORE es "+score, Toast.LENGTH_LONG).show();//ARRIBA DEL RETURN

        //ontextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.fullscreenalert );
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(), R.style.fullscreenalert);//

        //AlertDialog.Builder builder= new AlertDialog.Builder( ctw );

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo_fragmento, null);
        builder.setView(v);

        // setStyle(DialogFragment., android.R.style.fullscreenalert);
        txt_muestra_pregunt= v.findViewById(R.id.textView9);
        txt_muestra_respue= v.findViewById(R.id.textView8);
        boton_compartir = v.findViewById(R.id.button12);
      //  txt_pregunta=v.findViewById(R.id.txt_mspue);
   //   btn_next=v.findViewById(R.id.button13);
       ilayout=v.findViewById(R.id.mybackground);
     imageButton4=v.findViewById(R.id.imageButton4);
        txt_muestra_respue2=v.findViewById(R.id.respuestas2);
        txt_muestra_pregunt.setText(muestra_pregunta);
texto_Respuesta();

        /*
       btn_next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(),"esto funciona hurras ", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });






       // txt_muestra_respue.setText("hola mundo");
  eventosBotones();

        //AQUI UN TOAST
        //Toast.makeText(getContext(),"el valor del  SCORE es "+score, Toast.LENGTH_LONG).show();//ARRIBA DEL RETURN
*/
        return builder.create();

    }

public void texto_Respuesta(){
  if(boolean_recibe_respuesta ==true)

  { txt_muestra_respue2.setTextColor(Color.parseColor("#008f39"));
      txt_muestra_respue.setText("Respuesta correcta ");
      txt_muestra_respue2.setText("Hurra!");
  }
    else{
      txt_muestra_respue2.setTextColor(Color.parseColor("#fd1532"));
      txt_muestra_respue.setText("La respuesta correcta es: "+muestra_respuesta);
      txt_muestra_respue2.setText( "Opcion incorrecta");

      ilayout.setBackgroundResource(R.mipmap.tib23);
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
}






