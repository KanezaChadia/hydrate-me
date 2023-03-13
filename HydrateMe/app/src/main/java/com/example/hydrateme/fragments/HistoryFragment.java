package com.example.hydrateme.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.hydrateme.CustomAdapter;
import com.example.hydrateme.R;
import com.example.hydrateme.objects.WaterLog;

import java.util.ArrayList;

public class HistoryFragment extends ListFragment {

    private static final String ARG_DATA = "ARG_DATA";
    private static ArrayList<WaterLog> waterLogs = new ArrayList<>();


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HistoryFragment newInstance(ArrayList<WaterLog> waterLogs) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, waterLogs);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CustomAdapter customAdapter = null;
        if (getArguments() != null) {
            waterLogs = (ArrayList<WaterLog>) getArguments().getSerializable(ARG_DATA);
            customAdapter = new CustomAdapter(getContext(), waterLogs);
        }
        setListAdapter(customAdapter);
    }
}