package com.example.messagescheduler.alarm.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.messagescheduler.db.main.ScheduledMessage;

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
    public void setAlarm(Context context, ScheduledMessage message) throws Exception {

    }

    /**
     * Cancel existing alarm so no more text messages are sent
     * @param context - context
     */
    public void cancelAlarm(Context context) {

    }

    private Intent composeSmsMessageIntent(ScheduledMessage message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra("sms_body", message.getMessage());
        return intent;
    }

}
