package com.tiburela.TriviasMedicas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import com.tiburela.TriviasMedicas.callbacks.SampleCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import dialogos.Dialogo_fragmento;

public class MainActivity2dos extends AppCompatActivity {
    boolean seabrioventana=false;

    private AppBarConfiguration mAppBarConfiguration;
    Toolbar toolbar;
    private Menu menu;// Global Menu Declaration
     boolean dialogo_cerrado=false;

   public SampleCallback callback;

private final int REPUESTAS_OK_1_DIALOG=2; //minimo de respuestas correctas para mostrar el dialog ra

    Context mcontexto = MainActivity2dos.this;

     Juego_Partida jpobjeto=new Juego_Partida();

     int contador_intentos=0; //este hara que no se vulva a mostra la misma ventana
     int dias_intento3 = 5; //despues de 5 dias desde que le mostre la ultima vez...
     int dias_intento4 = 7; //despues de  7 dias desde que le mostre la ultima vez...
     int dias_intento5 = 10; //despues de  10 dias desde que le mostre la ultima vez...
     int dias_intento2 = 2; //dias  que tienen que pasar para mostra el dialog
     long dia_time_mostre_dialog; //dia cuando mostre la ventana la ultima vez...
     int cuenta_veces_habre_app; //contado dfe veces que se habre la app
    private final  int DAYS_UNTIL_PROMPT = 3; //dias  que tienen que pasar para las proximas notificaciones.....
     boolean mostro_primera_vez_ventan = false;
     boolean aparentemente_puntuo_app = false;

   Context contex_activity= MainActivity2dos.this;









    Button boton_ok_rate;

    public MainActivity2dos() {

    }
    // DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dos);


        boton_ok_rate= findViewById(R.id.boton_ok_rate);






        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar ( toolbar );
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled ( true );
        getSupportActionBar ().setDisplayShowHomeEnabled(true);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(

                R.id.navigation_home, R.id.navigation_dashboard,R.id.navigation_notifications,R.id.navigation_items,R.id.fragment_licencias,R.id.fragment_puntua_app,R.id.politica_privacidad,  R.id.fragment_acerca)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_view);
        NavigationUI.setupWithNavController(bottomNavigation, navController);

        //  NavigationView bnavigationView=(NavigationView) findViewById(R.id.nav_home);
        //  bnavigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));


/*
        // inicializamos drawer layout
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }






    protected boolean openFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
        return true;
    }



    /*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int title;
        switch (menuItem.getItemId()) {
            case R.id.item_munu1:
                title = R.string.item1;
                Toast.makeText(this, "se pulso itemmenu 1", Toast.LENGTH_SHORT).show();
                Log.i("debugeo", "se presiono 1");

                break;
            case R.id.item_munu2:
                Toast.makeText(this, "se pulso itemmenu 2", Toast.LENGTH_SHORT).show();
                Log.i("debugeo", "se presiono 2");

                title = R.string.item2;
                break;
            case R.id.item_munu3:
                Toast.makeText(this, "se pulso itemmenu 3", Toast.LENGTH_SHORT).show();
                Log.i("debugeo", "se presiono 3");

                title = R.string.item3;
                break;
            default:
                throw new IllegalArgumentException("menu option not implemented!!");

        }

     //   drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }




    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    */


//////////copiado desde aqui
public void solicitar_puntuacion3(View vista) {
    ReviewManager manager = ReviewManagerFactory.create(this);
    Log.d("hola", "1");
    com.google.android.play.core.tasks.Task<ReviewInfo> request = manager.requestReviewFlow();
    Log.d("hola", "2");
    request.addOnCompleteListener(task -> {
        if (task.isSuccessful()) {
            // We can get the ReviewInfo object
            Log.i("hola", "la tarea es exitosa ");
            ReviewInfo reviewInfo = task.getResult();
            Task<Void> flow = manager.launchReviewFlow(this, reviewInfo);
            flow.addOnCompleteListener(task2 -> {  //tarea finnalizada

                Log.i("hola", "se presento la ventana");

                // The flow has finished. The API does not indicate whether the user
                // reviewed or not, or even whether the review dialog was shown. Thus, no
                // matter the result, we continue our app flow.
// a terminado de puntuar o se mostro laventa y la cerro y puede seguir
            });
        } else {


            Toast.makeText(this, "Revise su conexion a interente he intente mas tarde", Toast.LENGTH_SHORT).show();
            // There was some problem, log or handle the error code.
        }
    });

}



    public void solicitar_puntuacion(Context context, Activity activity) {
        ReviewManager manager = ReviewManagerFactory.create(context);
        Log.d("hola", "1");
        com.google.android.play.core.tasks.Task<ReviewInfo> request = manager.requestReviewFlow();
        Log.d("hola", "2");
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                Log.i("hola", "la tarea es exitosa ");
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                flow.addOnCompleteListener(task2 -> {  //tarea finnalizada

                    Log.i("hola", "se presento la ventana");

                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
// a terminado de puntuar o se mostro laventa y la cerro y puede seguir
                });
            } else {


                Toast.makeText(context, "Revise su conexion a interente he intente mas tarde", Toast.LENGTH_SHORT).show();
                // There was some problem, log or handle the error code.
            }
        });

    }


///a partir de aqui codigo
public   void solicitar_puntuacion2(Context mContext, Activity activity  ){  //solicta una puntuacion in app
    //     SharedPreferences pref_puntuacion = getSharedPreferences("PUNTUACION_BOOLEAN", Context.MODE_PRIVATE);

    ReviewManager manager = ReviewManagerFactory.create(mContext);
    Log.d("hola", "1");
    Task<ReviewInfo> request = manager.requestReviewFlow();
    Log.d("hola", "2");
    request.addOnCompleteListener(task -> {
        if (task.isSuccessful()) {
            // We can get the ReviewInfo object
            Log.i("hola", "solicitar_puntuacion exitosa ");
            ReviewInfo reviewInfo = task.getResult();
            Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
            flow.addOnCompleteListener(task2 -> {  //tarea finnalizada
                //decidio darle click en el boton puntuar. entonces aparentmente puntuolapp=
                aparentemente_puntuo_app=true;
                //usuario_calific();

                // The flow has finished. The API does not indicate whether the user
                // reviewed or not, or even whether the review dialog was shown. Thus, no
                // matter the result, we continue our app flow.
// a terminado de puntuar o se mostro laventa y la cerro y puede seguir
            });
        } else {

          //  Juego_Partida jpobjeto=new Juego_Partida();
          //  jpobjeto.eviadata_abrefragment_level();
         //   Log.i("hola", " error en  solicitar_puntuacion ");

            // There was some problem, log or handle the error code.

        }
    });

}







    public  void app_launched(Context mContext,Activity activity) {
       seabrioventana=false;
        SharedPreferences mysharedpreferences = mContext.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);



        SharedPreferences prefsa =  mContext.getSharedPreferences("apprater", 0);
        boolean valorcont=prefsa.getBoolean("primera_ventana_mostro", false);
        int valorcontenido= prefsa.getInt("contador_intento",0);

        Log.d("vergaso", "el valor del entero es  "+valorcontenido );
        Log.d("vergaso", "el valor del booleano es "+valorcont );


        Toast.makeText(mContext, "getexct", Toast.LENGTH_SHORT).show();
        //prefencia no mopstrar nuevamente
        SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);
        SharedPreferences.Editor editor = prefs.edit();

        if (prefs.getBoolean("dontshowagain", false)&&  prefs.getInt("contador_intento", 0) >=5 ) { //aparentemente_puntuo_app tambien va..

            return;
        }


        //OBTENEMOS LAS VECS QUE SE ABRIO LA APP
        cuenta_veces_habre_app = prefs.getInt("launch_count", 0); //veces que se abrio la app


        // Incrementa contador de veces que se abrio la app
        cuenta_veces_habre_app++;
        editor.putInt("launch_count", cuenta_veces_habre_app);


        // obtiene la fecha de cuando abrio la app por primera vez y la GUARDA SI no es cero
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);


        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }


        //debugeo zona

        long hora_fechactual = System.currentTimeMillis();
        long fecha_esperadias = date_firstLaunch + (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000);


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String dateString = formatter.format(new Date(hora_fechactual));

        SimpleDateFormat formattero = new SimpleDateFormat("dd/MM/yy");
        String dateStringa = formattero.format(new Date(fecha_esperadias));


        Log.i("la_capital", "la fecha actual  es " + dateString);
        Log.i("la_capital", "la fecha proxima es " + dateStringa);
        Log.i("la_capital", "el valor de launch count es " + cuenta_veces_habre_app);


        //comprobamos que no mostro ventana por primera vez

        mostro_primera_vez_ventan = prefs.getBoolean("primera_ventana_mostro", false);

    //    showRateDialog(mContext, editor,activity); //borrar despues
        Log.i("mocho2","el valor de share es ");


        Log.i("mocho", "el valor de share es  "+ mysharedpreferences.getInt("respuestas_correctas",0));

        if(mysharedpreferences.getInt("respuestas_correctas",0)==REPUESTAS_OK_1_DIALOG){ //MOSTRAMOS EL PRIMER  DIALGO DESPUES DEL NUMERO DE PREGUNTAS ...
          ///intentar y chat a aqui para esta accion



          //There is an active fragment with tag "dialog" and "prev" variable holds a reference to it.



          showRateDialog(mContext, editor,activity);
          mostro_primera_vez_ventan = true;
          contador_intentos=1;
          editor.putBoolean("primera_ventana_mostro", mostro_primera_vez_ventan);
          editor.putInt("contador_intento", contador_intentos);
      }


       /*
        if (System.currentTimeMillis() >= date_firstLaunch + 5000000 && !mostro_primera_vez_ventan) { // y si an pasado 5 sg minutos y aun no se amostrado la ventana ,muestrala

            showRateDialog(mContext, editor,activity);
            mostro_primera_vez_ventan = true;
            contador_intentos=1;
            editor.putBoolean("primera_ventana_mostro", mostro_primera_vez_ventan);
            editor.putInt("contador_intento", contador_intentos);
            Log.i("ifuno", "se ejecuto el primer if ");


        }*/


        //esta es el segundo intento de mostrar ventana despues de 2 dias
        else if (System.currentTimeMillis() >= date_firstLaunch +   //proxima vez que se mostrara el dialog
                (dias_intento2 * 24 * 60 * 60 * 1000) && !aparentemente_puntuo_app && prefs.getInt("contador_intento", 0) ==1) {  //vuelve a mostrar despues de 3 dias

            showRateDialog(mContext, editor,activity);


            //Guardamos el dia que se mostro la ventana
            dia_time_mostre_dialog = System.currentTimeMillis();
            editor.putLong("dia_time_mostro_ventana", dia_time_mostre_dialog);
            contador_intentos=2;
            editor.putInt("contador_intento", contador_intentos);

        }


//3 intento
        else if (System.currentTimeMillis() >= prefs.getLong("dia_time_mostro_ventana", 0) +   //proxima vez que se mostrara el dialog
                (dias_intento3 * 24 * 60 * 60 * 1000)  && !aparentemente_puntuo_app && prefs.getLong("dia_time_mostro_ventana", 0) > 0 && prefs.getInt("contador_intento", 0) ==2 )   {  //vuelve a mostrar despues de 3 dias

            showRateDialog(mContext, editor,activity);


            dia_time_mostre_dialog = System.currentTimeMillis();
            editor.putLong("dia_time_mostro_ventana", dia_time_mostre_dialog);


            contador_intentos=3;
            editor.putInt("contador_intento", contador_intentos);

        }


//4 intento despues de 7 dia desde la ultima vez que se abrio
        else if (System.currentTimeMillis() >= prefs.getLong("dia_time_mostro_ventana", 0) +   //proxima vez que se mostrara el dialog
                (dias_intento4 * 24 * 60 * 60 * 1000) && !aparentemente_puntuo_app && prefs.getLong("dia_time_mostro_ventana", 0) > 0  && prefs.getInt("contador_intento", 0) ==3 ) {  //vuelve a mostrar despues de 3 dias

            showRateDialog(mContext, editor, activity);


            dia_time_mostre_dialog = System.currentTimeMillis();
            editor.putLong("dia_time_mostro_ventana", dia_time_mostre_dialog);

            contador_intentos=4;
            editor.putInt("contador_intento", contador_intentos);

        }


        //5  intento y ultimo despues de 7 dia desde la ultima vez que se abrio
        else if (System.currentTimeMillis() >= prefs.getLong("dia_time_mostro_ventana", 0) +   //proxima vez que se mostrara el dialog
                (dias_intento5 * 24 * 60 * 60 * 1000) && !aparentemente_puntuo_app && prefs.getLong("dia_time_mostro_ventana", 0) > 0 && prefs.getInt("contador_intento", 0) ==4 ) {  //vuelve a mostrar despues de 3 dias

            showRateDialog(mContext, editor,activity);

            dia_time_mostre_dialog = System.currentTimeMillis();
            editor.putLong("dia_time_mostro_ventana", dia_time_mostre_dialog);

            contador_intentos=5;
            editor.putInt("contador_intento", contador_intentos);

        }


        editor.commit();
    }



    public  void showRateDialog(final Context mContext, final SharedPreferences.Editor editor ,final Activity activity ) {
        //   final Dialog dialog = new Dialog(        getActivity() );


         seabrioventana=true;








        Log.d("renose", "se cerro despues de 10 segundos");
                Dialog dialog = new Dialog(mContext); //anterior sirvia mas o menosx
                //  final Dialog dialog = new Dialog(MainActivity2); //anterior sirvia mas o menosx


                //    getActivity()

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.custom_dialog_rateapp);

                Button b1 = dialog.findViewById(R.id.b1);
                Button b2 = dialog.findViewById(R.id.b2);
                Button b3 = dialog.findViewById(R.id.b3);

                Log.i("CIENCIA", "se jecuto showRATE ");

                b1.setOnClickListener(new View.OnClickListener() { ///pk lo hare
                    public void onClick(View v) {


                        Toast.makeText(mContext, "1111", Toast.LENGTH_SHORT).show();

                        solicitar_puntuacion2(mContext,activity);

                        //    callback.cuando_cierra();
                        callback.cuando_cierra();


                    }
                });


                b2.setOnClickListener(new View.OnClickListener() { //mas tarde
                    public void onClick(View v) {
                        Toast.makeText(mContext, "222", Toast.LENGTH_SHORT).show();


                        //    callback.cuando_cierra();

                        dialog.dismiss();

                        //    goToLoginActivity(mContext);

                        //  Intent intent = Juego_Partida.createIntent(this, 10);
                        //  startActivity(intent);

                        callback.cuando_cierra();


                    }
                });

                b3.setOnClickListener(new View.OnClickListener() { //no gracias..
                    public void onClick(View v) {
                        Toast.makeText(mContext, "3", Toast.LENGTH_SHORT).show();


                        if (editor != null) {
                            editor.putBoolean("dontshowagain", true);
                            editor.commit();
                        }



                        dialog.dismiss();

                        //   callback.cuando_cierra();





                    }
                });



                dialog.show();


                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(final DialogInterface arg) {
                        //when dialog closed
                        //ok dialogo cerrado ....



                        //   public void onDismiss(final DialogInterface dialog) {




                        dialogo_cerrado=true;
                        Toast.makeText(b1.getContext(), "se cerro el dialogo", Toast.LENGTH_SHORT).show();
                        isDialogo_cerrado();

                        callback.cuando_cierra();



                    }
                });


                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });


                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {

                    }
                });


                ///vamos a un metodo de la activity  que muestra continuar..ventana que no se puede cerrar.



            //    dismiss();



















    }

    public   Boolean isDialogo_cerrado(){
        return dialogo_cerrado;
    }



    public static Intent createIntent(Activity activity, int value) {
        Intent intent = new Intent(activity, Juego_Partida.class);
        intent.putExtra("EXTRA_VALUE", value);
        return intent;
    }


    public static void goToLoginActivity(Context mContext) {

        Intent login = new Intent(mContext, Juego_Partida.class);

        mContext.startActivity(login);
    }

    public void mundo_azul(){
        Log.d("eres", "mundo_azul");
        callback.cuando_cierra();

    }

    public MainActivity2dos(SampleCallback callback){

        this.callback= callback;


    }

    //callback de ejemplo


}