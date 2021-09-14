package dialogos;

import static android.content.ContentValues.TAG;

import android.animation.Animator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.tiburela.TriviasMedicas.R;

public class Item_desbloqueado extends DialogFragment {

    int indice_contador_imagen=0;

    ImageView nuevo_item_imagen;

    LottieAnimationView lotie_winner;



    public int[] items_drawables ={
            R.drawable.item_bandita,
            R.drawable.item_botiquin,
            R.drawable.item_estetoscopio,
            R.drawable.item_inyeccion,
            R.drawable.item_microscopio,
            R.drawable.item_pastilla_redonda,
            R.drawable.item_pildora,
            R.drawable.item_probeta2,
            R.drawable.item_probeta3,



    };







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null ){
            Bundle bundle = getArguments();

          indice_contador_imagen = bundle.getInt("ITEM_INDICE",0);




        }

    }

    public Item_desbloqueado() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        return crearDialogote();
    }


    private AlertDialog crearDialogote(){



        //ontextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.fullscreenalert );
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity() );//lo dejamos no fukll escreen

        //AlertDialog.Builder builder= new AlertDialog.Builder( ctw );

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.item_desbloqueado, null);
        builder.setView(v);

        lotie_winner  = v.findViewById(R.id.coin_collection_anim);
        nuevo_item_imagen=v.findViewById(R.id.nuevo_item_imagen);
        //mostramos nueva imagen..
        int img= items_drawables[indice_contador_imagen];

        Log.i(TAG, "crearDialogote: ");

        nuevo_item_imagen.setImageResource(img);




//////////////////////////////////////////////////////////////////
/////////// Boton compartir FACEBOOK LINK

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


        return builder.create();
    }










    //borra este







    @Override
    public void onAttach (@NonNull Context context){
        super.onAttach(context);

    }






    public void cerrar_fragmentos(){

        dismiss();
    }


}


