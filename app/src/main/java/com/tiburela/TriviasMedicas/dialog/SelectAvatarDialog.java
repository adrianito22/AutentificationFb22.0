package com.tiburela.TriviasMedicas.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tiburela.TriviasMedicas.R;
import com.tiburela.TriviasMedicas.adapter.SelectAvatarAdapter;
import com.tiburela.TriviasMedicas.comunicador.SelectAvatarComunicador;
import com.tiburela.TriviasMedicas.comunicador.SelectAvatarFragmentComunicador;
import com.tiburela.TriviasMedicas.model.SelectAvatar;

public class SelectAvatarDialog extends DialogFragment implements SelectAvatarComunicador {

    private SelectAvatarFragmentComunicador selectAvatarFragmentComunicador;

    public static SelectAvatarDialog getInstance(SelectAvatarFragmentComunicador selectAvatarFragmentComunicador){
        SelectAvatarDialog selectAvatarDialog = new SelectAvatarDialog();
        selectAvatarDialog.setSelectAvatarFragmentComunicador(selectAvatarFragmentComunicador);
        return selectAvatarDialog;
    }

    public SelectAvatarFragmentComunicador getSelectAvatarFragmentComunicador() {
        return selectAvatarFragmentComunicador;
    }

    public void setSelectAvatarFragmentComunicador(SelectAvatarFragmentComunicador selectAvatarFragmentComunicador) {
        this.selectAvatarFragmentComunicador = selectAvatarFragmentComunicador;
    }

    private RecyclerView rvAvatar;
    private SelectAvatarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_select_avatar,container,false);

        rvAvatar = view.findViewById(R.id.rvAvatar);
        adapter = new SelectAvatarAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rvAvatar.setAdapter(adapter);
        rvAvatar.setLayoutManager(gridLayoutManager);

        adapter.add(new SelectAvatar("https://picsum.photos/id/1/200/200"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/2/200/200"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/3/200/200"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/4/200/000"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/5/200/200"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/6/200/200"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/7/200/200"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/8/200/200"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/9/200/200"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/10/200/200"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/11/200/200"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/12/200/200"));
        adapter.add(new SelectAvatar("https://picsum.photos/id/13/200/200"));
        adapter.add(new SelectAvatar("https://firebasestorage.googleapis.com/v0/b/practica1-7bbc0.appspot.com/o/avatars%2Fimg_005.jpg?alt=media&token=784d7085-34be-41f2-84bc-2e896d6c26a0"));

        return view;
    }

    @Override
    public void click(SelectAvatar selectAvatar) {
        dismiss();
        getSelectAvatarFragmentComunicador().click(selectAvatar);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = LayoutParams.WRAP_CONTENT;
        params.height = LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        int size;
        if(getScreenWidth(getActivity())<=getScreenHeight(getActivity())){
            size = getScreenWidth(getActivity());
        }else{
            size = getScreenHeight(getActivity());
        }
        dialog.getWindow().setLayout((int) (size * .95), (int) (size * .95));
    }

    public static int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    public static int getScreenHeight(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.y;
    }

}
