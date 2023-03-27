package com.example.myapplication.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Database.DrinkDataSource;
import com.example.myapplication.MainWindow.DateHandler;
import com.example.myapplication.MainWindow.MainActivity;
import com.example.myapplication.Settings.PrefsHelper;
import com.example.myapplication.R;


public class AddDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private final String GLASS="glass";
    private final String BOTTLE="bottle";
    private int glassSize;
    private int bottleSize;
    private DrinkDataSource db;
    private MediaPlayer mediaPlayer;

    private Button cancel, glassButton,bottleButton;

    public AddDialog(Context context, DrinkDataSource db){
        super(context);
        this.context = context;
        this.db= db;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drink_dialog);
        mediaPlayer= MediaPlayer.create(context, R.raw.splash_water);
        cancel = (Button) findViewById(R.id.cancel_button);
        bottleButton= ( Button)findViewById(R.id.water_bottle_button);
        glassButton=  (Button) findViewById(R.id.water_glass_button);
        bottleButton.setOnClickListener(this);
        glassButton.setOnClickListener(this);
        cancel.setOnClickListener(this);
        setTitle("Select drink");
        loadContainerSizePrefs();

    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id){
             case R.id.water_bottle_button:
                addBottle();
                break;
            case R.id.water_glass_button:
                addGlass();
                break;
            case R.id.cancel_button:
              dismiss();
                break;

        }
        dismiss();
    }


    private void playSound() {
        if (PrefsHelper.getSoundsPrefs(context))
            mediaPlayer.start();
    }

    private void addGlass(){
       int perBefore= db.getConsumedPercentage();
        db.createTimeLog(glassSize,GLASS, DateHandler.getCurrentDate(),DateHandler.getCurrentTime());
        db.createDateLog(glassSize,PrefsHelper.getWaterNeedPrefs(context),
                DateHandler.getCurrentDate());
        db.updateConsumedWaterForTodayDateLog(glassSize);


        updateView(perBefore);
        playSound();
        dismiss();
    }

    private void addBottle() {
        int perBefore= db.getConsumedPercentage();
        db.createTimeLog(bottleSize,BOTTLE,DateHandler.getCurrentDate(),DateHandler.getCurrentTime());
        db.updateConsumedWaterForTodayDateLog(bottleSize);
        db.createDateLog(bottleSize,PrefsHelper.getWaterNeedPrefs(context),DateHandler.getCurrentDate());

        updateView(perBefore);
        playSound();
        dismiss();
    }

    private void updateView(int perBefore) {
        int perValue= db.getConsumedPercentage();
        MainActivity.choosenAmountTv.setText(String.valueOf(db.geConsumedWaterForTodayDateLog()+" out of "+
                PrefsHelper.getWaterNeedPrefs(context)+" oz"));

            if (perValue==100 && perBefore<100) {
                congratulationDialog c = new congratulationDialog(context);
                c.show();
            }
    }




    private void loadContainerSizePrefs() {
       String glassSizeStr= PrefsHelper.getGlassSizePrefs(context);
        String bottleSizeStr =PrefsHelper.getBottleSizePrefs(context);
        this.glassSize = Integer.valueOf(glassSizeStr);
        this.bottleSize = Integer.valueOf(bottleSizeStr);
        glassButton.setText(glassSizeStr+ " oz");
        bottleButton.setText(bottleSizeStr+ " oz");
    }

}