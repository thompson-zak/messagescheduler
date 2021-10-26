package com.example.messagescheduler.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.messagescheduler.R;
import com.example.messagescheduler.Constants.*;

/**
 * Fragment containing a form used to schedule new messages
 */
public class MessageSchedulerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_scheduler, viewGroup, false);

        //Create dropdown menu for selecting which app to schedule messages through
        Spinner appsDropdown = v.findViewById(R.id.messagingAppSelector);
        String[] apps = new String[]{App.WhatsApp.name()};
        ArrayAdapter<String> appsAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, apps);
        appsDropdown.setAdapter(appsAdapter);

        //Create dropdown menu for selecting the frequency for the scheduled message
        Spinner frequencyDropdown = v.findViewById(R.id.frequencySelector);
        String[] frequencies = new String[]{Frequency.Daily.name()};
        ArrayAdapter<String> frequencyAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, frequencies);
        frequencyDropdown.setAdapter(frequencyAdapter);

        //Create dropdown menu for selecting the time zone for the scheduled message
        Spinner timeZoneDropdown = v.findViewById(R.id.timeZoneSelector);
        String[] timeZones = new String[]{TimeZone.PT.name(), TimeZone.MT.name(), TimeZone.CT.name(), TimeZone.ET.name()};
        ArrayAdapter<String> timeZoneAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, timeZones);
        timeZoneDropdown.setAdapter(timeZoneAdapter);

        //Save message when schedule button is clicked
        Button button = (Button) v.findViewById(R.id.scheduleMessageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        return v;
    }
}