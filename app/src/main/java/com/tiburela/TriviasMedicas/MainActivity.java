package com.tiburela.TriviasMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private     DatabaseReference data;
    int valor_puntaje;//yo le pasare el puntaje a esta activity


    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    private static final String TAG = "FBAUTH";
    //private static final String EMAIL = "email";
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_partida);
        auth = FirebaseAuth.getInstance();

     //   loginButton.setPermissions("email", "public_profile"); // esconder

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    protected void onStart() {
        super.onStart();
        /*FirebaseUser firebaseUser = auth.getCurrentUser();
        if (firebaseUser != null) {
            openScores();
        }*/
    }








}
