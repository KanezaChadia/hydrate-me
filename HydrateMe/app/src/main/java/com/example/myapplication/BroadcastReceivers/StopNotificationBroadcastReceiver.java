package com.example.myapplication.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.myapplication.MainWindow.AlarmHelper;


public class StopNotificationBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmHelper.stopNotificationsAlarm(context);
        System.out.println("fired!");
    }
}
