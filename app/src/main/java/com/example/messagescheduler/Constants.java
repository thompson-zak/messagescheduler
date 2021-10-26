package com.example.messagescheduler;

public class Constants {

    public enum App {
        WhatsApp
    }

    public enum Frequency {
        Daily
    }

    public enum TimeZone {
        PT, MT, CT, ET
    }

    final public static String messageTableName = "scheduledMessage";
    final public static String databaseName = "schedulerDatabase";
}
