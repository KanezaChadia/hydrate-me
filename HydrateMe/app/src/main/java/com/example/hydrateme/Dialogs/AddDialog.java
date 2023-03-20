package com.example.hydrateme.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hydrateme.R;
import com.example.hydrateme.Settings.PrefsHelper;


public class AddDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private int glassSize;
    private int bottleSize;
    private MediaPlayer mediaPlayer;

    private Button otherSize,cancel, glassButton,bottleButton;

    public AddDialog(Context context){
        super(context);
        this.context = context;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drink_dialog);
        mediaPlayer= MediaPlayer.create(context, R.raw.splash_water);
        otherSize = (Button) findViewById(R.id.other_size_button);
        cancel = (Button) findViewById(R.id.cancel_button);
        bottleButton= ( Button)findViewById(R.id.water_bottle_button);
        glassButton=  (Button) findViewById(R.id.water_glass_button);
        bottleButton.setOnClickListener(this);
        glassButton.setOnClickListener(this);
        cancel.setOnClickListener(this);
        otherSize.setOnClickListener(this);
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
            case R.id.other_size_button:
               showOtherSizeDialog();
                break;

        }
        dismiss();
    }

    private void showOtherSizeDialog() {
        OtherSizeDialog otherSizeDialog = new OtherSizeDialog(context);
        otherSizeDialog.show();
        dismiss();
    }

    private void playSound() {
        if (PrefsHelper.getSoundsPrefs(context))
            mediaPlayer.start();


    }

    private void addGlass(){


    }

    private void addBottle() {

    }

    private void updateView(int perBefore) {


    }




    private void loadContainerSizePrefs() {
       String glassSizeStr= PrefsHelper.getGlassSizePrefs(context);
        String bottleSizeStr = PrefsHelper.getBottleSizePrefs(context);
        this.glassSize = Integer.valueOf(glassSizeStr);
        this.bottleSize = Integer.valueOf(bottleSizeStr);
        glassButton.setText(glassSizeStr+ " ml");
        bottleButton.setText(bottleSizeStr+ " ml");
    }

}