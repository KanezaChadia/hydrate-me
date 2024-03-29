package com.example.myapplication.WaterDrankHistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Database.DrinkDataSource;
import com.example.myapplication.MainWindow.DateHandler;
import com.example.myapplication.Model.DateLog;
import com.example.myapplication.R;
import com.github.lzyzsd.circleprogress.CircleProgress;

import java.util.List;

public class DateLogActivity extends AppCompatActivity {

    private DateLog dateLog;
    private TextView waterLog,dateTv;
    private CircleProgress circleProgress;
    static ListView listView;
    static List<DateLog> values ;
    static ArrayAdapter<DateLog> adapter;


    private static DrinkDataSource db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.date_log_activity);
        setTitle("History");

        db = new DrinkDataSource(this);
        db.open();

        dateLog= new DateLog();
        values = db.getAllDateLogs();

        listView = (ListView) findViewById(R.id.log_list);

        adapter = new myListAdapter();
        listView.setAdapter(adapter);


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private class myListAdapter extends ArrayAdapter<DateLog> {

        public myListAdapter() {
            super(DateLogActivity.this, R.layout.date_log_raw, values);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(
                        R.layout.date_log_raw, parent, false);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), TimeLogActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(Commands.DateLogItem,DateHandler.getDateFormat(values.get(position).getDate()));
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }

            });
            ImageButton shareButton = (ImageButton) itemView.findViewById(R.id.forward);

            final int  waterNeed =values.get(position).getWaterNeed();
            final int waterDrank = values.get(position).getWaterDrunk();
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text =  "I've just been reminded to drink " +dateLog.getWaterInOunce(waterDrank)
                            +  " of " + dateLog.getWaterInOunce(waterNeed) + "L by Daily Water Tracker!";
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,text);
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            });
            dateTv = (TextView) itemView.findViewById(R.id.date);
            dateTv.setText(DateHandler.dateFormat(values.get(position).getDate()));

            waterLog = (TextView) itemView.findViewById(R.id.water_drunk);

            waterLog.setText(waterDrank + "/" +
                    waterNeed + " oz");


            return itemView;
        }
    }
    public static void reloadAdapter() {
        adapter.clear();
        adapter.addAll(db.getAllDateLogs());
        listView.setAdapter(adapter);
    }

    }