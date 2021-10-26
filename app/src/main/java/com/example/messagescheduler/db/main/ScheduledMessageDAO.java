package com.example.messagescheduler.db.main;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.messagescheduler.Constants;

import java.util.List;

@Dao
public interface ScheduledMessageDAO {

    @Query("SELECT * FROM " + Constants.messageTableName)
    List<ScheduledMessage> getAll();

    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    void insert(ScheduledMessage message);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void update(ScheduledMessage message);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void delete(ScheduledMessage message);

    /*
     * delete list of objects from database
     * @param note, array of objects to be deleted
     */
    @Delete
    void delete(ScheduledMessage... message);      // Note... is varargs, here note is an array

}
