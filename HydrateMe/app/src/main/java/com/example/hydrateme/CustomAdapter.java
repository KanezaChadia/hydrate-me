package com.example.hydrateme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hydrateme.objects.WaterLog;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    // BASE ID
    private static final long BASE_ID = 0x1011;

    // Reference to our owning screen (context)
    private Context mContext = null;

    // Reference to our collection
    private final ArrayList<WaterLog> mWaterLogs;

    // C-tor
    public CustomAdapter(Context _context, ArrayList<WaterLog> _waterLogs){
        mWaterLogs = _waterLogs;
        mContext = _context;
    }


    @Override
    public int getCount() {
        if(mWaterLogs != null){
            return mWaterLogs.size();
        }
        return 0;
    }

    @Override
    public WaterLog getItem(int position) {
        if(mWaterLogs != null && position >= 0 && position < mWaterLogs.size()){
            return mWaterLogs.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return BASE_ID + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;
        WaterLog waterLog = (WaterLog) getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_item, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }

        if(waterLog != null){
            vh.tv_time.setText(waterLog.getTime());
            vh.tv_measurement.setText(waterLog.getQuantity());
        }

        return convertView;
    }


    // Optimize with view holder!

    static class ViewHolder{
        TextView tv_time;
        TextView tv_measurement;



        public ViewHolder(View _layout){
            tv_time = _layout.findViewById(R.id.time_logged);
            tv_measurement = _layout.findViewById(R.id.water_amount);
        }
    }
}
