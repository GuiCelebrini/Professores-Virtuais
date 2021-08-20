package com.android.guicelebrini.professoresvirtuais.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferences {

    private Context context;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "teacher.preferences";
    private final int MODE = 0;

    private final String KEY_NAME = "userName";

    public Preferences(Context parameterContext){
        this.context = parameterContext;
        preferences = context.getSharedPreferences(FILE_NAME, MODE);
        editor = preferences.edit();
    }

    public void saveData(String userName){
        editor.putString(KEY_NAME, userName);
        editor.commit();
    }


    public String getUserName(){
        return preferences.getString(KEY_NAME, null);
    }
}
