package com.example.centaio.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Devices.class, Page.class, Event.class}, version = 1)
@TypeConverters({DateRoomConverter.class})
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase appDB;

    public abstract DevicesDao getDevicesDao();

    public abstract PageDao getPageDao();

    public abstract EventDao getEventDao();


    public static AppDataBase getInstance(Context context) {
        if (null == appDB) {
            appDB = buildDatabaseInstance(context);
        }
        return appDB;
    }

    private static AppDataBase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, Constants.DB_NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp() {
        appDB = null;
    }
}