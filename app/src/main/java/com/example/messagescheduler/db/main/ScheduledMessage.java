package com.example.messagescheduler.db.main;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.messagescheduler.Constants;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ScheduledMessage {

    @PrimaryKey
    private int message_id;

    private Constants.App app;

    private String message;

    private Constants.Frequency frequency;

    private LocalDateTime scheduledTime;

    private Constants.TimeZone timeZone;

    public ScheduledMessage(int message_id, Constants.App app, String message, Constants.Frequency frequency, LocalDateTime scheduledTime, Constants.TimeZone timeZone) {
        this.message_id = message_id;
        this.app = app;
        this.message = message;
        this.frequency = frequency;
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

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
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
        return message_id == that.message_id && app == that.app && Objects.equals(message, that.message) && frequency == that.frequency && Objects.equals(scheduledTime, that.scheduledTime) && timeZone == that.timeZone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message_id, app, message, frequency, scheduledTime, timeZone);
    }

    @Override
    public String toString() {
        return "ScheduledMessage{" +
                "message_id=" + message_id +
                ", app=" + app +
                ", message='" + message + '\'' +
                ", frequency=" + frequency +
                ", scheduledTime=" + scheduledTime +
                ", timeZone=" + timeZone +
                '}';
    }
}
