package com.example.centaio.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;


@Dao
public interface EventDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME_EVENT)
    List<Event> getEvent();


    @Insert
    void insertEvent(Event event);


    @Update
    void updateEvent(Event repos);


    @Delete
    void deleteEvent(Event event);


    @Delete
    void deleteEvent(Event... event);

}
