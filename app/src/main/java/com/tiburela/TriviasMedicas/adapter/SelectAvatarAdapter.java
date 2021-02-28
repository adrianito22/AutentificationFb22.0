package com.tiburela.TriviasMedicas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tiburela.TriviasMedicas.R;
import com.tiburela.TriviasMedicas.comunicador.SelectAvatarComunicador;
import com.tiburela.TriviasMedicas.holder.SelectAvatarHolder;
import com.tiburela.TriviasMedicas.model.SelectAvatar;

import java.util.ArrayList;
import java.util.List;

public class SelectAvatarAdapter extends RecyclerView.Adapter<SelectAvatarHolder> {

    private List<SelectAvatar> selectAvatarList;
    private SelectAvatarComunicador selectAvatarComunicador;

    public SelectAvatarAdapter(SelectAvatarComunicador selectAvatarComunicador) {
        selectAvatarList = new ArrayList<>();
        this.selectAvatarComunicador = selectAvatarComunicador;
    }

    public void add(SelectAvatar selectAvatar) {
        selectAvatarList.add(selectAvatar);
    }

    @NonNull
    @Override
    public SelectAvatarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_select_avatar,parent,false);
        return new SelectAvatarHolder(view, selectAvatarComunicador);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectAvatarHolder holder, int position) {
        holder.init(selectAvatarList.get(position));
    }

    @Override
    public int getItemCount() {
        return selectAvatarList.size();
    }

}
