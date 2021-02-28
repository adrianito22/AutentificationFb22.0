package com.tiburela.TriviasMedicas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.tiburela.TriviasMedicas.utils.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ListView listita;
    private List<Data> dataList;
    private AdaptadorData adapter;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;

    private Button btnCerrarSesion;
    private ImageView fotoPerfil;
    private TextView name, score, tvNombre;
    private int puntos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);


        dataList = new ArrayList<>();
        listita = findViewById(R.id.listita);
        adapter = new AdaptadorData(this);
        listita.setAdapter(adapter);

        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        fotoPerfil = findViewById(R.id.fotoPerfil);
        tvNombre = findViewById(R.id.tvNombre);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        if(user==null){
            finish();//si el usuario es null finalizamos la actividad
            return;
        }

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            puntos = bundle.getInt("puntos");
        }

        String fotoDeperfil;
        String nombresCompletos;

        if(Preferences.obtenerPreferenceString(this,Preferences.KEY_AVATAR).isEmpty()){
            fotoDeperfil = user.getPhotoUrl().toString();
            nombresCompletos = user.getDisplayName();
        }else{
            fotoDeperfil = Preferences.obtenerPreferenceString(this,Preferences.KEY_AVATAR);
            nombresCompletos = Preferences.obtenerPreferenceString(this,Preferences.KEY_NAME);
        }

        Glide.with(this).load(fotoDeperfil).circleCrop().into(fotoPerfil);

        //creamos el objeto de puntuacion
        Data data = new Data();
        data.setNombresCompletos(nombresCompletos);
        data.setFoto(fotoDeperfil);
        data.setPuntaje(puntos);
        tvNombre.setText(data.getNombresMaxTwo());

        //guardamos en firebase la informacion
        database.getReference(Constantes.NODO_PUNTUACION)
                .child(user.getUid())//codigo unico
                .setValue(data)//insertamos el objeto
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            //se subio correctamente la informacion
                            Toast.makeText(ProfileActivity.this, "se subio correctamente la informacion", Toast.LENGTH_SHORT).show();
                        }else{
                            //error
                            Toast.makeText(ProfileActivity.this, "Error: "+task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //escuchamos en tiempo real los cambios
        database.getReference(Constantes.NODO_PUNTUACION)
                .orderByChild("puntaje")//ordenamos por puntaje
                .limitToFirst(10)//los 10 primeros
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        //nueva puntuacion
                        Data data = snapshot.getValue(Data.class);
                        data.setId(snapshot.getKey());
                        nuevaPuntuacion(data);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Data data = snapshot.getValue(Data.class);
                        data.setId(snapshot.getKey());
                        actualizarPuntuacion(data);
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void nuevaPuntuacion(Data data) {
        dataList.add(data);//agregamos el objeto a nuestra lista global
        limpiarOrdenar();
    }

    private void actualizarPuntuacion(Data data){
        for (int i = 0; i < dataList.size(); i++) {
            Data data1 = dataList.get(i);
            if(data1.getId().equals(data.getId())){
                dataList.set(i,data);//actualizamos con la nueva informacion
            }
        }
        limpiarOrdenar();
    }

    private void limpiarOrdenar(){
        Collections.sort(dataList, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return Integer.compare(o2.getPuntaje(),o1.getPuntaje());//ordenamos la puntuacion asendentemente
            }
        });
        adapter.clear();//limpiamos el listview
        for (int i = 0; i < dataList.size(); i++) {
            Data d = dataList.get(i);
            d.setPosicion(i+1);//ponemos la posicion localmente
            adapter.add(d);//agregamos el objeto al listview
        }
        adapter.notifyDataSetChanged();//mandamos una actualizacion
    }

    private void cerrarSesion() {
        Preferences.savePreferenceString(this,"",Preferences.KEY_NAME);
        Preferences.savePreferenceString(this,"",Preferences.KEY_AVATAR);
        auth.signOut();//cerrar firebase
        LoginManager.getInstance().logOut();//cerrar facebook
        finishAffinity();
        startActivity(new Intent(this, com.tiburela.TriviasMedicas.MainActivity.class));

    }

    class AdaptadorData extends ArrayAdapter<Data>{

        //private List<Data> dataList = new ArrayList<>();

        public AdaptadorData(@NonNull Context context) {
            super(context, R.layout.view_posicion);
            //this.dataList = dataList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_posicion,parent,false);

            TextView tvPosicion = view.findViewById(R.id.tvPosicion);
            TextView tvNombre = view.findViewById(R.id.tvNombre);
            TextView tvPuntos = view.findViewById(R.id.tvPuntos);
            ImageView fotoPerfil = view.findViewById(R.id.fotoPerfil);

            Data data = getItem(position);
            tvPosicion.setText(""+data.getPosicion());
            tvNombre.setText(data.getNombresMaxTwo());
            tvPuntos.setText("Puntos: "+data.getPuntaje());
            Glide.with(getContext()).load(data.getFoto()).into(fotoPerfil);

            return view;
        }

    }


    public void iraactivity(View view) {
        Intent iactivity =new Intent(this , com.tiburela.TriviasMedicas.Juego_Partida.class );

        startActivity(iactivity);

    }



}