package com.castle.zomatotestapp.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.castle.zomatotestapp.SearchApplication;
import com.castle.zomatotestapp.constants.AppConstant;

/**
 * Created by nightstay on 12/06/17.
 */
public class AppStorage {

    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor editor;
    private static AppStorage appStorage;

    private AppStorage(){
        mPreferences = SearchApplication.getInstance().getSharedPreferences(AppConstant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static AppStorage getInstance(){
        if(appStorage == null){
            appStorage = new AppStorage();
        }
        return appStorage;
    }

    public void saveStringPreferences(String key, String value){
        editor = mPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringPreference(String key){
        return mPreferences.getString(key, "");
    }
}
