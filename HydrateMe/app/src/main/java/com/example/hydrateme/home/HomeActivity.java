package com.example.hydrateme.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.hydrateme.Dialogs.AddDialog;
import com.example.hydrateme.R;
import com.example.hydrateme.Settings.SettingsActivity;
import com.example.hydrateme.history.HistoryActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    BottomNavigationView bottomNavigationView;
    Button addADrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.tab_home);

        addADrink = findViewById(R.id.add_a_drink);
        addADrink.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.tab_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_home:
                return true;

            case R.id.tab_history:
                startActivityForResult(new Intent(this, HistoryActivity.class), 0);
                return true;

            case R.id.tab_settings:
                startActivityForResult(new Intent(this, SettingsActivity.class),0);
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        AddDialog dialog = new AddDialog(this);
        dialog.show();

    }

    public void setupSettings(){

    }

}