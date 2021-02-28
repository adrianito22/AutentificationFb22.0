package com.tiburela.TriviasMedicas.holder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tiburela.TriviasMedicas.R;
import com.tiburela.TriviasMedicas.comunicador.SelectAvatarComunicador;
import com.tiburela.TriviasMedicas.model.SelectAvatar;

public class SelectAvatarHolder extends RecyclerView.ViewHolder {

    private SelectAvatarComunicador selectAvatarComunicador;
    private ImageView imgAvatar;

    public SelectAvatarHolder(@NonNull View itemView, SelectAvatarComunicador selectAvatarComunicador) {
        super(itemView);

        this.selectAvatarComunicador = selectAvatarComunicador;

        imgAvatar = itemView.findViewById(R.id.imgAvatar);

    }

    public void init(SelectAvatar selectAvatar){
        Glide.with(itemView).load(selectAvatar.getUrl())
                .apply(new RequestOptions().override(200, 200))//tamaño de las fotos
                .into(imgAvatar);

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAvatarComunicador.click(selectAvatar);
            }
        });

    }

}
