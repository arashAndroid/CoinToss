package com.example.admin.cointoss;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    Context _context;
    SharedPreferences pref;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "coinToss";
    private static final String LANGUAGE_KEY = "language";
    SharedPreferences.Editor editor;
    public SharedPrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void setLanguage(String language){
        editor.putString(LANGUAGE_KEY,language);
        editor.commit();
    }
    public String getLanguage(){
        return pref.getString(LANGUAGE_KEY,"fa");
    }
    public boolean isFirstRun(){
        return pref.getBoolean("isFirstRun",true);
    }
    public void setFirstRun(){
        editor.putBoolean("isFirstRun",false);
        editor.commit();
    }
    public int getHeadsId(){
        return pref.getInt("headsID",R.drawable.b500);
    }
    public int getTailsId(){
        return pref.getInt("tailsID",R.drawable.f500);
    }
    public void setHeadsId(int headsId){
        editor.putInt("headsID",headsId);
        editor.commit();
    }
    public void setTailsId(int tailsId){
        editor.putInt("tailsID",tailsId);
        editor.commit();
    }
    public void setSoundPref(boolean soundPref){
        editor.putBoolean("soundPref",soundPref);
        editor.commit();
    }
    public boolean getSoundPref(){
        return pref.getBoolean("soundPref",true);
    }

    public void setPremium(){
        editor.putBoolean("premium",true);
        editor.commit();
    }
    public boolean hasPremium(){
        return pref.getBoolean("premium",false);
    }

    public void setTheme(int themeId){
        editor.putInt("themeId",themeId);
        editor.commit();
    }
    public int getTheme(){
        return pref.getInt("themeId",0);
    }

}
