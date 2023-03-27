package com.example.myapplication.MainWindow;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Database.DrinkDataSource;
import com.example.myapplication.Dialogs.AddDialog;
import com.example.myapplication.R;
import com.example.myapplication.Settings.PrefsHelper;
import com.example.myapplication.Settings.SettingsActivity;
import com.example.myapplication.WaterDrankHistory.DateLogActivity;
import com.github.lzyzsd.circleprogress.DonutProgress;

public class MainActivity extends Activity  implements View.OnClickListener {

    private ImageButton logButton, settingButton;
    private Button addDrinkButton;
    private LinearLayout mainLayout;
    public static  DonutProgress circleProgress;
    public static  TextView choosenAmountTv;
    private DrinkDataSource db;
    private BroadcastReceiver updateUIReciver;
    private Context context ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= new DrinkDataSource(this);
        db.open();
        context=getApplicationContext();
        checkAppFirstTimeRun();
        AlarmHelper.setDBAlarm(context);
        setContentView(R.layout.main_page);
        mainLayout= (LinearLayout) findViewById(R.id.main_view);
        initializeViews();
        updateView();
        registerUIBroadcastReceiver();
      }

    private void checkAppFirstTimeRun() {

        if (PrefsHelper.getFirstTimeRunPrefs(context)) {
            db.createDateLog(0,PrefsHelper.getWaterNeedPrefs(context),
                    DateHandler.getCurrentDate());
            AlarmHelper.setDBAlarm(context);
            startActivityForResult(new Intent(getBaseContext(), SettingsActivity.class),0);
            PrefsHelper.setFirstTimeRunPrefs(context,false);
        }
    }

    @Override
    protected void onDestroy(){
        unregisterReceiver(updateUIReciver);
        super.onDestroy();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        switch (requestCode) {
            case 0:
                 db.updateWaterNeedForTodayDateLog(PrefsHelper.getWaterNeedPrefs(context));
                 updateView();
                 loadNotificationsPrefs();
                break;
        }

    }

    private void loadNotificationsPrefs() {
        boolean isEnable= PrefsHelper.getNotificationsPrefs(context);

        if (isEnable)
        {
            AlarmHelper.setNotificationsAlarm(context);
            AlarmHelper.setCancelNotificationAlarm(context);
           }
        else
        {
            AlarmHelper.stopNotificationsAlarm(context);
      }
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
            switch (id){
                case R.id.log_button:
                    goToLogActivity();
                    break;
                case R.id.setting_button:
                    goToSettingActivity();
                    break;
                case R.id.add_drink_button:
                  showAddDialog();
                    break;



    }
}


    private void  updateView(){
      int perValue= db.getConsumedPercentage();
          choosenAmountTv.setText(String.valueOf(db.geConsumedWaterForTodayDateLog()+" out of "+
                  PrefsHelper.getWaterNeedPrefs(getApplicationContext())+" oz"));
       }

    private void showAddDialog() {
        AddDialog a = new AddDialog(this, db);
        a.show();
    }


    private void goToSettingActivity() {
        startActivityForResult(new Intent(this, SettingsActivity.class),0);
    }



    private void goToLogActivity() {
        Intent intent4 = new Intent(getApplicationContext(), DateLogActivity.class);
        startActivity(intent4);
    }



    public void  initializeViews(){

        logButton =(ImageButton)findViewById(R.id.log_button);
        settingButton =(ImageButton)findViewById(R.id.setting_button);

        choosenAmountTv=(TextView) findViewById(R.id.choosen_drink_text);
        addDrinkButton = (Button) findViewById(R.id.add_drink_button);
        logButton.setOnClickListener(this);
        settingButton.setOnClickListener(this);

        addDrinkButton.setOnClickListener(this);
    }

    private void registerUIBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.update.view.action");
        updateUIReciver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateView();
            }
        };
        registerReceiver(updateUIReciver,filter);
    }

}
