package com.tiburela.TriviasMedicas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

import java.util.Objects;

public class MainActivity2dos extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    Toolbar toolbar;
    private Menu menu;// Global Menu Declaration



    Button boton_ok_rate;
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

                R.id.navigation_home, R.id.navigation_dashboard,R.id.navigation_notifications,R.id.navigation_items,R.id.fragment_licencias,R.id.fragment_puntua_app,R.id.fragment_acerca)
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





}



