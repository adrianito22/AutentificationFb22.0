package com.tiburela.TriviasMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
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
    private CallbackManager callbackManager;
    //private static final String EMAIL = "email";
    private LoginButton loginButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_partida);

        auth = FirebaseAuth.getInstance();

        callbackManager = CallbackManager.Factory.create();

      //  loginButton = (LoginButton)findViewById(R.id.login_button);

       loginButton.setReadPermissions("email", "public_profile");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d(TAG, "onError: "+exception.getMessage());
            }
        });
    }

    //esto es lo que hiciste la vez pasada.. :)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                          // FirebaseUser user = mAuth.getCurrentUser();
                            openScores();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "No display text",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*FirebaseUser firebaseUser = auth.getCurrentUser();
        if (firebaseUser != null) {
            openScores();
        }*/
    }

    private void openScores() {
        Intent intent = new Intent(this, ProfileActivity.class);
        //voy a pasar una puntuacion de testeo dale luego le pones tu el parametro global
        intent.putExtra("puntos",50);
        startActivity(intent);
        finish();
    }

}
