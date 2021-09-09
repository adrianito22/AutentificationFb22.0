package dialogos;

import android.animation.Animator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;

import com.tiburela.TriviasMedicas.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static android.content.ContentValues.TAG;

public class NewLevel_dialogFragment extends DialogFragment {


    TextView txt1;
    ImageButton btn_salir;
    String textoe;
    Button Continuar;
    String muestra_frase;
    LottieAnimationView lotie_winner;
    LottieAnimationView animate_fiesta;

   TextView puntos_text ;
   TextView  frase_levels  ;


  int puntos_level;
  int numero_level;

    ImageView bt_f;
    ImageView bt_w;
    ImageView bt_t;








    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null ){
            Bundle bundle = getArguments();

          numero_level = bundle.getInt("NIVEL",0);
          puntos_level = bundle.getInt("SCORE",0);



        }

    }

    public NewLevel_dialogFragment() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        return crearDialogote();
    }


    private AlertDialog crearDialogote(){



        //ontextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.fullscreenalert );
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(), R.style.fullscreenalert );//

        //AlertDialog.Builder builder= new AlertDialog.Builder( ctw );

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_new_level, null);
        builder.setView(v);



         frase_levels  =v.findViewById(R.id.textView15);
         Continuar=v.findViewById(R.id.boton_continuar);
         bt_f=v.findViewById(R.id.tl_b);
         bt_t =v.findViewById(R.id.imageView6);
         lotie_winner  = v.findViewById(R.id.coin_collection_anim);

         String leveltext=String.valueOf(numero_level);
         frase_levels.setText("Nivel "+leveltext+" completado");



//////////////////////////////////////////////////////////////////
/////////// Boton compartir FACEBOOK LINK




        bt_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });

//////////////////////////////////////////////////////////////////

        //  muestra texto..
  //     puntos_text.setText("Puntos: "+puntos_level);
 //      frase_levels.setText("Has llegado al nivel "+numero_level);

        ///Animacion lottie aqui

        lotie_winner.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("Animation:","start");

                lotie_winner.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("Animation:","end");
                lotie_winner.setVisibility(View.GONE);

              // img1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e("Animation:","cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e("Animation:","repeat");
            }
        });




        // txt1.setText(muestra_frase);
       eventosBotones();


        return builder.create();
    }


    private void eventosBotones() {

          bt_t .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Create intent using ACTION_VIEW and a normal Twitter url:
                String tweetUrl = String.format("https://twitter.com/intent/tweet?text=%s&url=%s",
                        urlEncode("Tweet text"),
                        urlEncode("https://www.google.fi/"));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));

                // Narrow down to official Twitter app, if available:
                List<ResolveInfo> matches =getActivity().getPackageManager().queryIntentActivities(intent, 0);
                for (ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                        intent.setPackage(info.activityInfo.packageName);
                    }
                }

                startActivity(intent);



            }
        });


    }

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            Log.wtf(TAG, "UTF-8 should always be supported", e);
            throw new RuntimeException("URLEncoder.encode() failed for " + s);
        }
    }


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


