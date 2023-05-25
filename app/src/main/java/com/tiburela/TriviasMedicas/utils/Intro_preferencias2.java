package com.tiburela.TriviasMedicas.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Intro_preferencias2 {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "miprefrencia";
    private static final String IS_FIRST_TIME_LAUNCH = "firstTime";

    public Intro_preferencias2(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setIsFirstTimeLauncher(boolean firstTimeLaunch) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, firstTimeLaunch);
        editor.commit();
    }

    public boolean isFirstTimeLauncher() {
        return preferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}
