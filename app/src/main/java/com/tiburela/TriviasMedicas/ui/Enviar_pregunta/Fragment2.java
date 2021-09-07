package com.tiburela.TriviasMedicas.ui.Enviar_pregunta;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tiburela.TriviasMedicas.R;


public class Fragment2 extends Fragment {

    EditText pregunta;
    EditText edi1;
    EditText edi2;
    EditText edi3;
    EditText edi4;

    Button boton_upload;
    String texto_ultimate;
    LinearLayout linearLayout;
    String dificultad;

    Button bt ;


    FirebaseDatabase database = FirebaseDatabase.getInstance();



    private DatabaseReference mdatabaseC;

    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, container, false);


        // Inflate the layout for this fragment
     //   return inflater.inflate(R.layout.fragment_2, container, false);
       // FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        pregunta =(EditText)view.findViewById(R.id.editTextTextPersonName2);//esta pregunta
        edi1=(EditText)view.findViewById(R.id.editTextTextPersonName); //correct opcion
        edi2= (EditText) view.findViewById(R.id.gdfg4); // opcion2
        edi3= (EditText) view.findViewById(R.id.gdfg3);  //opcion3
        edi4= (EditText) view.findViewById(R.id.editTextTextPersonName2); //opcion4

     // mdatabaseC= FirebaseDatabase.getInstance().getReference("Preguntas");
       linearLayout= view.findViewById(R.id.ly);
        boton_upload=view.findViewById(R.id.mibutton);



           //     mdatabaseC    .setValue("Hellodfksjfklj");

        boton_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     registrar_questions();
                comprobar_Espacios_vacios_y_envia();
            }
        });





        return view;

    }


    public void crearTexto_formatedado () {// toma el

        String question = pregunta.getText().toString(); //pregunta esta

        String correct_opcion = edi1.getText().toString(); //opcion correcta1
        String opcion2 = edi2.getText().toString();  //opcion 2
        String opcion3 = edi3.getText().toString(); //opcion 3
        String opcion4 = edi4.getText().toString(); //opcion 4

        texto_ultimate = "{" + (char) 34 + question + (char) 34 + (char) 44 + (char) 34 + correct_opcion + (char) 34 + (char) 44 + (char) 34 + opcion2 + (char) 34 + (char) 44 + (char) 34 + opcion3 + (char) 34 + (char) 44 + (char) 34 + opcion4 + (char) 34 + (char) 125 + (char) 44;

    }



   public void  comprobar_Espacios_vacios_y_envia(){

    if (("".equals(pregunta.getText().toString().trim()) || "".equals(edi1.getText().toString().trim()) || "".equals(edi2.getText().toString().trim()) || "".equals(edi3.getText().toString().trim()) || "".equals(edi4.getText().toString().trim())))
    {
        Toast.makeText(getContext(), "Uno o mas campos estan  vacios", Toast.LENGTH_LONG).show();

      //  boolean edi_lleno = true;


          }

    else{
        crearTexto_formatedado();

        mdatabaseC = database.getReference("Preguntas");

        String id= mdatabaseC.push().getKey();
        //  mdatabaseC  = database .getReference();
        //     Envio_Preguntas  preguntar=new Envio_Preguntas (id,texto_ultimate,dificultad);// ESTO PERFECTO PaRA USUARIO UNICO QUE
        //que edite su propia info,y cree una cada vex creo.. no se aun.

        //      mdatabaseC  .setValue(preguntar);
        mdatabaseC.push().setValue(texto_ultimate);
        Toast.makeText(getContext(), "La pregunta se envio exitosamente", Toast.LENGTH_SHORT).show();
    }

    }

}