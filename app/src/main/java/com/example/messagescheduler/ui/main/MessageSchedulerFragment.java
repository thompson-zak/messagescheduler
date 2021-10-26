package com.example.messagescheduler.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.example.messagescheduler.Constants;
import com.example.messagescheduler.R;
import com.example.messagescheduler.Constants.*;
import com.example.messagescheduler.db.main.ScheduledMessage;
import com.example.messagescheduler.db.main.SchedulerDatabase;

import java.time.LocalDateTime;

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
                ScheduledMessage message = new ScheduledMessage();

                Constants.App app = Constants.App.valueOf(((Spinner)v.findViewById(R.id.messagingAppSelector)).getSelectedItem().toString());
                message.setApp(app);

                String messageText = v.findViewById(R.id.messageText).toString();
                message.setMessage(messageText);

                Constants.Frequency frequency = Constants.Frequency.valueOf(((Spinner)v.findViewById(R.id.frequencySelector)).getSelectedItem().toString());
                message.setFrequency(frequency);

                TimePicker timePicker = (TimePicker)v.findViewById(R.id.scheduledTimePicker);
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                LocalDateTime scheduledTime = LocalDateTime.of(0,0,0,hour,minute);
                message.setScheduledTime(scheduledTime);

                Constants.TimeZone timeZone = Constants.TimeZone.valueOf(((Spinner)v.findViewById(R.id.timeZoneSelector)).getSelectedItem().toString());
                message.setTimeZone(timeZone);

                SchedulerDatabase db = SchedulerDatabase.getInstance(getActivity());

                db.getScheduledMessageDao().insert(message);
            }
        });

        return v;
    }
}