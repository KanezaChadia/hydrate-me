package com.example.hydrateme.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.hydrateme.R;


public class finishDialog extends Dialog implements View.OnClickListener{
    private ImageView CongCancel;

    public finishDialog(Context context) {
        super(context);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_dialog);
        CongCancel = (ImageView) findViewById(R.id.imageView2);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        CongCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imageView2:
                dismiss();
                break;
        }

    }
}
