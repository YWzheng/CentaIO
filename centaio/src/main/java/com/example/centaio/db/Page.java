package com.example.centaio.db;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity(tableName = Constants.TABLE_NAME_PAGE)
public class Page implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long page_id;

    private String name;

    private String title;

    private Date date;

    public Page(String name, String title) {
        this.name = name;
        this.title = title;
        this.date = new Date(System.currentTimeMillis());
    }

    @Ignore
    public Page() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return page_id == page.page_id &&
                Objects.equals(name, page.name) &&
                Objects.equals(title, page.title) &&
                Objects.equals(date, page.date);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(page_id, name, title, date);
    }

    
}
