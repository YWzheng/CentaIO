package com.example.centaio.db.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.centaio.db.Constants;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName = Constants.TABLE_NAME_EVENT)
public class Event implements Serializable {

    @Expose()
    @PrimaryKey(autoGenerate = true)
    private long event_id;

    private String page;//事件所在页面

    private String name;//事件名称

    private String type;//事件类型

    private Date time;//事件时间


    public Event(String page, String name, String type) {
        this.page = page;
        this.name = name;
        this.type = type;
        this.time = new Date(System.currentTimeMillis());
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
