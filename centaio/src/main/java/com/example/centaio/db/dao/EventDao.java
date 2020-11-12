package com.example.centaio.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.centaio.db.Constants;
import com.example.centaio.db.model.Event;

import java.util.List;


@Dao
public interface EventDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME_EVENT)
    List<Event> getEvent();


    @Insert
    void insertEvent(Event event);


    @Update
    void updateEvent(Event repos);


    @Query("DELETE FROM " + Constants.TABLE_NAME_EVENT)
    void deleteAll();

    @Delete
    void deleteEvent(Event event);


    @Delete
    void deleteEvent(Event... event);

}
