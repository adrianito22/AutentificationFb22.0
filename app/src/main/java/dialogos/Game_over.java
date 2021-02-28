package dialogos;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tiburela.TriviasMedicas.Countdow;
import com.tiburela.TriviasMedicas.R;

public class Game_over extends DialogFragment {

//    Button boton_cerrar;
   // LottieAnimationView lotie;

    TextView resultLabel ; //score level
    TextView totalScoreLabel ; //preguntas adivinadas

    String correcto_answer ;
    String contadorPartida;
    String total_score;
    String concatenardo_score ;

    int correct_answer ;
    int totalscore ;
    int contador_partida;

   Button button5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null ){
            Bundle bundle = getArguments();
            correct_answer = bundle.getInt("RIGHT_ANSWER_COUNT",0);
            totalscore = bundle.getInt("SCORE_PARTIDA",0);
            contador_partida= bundle.getInt("COUNTQUESTION_LOCAL",0);

           correcto_answer = String.valueOf(correct_answer);
           contadorPartida= String.valueOf(contador_partida);
           total_score=String.valueOf(totalscore);
           concatenardo_score =correcto_answer +"/"+contadorPartida;

        }

    }

//respuestas correctas...respuestas

    //puntaje local ,numero de preguntas acertadas/
//numero de respuestas correctas y numero de preguntas realizadas...
    public Game_over() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        return crearDialogote();
    }


    private AlertDialog crearDialogote(){



        //ontextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.fullscreenalert );
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(), R.style.fullscreenalert);//

        //AlertDialog.Builder builder= new AlertDialog.Builder( ctw );

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_game_over, null);
        builder.setView(v);


        totalScoreLabel=v.findViewById(R.id.textView7);
         resultLabel=v.findViewById(R.id.result);
        button5=v.findViewById(R.id.button5);


        //MOSTRAR RESULTADOS E INTENTOS..
        resultLabel.setText(concatenardo_score);
        totalScoreLabel.setText(total_score);


        eventosBotones();

        return builder.create();

    }



    private void eventosBotones() {

       button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getActivity(),Countdow.class );

                startActivity(intent);

            }
        });


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



    public void cerrar_fragmento(){

        dismiss();
    }






}






