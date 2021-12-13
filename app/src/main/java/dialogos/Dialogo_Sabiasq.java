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

public class Dialogo_Sabiasq extends DialogFragment {
    TextView texto_sabias;
    TextView fuente;

    Button lotengobtn; //cerrar boton
    private View rootView;




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
        rootView = inflater.inflate(R.layout.sabiasq_item, container, false);
        findViewsByIds();
      //  texto_Respuesta();

        //   preCargar();
    //    eventos();

        return rootView;

    }



    private void findViewsByIds() {
        texto_sabias= rootView.findViewById(R.id.texto_sabias);
        fuente= rootView.findViewById(R.id.fuente);

        lotengobtn=rootView.findViewById(R.id.lo_tengobtn);


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


        }

    }





    public void cerrar_fragmento(){

        dismiss();
    }








    @Override
    public int getTheme() {
        return R.style.DialogTheme;
    }




}
