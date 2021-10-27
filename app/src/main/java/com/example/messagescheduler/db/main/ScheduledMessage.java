package com.example.messagescheduler.db.main;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.messagescheduler.Constants;

import java.util.Objects;

@Entity
public class ScheduledMessage {

    @PrimaryKey(autoGenerate = true)
    private int message_id;

    private Constants.App app;

    private String message;

    private Constants.Frequency frequency;

    private String phoneNumber;

    private String scheduledTime;

    private Constants.TimeZone timeZone;

    public ScheduledMessage() {}

    public ScheduledMessage(int message_id, Constants.App app, String message, Constants.Frequency frequency, String phoneNumber, String scheduledTime, Constants.TimeZone timeZone) {
        this.message_id = message_id;
        this.app = app;
        this.message = message;
        this.frequency = frequency;
        this.phoneNumber = phoneNumber;
        this.scheduledTime = scheduledTime;
        this.timeZone = timeZone;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public Constants.App getApp() {
        return app;
    }

    public void setApp(Constants.App app) {
        this.app = app;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Constants.Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Constants.Frequency frequency) {
        this.frequency = frequency;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Constants.TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Constants.TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduledMessage that = (ScheduledMessage) o;
        return message_id == that.message_id && app == that.app && Objects.equals(message, that.message) && frequency == that.frequency && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(scheduledTime, that.scheduledTime) && timeZone == that.timeZone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message_id, app, message, frequency, phoneNumber, scheduledTime, timeZone);
    }

    @Override
    public String toString() {
        return "ScheduledMessage{" +
                "message_id=" + message_id +
                ", app=" + app +
                ", message='" + message + '\'' +
                ", frequency=" + frequency +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", scheduledTime=" + scheduledTime +
                ", timeZone=" + timeZone +
                '}';
    }
}
