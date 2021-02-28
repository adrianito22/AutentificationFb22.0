package com.tiburela.TriviasMedicas.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String PREFERENCES = "com.tiburela.triviasMedicas";

    public static final String KEY_NAME = "key.name";
    public static final String KEY_AVATAR = "key.avatar";

    public static void savePreferenceBoolean(Context c, boolean b, String key){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key,b).apply();
    }

    public static void savePreferenceString(Context c, String b, String key){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        preferences.edit().putString(key,b).apply();
    }

    public static void savePreferenceInteger(Context c, Integer b, String key){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        preferences.edit().putInt(key,b).apply();
    }

    public static boolean obtenerPreferenceBoolean(Context c, String key){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return preferences.getBoolean(key,false);//Si es que nunca se ha guardado nada en esta key pues retornara false
    }

    public static String obtenerPreferenceString(Context c, String key){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return preferences.getString(key,"");//Si es que nunca se ha guardado nada en esta key pues retornara una cadena vacia
    }

    public static Integer obtenerPreferenceInteger(Context c, String key){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return preferences.getInt(key,0);//Si es que nunca se ha guardado nada en esta key pues retornara 0
    }

}
