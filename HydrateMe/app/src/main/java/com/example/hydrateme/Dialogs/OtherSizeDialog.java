package com.example.hydrateme.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.hydrateme.R;
import com.example.hydrateme.Settings.PrefsHelper;


public class OtherSizeDialog  extends Dialog implements View.OnClickListener,NumberPicker.OnValueChangeListener{
    private Context context;
    private NumberPicker numberPicker;
    private Button setButton;
    private int NumberPickerValue;
    private  MediaPlayer mediaPlayer;
    private final String OTHER= "other";
    public OtherSizeDialog(Context context) {
        super(context);
        this.context=context;


    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_picker_dialog);
        numberPicker=(NumberPicker)findViewById(R.id.numberPicker);
        setButton = (Button) findViewById(R.id.set_button);
        mediaPlayer= MediaPlayer.create(context, R.raw.splash_water);
        setButton.setOnClickListener(this);
        numberPicker.setOnClickListener(this);

        setNumberPickerFormat();
        setTitle("Add drink");
    }
    private void setNumberPickerFormat() {
        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                value =value*5;
                return  value+" ml";
            }
        };
        numberPicker.setFormatter(formatter);
        numberPicker.setMaxValue(300);
        numberPicker.setMinValue(0);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.set_button:
                addFromNumberPiker();

        }


    }

    private void addFromNumberPiker() {

    }

    private void playSound() {
        if (PrefsHelper.getSoundsPrefs(context))
            mediaPlayer.start();


    }

    private void updateView(int perBefore) {


    }



    public boolean getCongDialogPrefs()
    {   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean showDialog = preferences.getBoolean("show_dialog", true);
        return showDialog;
    }
    public  void setCongDialogPrefs(boolean value)
    {   SharedPreferences preferences =PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("show_dialog", value);
        editor.commit();
    }



    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        this.NumberPickerValue= picker.getValue()*5;
    }
}
