package com.tiburela.TriviasMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tiburela.TriviasMedicas.comunicador.SelectAvatarFragmentComunicador;
import com.tiburela.TriviasMedicas.dialog.SelectAvatarDialog;
import com.tiburela.TriviasMedicas.model.SelectAvatar;
import com.tiburela.TriviasMedicas.utils.Preferences;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "FBAUTH";

    private CallbackManager callbackManager;

    private ImageView imgAvatar;
    private EditText edtNombre;
    private Button btnSelectPerfil;
    private Button btnIniciarInvitado;
    private String avatarUrl;
    private LoginButton loginButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgAvatar = findViewById(R.id.imgAvatar);
        edtNombre = findViewById(R.id.edtNombre);
        btnSelectPerfil = findViewById(R.id.btnSelectPerfil);
        btnIniciarInvitado = findViewById(R.id.btnIniciarInvitado);

        auth = FirebaseAuth.getInstance();

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.setReadPermissions("email", "public_profile");

        btnSelectPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectAvatarDialog selectAvatarDialog = SelectAvatarDialog.getInstance(new SelectAvatarFragmentComunicador() {
                    @Override
                    public void click(SelectAvatar selectAvatar) {
                        Glide.with(getApplicationContext()).load(selectAvatar.getUrl())
                                .apply(new RequestOptions().override(200, 200))//tamaño de las fotos
                                .into(imgAvatar);
                        avatarUrl = selectAvatar.getUrl();
                    }
                });
                selectAvatarDialog.show(getSupportFragmentManager(), "SA");
            }
        });

        btnIniciarInvitado.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                iniciarInvitado();
            }
        });

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

    private void iniciarInvitado() {
        String nombre = edtNombre.getText().toString().trim();
        if(nombre.isEmpty()){
            edtNombre.setError("debe poner el nombre de usuario");
            return;
        }
        if(avatarUrl==null){
            Toast.makeText(this, "Debe seleccionar un avatar para continuar", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.getException()!=null){
                    Toast.makeText(LoginActivity.this, "Error :( ,revisa tu conexion a internet "+task.getException(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(LoginActivity.this, "Inicio corrrectamente como usuario invitado", Toast.LENGTH_SHORT).show();
                Preferences.savePreferenceString(LoginActivity.this,nombre,Preferences.KEY_NAME);
                Preferences.savePreferenceString(LoginActivity.this,avatarUrl,Preferences.KEY_AVATAR);
                // openScores();
                iraIntroslider();
            }
        });


    }

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
                            iraIntroslider();
                            //openScores();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "No display text",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if (firebaseUser != null) {
           // openScores();
            iraIntroslider();

        }
    }

    private void openScores() {
        Intent intent = new Intent(this, ProfileActivity.class);
        //voy a pasar una puntuacion de testeo dale luego le pones tu el parametro global
        intent.putExtra("puntos",50);
        startActivity(intent);
        finish();
    }



    public void iraIntroslider(){
        Intent intento = new Intent(this, com.tiburela.TriviasMedicas.Home.class);
        //voy a pasar una puntuacion de testeo dale luego le pones tu el parametro global
        startActivity(intento);
        finish();

    }

}

