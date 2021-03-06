package com.example.messagescheduler.ui.main;

import static java.net.Proxy.Type.HTTP;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.messagescheduler.Constants;
import com.example.messagescheduler.R;
import com.example.messagescheduler.Constants.*;
import com.example.messagescheduler.alarm.main.TextAlarm;
import com.example.messagescheduler.db.main.ScheduledMessage;
import com.example.messagescheduler.db.main.SchedulerDatabase;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fragment containing a form used to schedule new messages
 */
public class MessageSchedulerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_scheduler, viewGroup, false);

        //Create dropdown menu for selecting which app to schedule messages through
        Spinner appsDropdown = v.findViewById(R.id.messagingAppSelector);
        String[] apps = new String[]{App.Text.name(), App.WhatsApp.name()};
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

        EditText messageTextComponent = v.findViewById(R.id.messageText);
        EditText phoneNumberComponent = v.findViewById(R.id.phoneNumber);
        TimePicker timePicker = (TimePicker)v.findViewById(R.id.scheduledTimePicker);

        //Save message when schedule button is clicked
        Button button = (Button) v.findViewById(R.id.scheduleMessageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ScheduledMessage message = new ScheduledMessage();

                Constants.App app = Constants.App.valueOf(appsDropdown.getSelectedItem().toString());
                message.setApp(app);

                String messageText = messageTextComponent.getText().toString();
                message.setMessage(messageText);

                Constants.Frequency frequency = Constants.Frequency.valueOf(frequencyDropdown.getSelectedItem().toString());
                message.setFrequency(frequency);

                String phoneNumber = phoneNumberComponent.getText().toString();
                message.setPhoneNumber(phoneNumber);

                String hour = String.valueOf(timePicker.getHour());
                String minute = String.valueOf(timePicker.getMinute());

                if(hour.length() == 1) {
                    hour = "0" + hour;
                }

                if(minute.length() == 1) {
                    minute = "0" + minute;
                }

                String scheduledTime = hour + ":" + minute;
                message.setScheduledTime(scheduledTime);

                Constants.TimeZone timeZone = Constants.TimeZone.valueOf(timeZoneDropdown.getSelectedItem().toString());
                message.setTimeZone(timeZone);

                if(!validateSchedulingRequest(message)) {
                    return;
                }

                if(enqueueWorkManagerJob(message)) {
                    //Only load message into DB if the work request is successfully scheduled
                    SchedulerDatabase db = SchedulerDatabase.getInstance(getActivity());
                    db.getScheduledMessageDao().insert(message);
                } else {
                    //TODO - throw error or alert dialog for failure to enqueue work
                }

                //Clear all text fields after successful scheduling
                messageTextComponent.setText("");
                phoneNumberComponent.setText("");
            }
        });

        return v;
    }

    private boolean enqueueWorkManagerJob(ScheduledMessage message) {

        Constants.Frequency frequency = message.getFrequency();

        // TODO - expand frequency options
        switch(frequency) {
            case Daily:
                TextAlarm textAlarm = new TextAlarm();
                try {
                    textAlarm.setAlarm(this.requireContext(), message);
                } catch (Exception e) {
                    return false;
                }
                break;
        }

        return true;
    }

    private boolean validateSchedulingRequest(ScheduledMessage scheduledMessage) {

        //WhatsApp support is not enabled due to user input requirements for sending a message
        if(scheduledMessage.getApp().equals(App.WhatsApp)) {
            showAlertDialog("WhatsApp is currently not supported.");
            return false;
        }

        //Do not allow empty or null messages
        String messageText = scheduledMessage.getMessage();
        if(messageText == null || messageText.isEmpty()) {
            showAlertDialog("Message cannot be empty.");
            return false;
        }

        //Very basic check to ensure phone number is only numbers
        String phoneNumber = scheduledMessage.getPhoneNumber();
        Pattern p = Pattern.compile("[0-9]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(phoneNumber);
        boolean matches = m.find();
        if(!matches) {
            showAlertDialog("The phone number you've entered is invalid.");
            return false;
        }

        return true;
    }

    private void showAlertDialog(String warningText) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Issue With Scheduling Request")
                .setMessage(warningText)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}