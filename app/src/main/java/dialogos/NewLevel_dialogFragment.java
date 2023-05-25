package dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tiburela.TriviasMedicas.Activities.Juego_Partida;
import com.tiburela.TriviasMedicas.R;

public class NewLevel_dialogFragment extends DialogFragment {

    Button continuar_btn;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null ){
            Bundle bundle = getArguments();





        }

    }

    public NewLevel_dialogFragment(Juego_Partida instance) {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        return crearDialogote();
    }


    private AlertDialog crearDialogote(){



        //ontextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.fullscreenalert );
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(), R.style.fullscreenalert);

        //  AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(), R.style.fullscreenalert );//anterior asi y salia grande


        //AlertDialog.Builder builder= new AlertDialog.Builder( ctw );

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_new_level, null);
        builder.setView(v);

         continuar_btn =v.findViewById(R.id.boton_continuar);


//////////////////////////////////////////////////////////////////
/////////// Boton compartir FACEBOOK LINK



            continuar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
                ((Juego_Partida)getActivity()).showNextQuiz();


            }
        });

//////////////////////////////////////////////////////////////////

        //  muestra texto..
  //     puntos_text.setText("Puntos: "+puntos_level);
 //      frase_levels.setText("Has llegado al nivel "+numero_level);

        ///Animacion lottie aqui



     // eventosBotones();


        return builder.create();
    }


   /*

    private void eventosBotones() {

          bt_t .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


    }
    */


    //borra este







    @Override
    public void onAttach (@NonNull Context context){
        super.onAttach(context);

    }


/*

    public void enviaMensajeWhatsApp(String msj) {
        //  List<PackageInfo> packs = getActivity().getPackageManager().getInstalledPackages(0);
        //  getActivity().getPackageManager().getInstalledPackages(0);
        PackageManager pm= getActivity().getPackageManager();

        try {
            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            waIntent.setPackage("com.whatsapp");
            waIntent.putExtra(Intent.EXTRA_TEXT, msj);
            startActivity(Intent.createChooser(waIntent, "Compartir con:"));
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getApplicationContext(), "WhatsApp no esta instalado!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

*/




/*
    ShareLinkContent linkcontent = new ShareLinkContent.Builder()
            .setQuote("sdfgdfg")
            .setContentUrl(Uri.parse("https://developers.facebook.com/docs/reference/android/current/class/ShareLinkContent"))
            .build();

*/



    public void cerrar_fragmentos(){

        dismiss();
    }


}


