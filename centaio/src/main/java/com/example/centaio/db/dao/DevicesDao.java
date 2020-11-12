package com.example.centaio.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.centaio.db.Constants;
import com.example.centaio.db.model.Devices;

import java.util.List;


@Dao
public interface DevicesDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME_DEVICES)
    List<Devices> getDevices();


    @Insert
    void insertDevices(Devices devices);


    @Update
    void updateDevices(Devices devices);

    @Query("DELETE FROM " + Constants.TABLE_NAME_DEVICES)
    void deleteAll();

    @Delete
    void deleteDevices(Devices devices);


    @Delete
    void deleteDevices(Devices... devices);

}
