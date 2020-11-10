package com.example.centaio.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = Constants.TABLE_NAME_DEVICES)
public class Devices implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long devices_id;

    private String name;//设备型号

    private String sysVersion;//系统版本

    private String w_and_h;//设备宽高

    private String UUID;//UUid;

    private String appVersion;//app版本

    private String appCode;//appCode;

    public Devices(String name, String sysVersion, String w_and_h, String UUID, String appVersion, String appCode) {
        this.name = name;
        this.sysVersion = sysVersion;
        this.w_and_h = w_and_h;
        this.UUID = UUID;
        this.appVersion = appVersion;
        this.appCode = appCode;
    }

    public long getDevices_id() {
        return devices_id;
    }

    public void setDevices_id(long devices_id) {
        this.devices_id = devices_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public String getW_and_h() {
        return w_and_h;
    }

    public void setW_and_h(String w_and_h) {
        this.w_and_h = w_and_h;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }


}
