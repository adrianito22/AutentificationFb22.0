package com.tiburela.TriviasMedicas.Controlador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tiburela.TriviasMedicas.ui.home_juego.Fragment1;
import com.tiburela.TriviasMedicas.ui.Enviar_pregunta.Fragment2;
import com.tiburela.TriviasMedicas.ui.Acerca.Fragment3;

public class PagerController extends FragmentPagerAdapter {

    int numero_tabs;

    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numero_tabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

                 case 0:
                     return new Fragment1();
                case 1:
                    return new Fragment2();
                case 2:
                    return new Fragment3();
                default:
                    return null;
        }



    }

    @Override
    public int getCount() {
        return numero_tabs;
    }
}
