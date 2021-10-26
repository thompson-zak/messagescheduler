package com.example.messagescheduler.db.main;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.messagescheduler.Constants;

@Database(entities = { ScheduledMessage.class }, version = 1)
public abstract class SchedulerDatabase extends RoomDatabase {

    public abstract ScheduledMessageDAO getScheduledMessageDao();

    private static SchedulerDatabase schedulerDB;

    public static SchedulerDatabase getInstance(Context context) {
        if (null == schedulerDB) {
            schedulerDB = buildDatabaseInstance(context);
        }
        return schedulerDB;
    }

    private static SchedulerDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                SchedulerDatabase.class,
                Constants.databaseName)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        schedulerDB = null;
    }

}
