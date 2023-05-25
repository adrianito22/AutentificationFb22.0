package com.tiburela.TriviasMedicas.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.tiburela.TriviasMedicas.R;

public class Items_targetas extends AppCompatActivity {
static int indice_contador=0;

 ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    ImageView[] items_imagenes;


   public int items_drawables[]={
           R.drawable.tiburon_enojado2,
           R.drawable.tiburon_enojado2,
           R.drawable.tiburon_enojado2,
           R.drawable.tiburon_enojado2,
           R.drawable.tiburon_enojado2,
           R.drawable.tiburon_enojado2,
           R.drawable.tiburon_enojado2,
           R.drawable.tiburon_enojado2,


   };




   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_tarjetas);

               img1= findViewById(R.id.img1);
               img2= findViewById(R.id.img2);
               img3= findViewById(R.id.img3);
               img4= findViewById(R.id.img4);
               img5= findViewById(R.id.img5);
               img6= findViewById(R.id.img6);
               img7= findViewById(R.id.img7);
               img8= findViewById(R.id.img8);
               img9= findViewById(R.id.img9);

                   genera_index_array(Items_targetas.this);
                   desbloquea_Nuevo_item(Items_targetas.this);

    }




public void desbloquea_Nuevo_item( Context mContext){  //metodo que muestra nuevo item...

       items_imagenes  = new ImageView[]{
            img1 = findViewById(R.id.img1),
            img2 = findViewById(R.id.img2),
            img3 = findViewById(R.id.img3),
            img4 = findViewById(R.id.img4),
            img5 = findViewById(R.id.img5),
            img6 = findViewById(R.id.img6),
            img7 = findViewById(R.id.img7),
            img8 = findViewById(R.id.img8),
            img9 = findViewById(R.id.img9),

    };

       //obtenemos el valor de share.
       SharedPreferences obj_prefe = mContext.getSharedPreferences("ITEMS_ARCHIVO", Context.MODE_PRIVATE);
       indice_contador  =obj_prefe.getInt("index_sin_add",0);
       int img= items_drawables[indice_contador];


       //mostramos imagen
    items_imagenes[indice_contador].setImageResource(img);


}

static public void genera_index_array (Context mContext){ //este metodo sera suado en el activity targetas

    SharedPreferences obj_prefer = mContext.getSharedPreferences("ITEMS_ARCHIVO", Context.MODE_PRIVATE);
    indice_contador  =obj_prefer.getInt("index_imagen",0);
    int indice_c_sinadd= indice_contador;

    // ACTUALIZA VALOR DE SHAREPREFRENCES
    indice_contador++;
    SharedPreferences.Editor editor = obj_prefer.edit();
    editor.putInt("index_imagen", indice_contador);
    editor.putInt("index_sin_add", indice_c_sinadd);

    editor.apply();


   }











}