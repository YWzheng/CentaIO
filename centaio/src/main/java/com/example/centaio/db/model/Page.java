package com.example.centaio.db.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.centaio.db.Constants;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName = Constants.TABLE_NAME_PAGE)
public class Page implements Serializable {

    @Expose()
    @PrimaryKey(autoGenerate = true)
    private long page_id;

    private String name;//页面名称

    private String path;//页面路径

    private String from;//上一级页面

    private Date time;//页面打开时间

    public Page(String name, String path, String from) {
        this.name = name;
        this.path = path;
        this.from = from;
        this.time = new Date(System.currentTimeMillis());
    }

    public long getPage_id() {
        return page_id;
    }

    public void setPage_id(long page_id) {
        this.page_id = page_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
