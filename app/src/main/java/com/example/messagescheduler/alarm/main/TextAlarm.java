package com.example.messagescheduler.alarm.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.TimeZone;

import com.example.messagescheduler.Constants;
import com.example.messagescheduler.db.main.ScheduledMessage;

import java.util.Calendar;
import java.util.Date;

public class TextAlarm extends BroadcastReceiver {

    /**
     * Send text when alarm is triggered, after being initialized
     * @param context - context
     * @param intent - intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {

    }

    /**
     * Create alarm to send texts on a recurring basis
     * @param context - context
     */
    public void setAlarm(Context context, ScheduledMessage message) {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = composeTextAlarmIntent(context, message);
        PendingIntent pi = PendingIntent.getBroadcast(context, message.getMessage_id(), intent, PendingIntent.FLAG_IMMUTABLE);
        // TODO - Need to calculate initial millis and set delay to 24 hours (for now)
        am.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), getFullDayInMillis(), pi);
    }

    /**
     * Cancel existing alarm so no more text messages are sent
     * @param context - context
     */
    public void cancelAlarm(Context context, ScheduledMessage message) {
        Intent intent = new Intent(context, TextAlarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, message.getMessage_id(), intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    private int getFullDayInMillis() {
        // 1000 millis - 1 sec
        // 60 secs - 1 min
        // 60 min - 1 hour
        // 24 hours - 1 day
        return 1000 * 60 * 60 * 24;
    }

    /**
     * Calculate time (in milliseconds) for first scheduled message
     * @param appTimeZone - time zone designated by user through UI
     * @param scheduledTime - time picked by user for message to send on
     * @return - time in milliseconds
     */
    private int calculateInitialTime(Constants.TimeZone appTimeZone, String scheduledTime) {

        TimeZone timeZone;
        switch(appTimeZone){
            case CT:
                timeZone = TimeZone.getTimeZone("America/Chicago");
            case ET:
                timeZone = TimeZone.getTimeZone("America/New_York");
            case MT:
                timeZone = TimeZone.getTimeZone("America/Phoenix");
            case PT:
                timeZone = TimeZone.getTimeZone("America/Los_Angeles");
            default:
                timeZone = TimeZone.getTimeZone("Etc/GMT");
                break;
        }

        //Get calendar instance with current time and then set to given hour and minute
        Calendar calendar = Calendar.getInstance();
        int hour = Integer.parseInt(scheduledTime.substring(0, 2));
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        int minute = Integer.parseInt(scheduledTime.substring(3));
        calendar.set(Calendar.MINUTE, minute);

        //If scheduled time is before current time, increment a day for initial scheduling
        if(calendar.getTime().before(Calendar.getInstance().getTime())) {
            calendar.add(Calendar.DATE, 1);
        }

        //Get milliseconds time and offset from UTC, then transform and return
        long timeInMillis = calendar.getTimeInMillis();

        return 1;
    }

    private Intent composeTextAlarmIntent(Context context, ScheduledMessage message) {
        Intent intent = new Intent(context, TextAlarm.class);
        intent.putExtra("requestCode", message.getMessage_id());
        intent.putExtra("messageText", message.getMessage());
        intent.putExtra("phoneNumber", message.getPhoneNumber());
        return intent;
    }

    private Intent composeSmsMessageIntent(ScheduledMessage message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra("sms_body", message.getMessage());
        return intent;
    }

}
