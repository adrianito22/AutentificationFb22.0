package com.tiburela.TriviasMedicas.ui.Acerca;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiburela.TriviasMedicas.R;

import dialogos.DialogFragment3;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3 extends Fragment {

TextView calificanos;
TextView agradecimientos;
TextView envia_sugerencia;
TextView solicitar_ayuda;
TextView acerca;

    int opcion_elegida;
////////////////////////////////////////
    Bundle bundle = new Bundle();

    DialogFragment3 dialog_fragment3= new DialogFragment3();



/////////////////////////////////////////////////////



    public Fragment3() {
        // Required empty public constructor
    }

    public static Fragment3 newInstance(String param1, String param2) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);




        calificanos= view.findViewById(R.id.calificanostxt);
        agradecimientos= view.findViewById(R.id.agradecimientostx);
        envia_sugerencia= view.findViewById(R.id.enviarsugerencia_txt);
        solicitar_ayuda= view.findViewById(R.id.solicitarayuda_txt);
        acerca=view.findViewById(R.id.acerca);

        eventosBotones();

        return view;
    }




    private void eventosBotones() {

        acerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opcion_elegida=1;
                abredialog_Fragment_yenvia_data();


            }


        });






        calificanos.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
           opcion_elegida=2;
                abredialog_Fragment_yenvia_data();

            }
        });


      agradecimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcion_elegida=3;
                abredialog_Fragment_yenvia_data();
            }


        });


        envia_sugerencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcion_elegida=4;
                abredialog_Fragment_yenvia_data();
            }


        });



        solicitar_ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opcion_elegida=5;
                abredialog_Fragment_yenvia_data();


            }


        });






    }








 //   Bundle bundle = getArguments(); //para recibir datos
   // muestra_respuesta = bundle.getString("CORRECT_ANSWER","");

  public void   abredialog_Fragment_yenvia_data() {

        bundle.putInt("OPCION_SELECIONADA", opcion_elegida);


      dialog_fragment3.setArguments(bundle);
      dialog_fragment3.show(getActivity().getSupportFragmentManager(), "fragment_dialog3");




    }

}