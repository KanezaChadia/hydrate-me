package com.example.myapplication.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

import com.example.myapplication.Database.DrinkDataSource;
import com.example.myapplication.MainWindow.AlarmHelper;
import com.example.myapplication.MainWindow.DateHandler;
import com.example.myapplication.Settings.PrefsHelper;
import com.example.myapplication.Settings.PreferenceKey;


public class DateLogBroadcastReceiver extends BroadcastReceiver {

    private DrinkDataSource db;
    private Context mContext;


    @Override
    public void onReceive(Context context, Intent intent) {
        mContext =context;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean NotificationSet= prefs.getBoolean(PreferenceKey.PREF_IS_ENABLED,true);
                insertDateLog();

                  if(NotificationSet)
                  { AlarmHelper.setNotificationsAlarm(context);
                    AlarmHelper.setCancelNotificationAlarm(context);}

    }



    private void insertDateLog() {

        int waterNeed= PrefsHelper.getWaterNeedPrefs(mContext);
        db= new DrinkDataSource(mContext);
        db.open();
         db.createDateLog(0,waterNeed, DateHandler.getCurrentDate());
  //     db.createMissingDateLog(0,waterNeed);

        System.out.println("db alarm fired "+ DateHandler.getCurrentDate());
        System.out.println("new record inserted: "+new Date()+" water need: "+waterNeed+" Water drank: "+0);

        Intent local = new Intent();
        local.setAction("com.update.view.action");
        mContext.sendBroadcast(local);

    }

}
