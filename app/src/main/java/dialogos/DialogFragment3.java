package dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiburela.TriviasMedicas.Juego_Partida;
import com.tiburela.TriviasMedicas.R;

public class DialogFragment3 extends DialogFragment {

    Button boton_calificar;
    TextView txt_primero,txt_segundo;
    String stringtxt_primero,Stringtxt_segundo;

  ImageView img_cerrar;
int numerorecibido;
LinearLayout ly_chat_app,barra_estrellas;

ImageView tl_b;

ImageView  whatsapp_b,rate_please;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("numero", "el valor del numero es "+ numerorecibido);

        if (getArguments() != null ){
            Bundle bundle = getArguments();

            numerorecibido=  bundle.getInt("OPCION_SELECIONADA",0);
        }


    }



    public DialogFragment3() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        return crearDialogote();
    }


    private AlertDialog crearDialogote(){


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(), R.style.Theme_AppCompat_Light_Dialog_Alert);


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialog3, null);
        builder.setView(v);


       txt_primero= v.findViewById(R.id.textView17);
        txt_segundo= v.findViewById(R.id.textView18);

        img_cerrar=v.findViewById(R.id.img_cerrar);
   //    image_cerrar= v.findViewById(R.id.button12);//reanudar despues
        ly_chat_app=v.findViewById(R.id.ly_chat_app);

        tl_b=v.findViewById(R.id.tl_b);
        barra_estrellas=v.findViewById(R.id.barra_estrellas);
        whatsapp_b=v.findViewById(R.id.whatsapp_b);
        rate_please=v.findViewById(R.id.foto_calificame);
        boton_calificar=v.findViewById(R.id.boton_calificar);


        opcion_selec_exe_code();
        eventosBotones();

    //    eventosBotones();




        return builder.create();

    }







    public void Crea_texto(){



    }


    private void eventosBotones() {

        barra_estrellas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rateApp();



            }
        });



        boton_calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rateApp();




            }
        });



      img_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();





            }
        });




        tl_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mensaje_telgram();

            }
        });



        whatsapp_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mensaje_whatsapp();
                
            }
        });

        
    }
    
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
    
    public void asdjkh(View vista) {

        ((Juego_Partida) getActivity()).showNextQuiz();
        dismiss();


    }
    public void cerrar_fragmento(){

        dismiss();
    }

    public void opcion_selec_exe_code(){



        if(numerorecibido==1){   // aCERCA
            rate_please.setVisibility(ImageView.GONE);
            barra_estrellas.setVisibility(LinearLayout.GONE);
            boton_calificar.setVisibility(Button.GONE);
            txt_primero.setText("ACERCA");
            txt_segundo.setText("Esta es una app educativa ,tiene el fin de ayudar a memorizar conceptos y conocimientos basicos sobre medicina y anatomia humana  .El contenido de nuestra aplicación fue previamente revisado y editado, aun así no podemos decir que el mismo este 100% libre de errores, en caso de encontrar alguno ,pedimos por favor que nos Lo notifiques. Tiburela es una marca joven que busca seriamente desarrollar un espacio importante en la vida académica de estudiantes de diversas partes del mundo. Tiburela esta conformamos todos los que hacemos posible que una nueva app cobre vida ,desde amigos, familares y todos los que an aportado alguna o muchas veces en la creacion de un nuevo producto.");

            txt_segundo.setTextSize(TypedValue.COMPLEX_UNIT_PX, getActivity() .getResources().getDimension(R.dimen.text_little ));
            ly_chat_app.setVisibility(LinearLayout.GONE);

        }

        if(numerorecibido==2){   //CALIFICANOS

            txt_primero.setVisibility(TextView.GONE);
            txt_segundo.setText("Por favor podrias calificar nuestra APP?");
            ly_chat_app.setVisibility(LinearLayout.GONE);
            rate_please.setImageDrawable(getResources().getDrawable(R.drawable.tiburon_puntuacion_please2));


        }

        if(numerorecibido==3){   //AGRADECMIENTOS

            rate_please.setVisibility(ImageView.GONE);
            barra_estrellas.setVisibility(LinearLayout.GONE);
            boton_calificar.setVisibility(Button.GONE);

            txt_primero.setText("AGRADECIMIENTOS");
            txt_segundo.setText("Agradecemos a cada una de las personas que hizo posible el desarrollo de esta app, lamentablemente algunos de los usuarios que contribuyeron de forma indirecta son desconocidos y es poco probable que vean esto ,aun asi gracias por tanto. Agradecemos a: Nina,Junior Rosillo de Rosillolabs, los chicos de Stackoverflow ,Coding with Sara,@altared");
          txt_segundo.setTextSize(TypedValue.COMPLEX_UNIT_PX, getActivity() .getResources().getDimension(R.dimen.text_medium ));
            ly_chat_app.setVisibility(LinearLayout.GONE);

        }
        if(numerorecibido==4){   //ENVIA SUGRENCIAS
            rate_please.setVisibility(ImageView.GONE);
            txt_primero.setText("TIENES UNA IDEA O SUGERENCIA?");
            barra_estrellas.setVisibility(LinearLayout.GONE);
            boton_calificar.setVisibility(Button.GONE);
            txt_segundo.setText("Si tienes una o mas  ideas o sugerencias ,ten en cuenta que nos encantara escucharte ,no dudes en escribirnos por medio de nuestras aplicaciones de mensajeria favoritas");     //txt_segundo.setTextSize(5,5);
            
        }

        if(numerorecibido==5){ //necesitas ayuda
            barra_estrellas.setVisibility(LinearLayout.GONE);
            boton_calificar.setVisibility(Button.GONE);


            rate_please.setVisibility(ImageView.GONE);
            txt_primero.setText("AYUDA");
            txt_segundo.setText(" Anímate a escribirnos a través de nuestras apps de chats favoritas, da click en cualquiera de los dos botones para una consulta personalizada, estaremos encantados en ayudarte y dar solución a tu problema relacionado con nuestra app");

        }

    }
    
    
    
    
    void intentMessageTelegram(String msg)
    {
        final String appName = "org.telegram.messenger";
        final boolean isAppInstalled = isAppAvailable(getContext().getApplicationContext(), appName);
        if (isAppInstalled)
        {
            Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/adrianov222")); //where 1111111111 - telegram userId
            telegram.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            telegram.setPackage("org.telegram.messenger");
            // startActivity(telegram);

            this.startActivity(Intent.createChooser(telegram, "Share with"));



/*
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        myIntent.setPackage(appName);
        myIntent.putExtra(Intent.EXTRA_TEXT, msg);//
        this.startActivity(Intent.createChooser(myIntent, "Share with"));
*/


        }
        else
        {
            Toast.makeText(getContext(), "Telegram not Installed", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isAppAvailable(Context context, String appName)
    {
        PackageManager pm = context.getPackageManager();
        try
        {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public void mensaje_telgram() {

        intentMessageTelegram("Hi ftrytrtyut");
    }


    public void enviadsds(View vista) {

        Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Helloworld_1010")); //where 1111111111 - telegram userId
        telegram.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        telegram.setPackage("org.telegram.messenger");
        startActivity(telegram);

        //esta tambien funciona //ahora no la estoy usando


    }





    void intentMessageWhatsapp(String msg)
    {
        final String appName = "com.whatsapp";
        final boolean isAppInstalled = isAppAvailable(getContext().getApplicationContext(), appName);
        if (isAppInstalled)
        {
            Intent whatsapp = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=5930989208333&text=Hola%20cuantos%20perros%20tienes??")); //where 1111111111 - telegram userId
            whatsapp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            whatsapp.setPackage("com.whatsapp");
            // startActivity(telegram);

            this.startActivity(Intent.createChooser(whatsapp, "Share with"));


/*
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        myIntent.setPackage(appName);
        myIntent.putExtra(Intent.EXTRA_TEXT, msg);//
        this.startActivity(Intent.createChooser(myIntent, "Share with"));
*/


        }
        else
        {
            Toast.makeText(getContext(), "whatsapp no esta instalado", Toast.LENGTH_SHORT).show();
        }
    }



    public static boolean isAppAvailable_whatsapp(Context context, String appName)
    {
        PackageManager pm = context.getPackageManager();
        try
        {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void mensaje_whatsapp() {

        intentMessageWhatsapp("Hi ftrytrtyut");
    }


    ///califca



    public void rateApp()
    {
        try
        {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details?id=com.tiburela.TriviasMedicas&hl");
            startActivity(rateIntent);
        }
    }

    public void   rateapp_in_app(){



    }



    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getActivity().getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }






}






