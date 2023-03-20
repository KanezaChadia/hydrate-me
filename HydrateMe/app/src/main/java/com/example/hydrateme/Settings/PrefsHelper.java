package com.example.hydrateme.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;



public class PrefsHelper {



    public static int getWaterNeedPrefs(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(PreferenceKey.PREF_WATER_NEED,2500);
    }


    public static boolean getSoundsPrefs(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(PreferenceKey.PREF_SOUND,false);
    }

    public static String getGlassSizePrefs(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
         return prefs.getString(PreferenceKey.PREF_GLASS_SIZE,"6");

    }
    public static String getBottleSizePrefs(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

          return  prefs.getString(PreferenceKey.PREF_BOTTLE_SIZE, "32");
    }

    public static void setFullName(Context context, String FullName){
         PreferenceManager.getDefaultSharedPreferences(context)
        .edit()
                 .putString("full_name",FullName)
                 .apply();


    }

    public static void setEmail(Context context, String Email){
        PreferenceManager.getDefaultSharedPreferences(context)
        .edit()
                .putString("email",Email)
                .apply();


    }
    public static void setPassword(Context context, String Password){
        PreferenceManager.getDefaultSharedPreferences(context)
        .edit()
                .putString("password",Password)
                .apply();
    }



}