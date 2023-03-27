package com.example.myapplication.Settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

public class SettingsActivity extends PreferenceActivity {

    static Toolbar bar ;


            @Override
            protected void onCreate(final Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
                bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
                root.addView(bar, 0); // insert at top
                bar.setNavigationOnClickListener(v -> finish());
               FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.settings_fragment, root, false);
                root.addView(frameLayout,1);
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentPrefs()).commit();
            }
        }


