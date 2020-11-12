package com.example.centaio.db.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.centaio.CentaIO;
import com.example.centaio.db.Constants;
import com.example.centaio.util.DeviceUtils;
import com.google.gson.annotations.Expose;

import java.io.Serializable;


@Entity(tableName = Constants.TABLE_NAME_DEVICES)
public class Devices implements Serializable {

    @Expose()
    @PrimaryKey(autoGenerate = true)
    private long devices_id;

    private String deviceOS;//统计平台

    private String sysVersion;//系统版本

    private String name;//设备型号

    private String appVersion;//app版本

    private String appCode;//appCode;

    private String w_and_h;//设备宽高

    private String devicesID;//devicesID;

    private String netWorkType;//网络状态

    private String deviceIP;//设备IP


    public Devices() {
        this.name = DeviceUtils.getDeviceModel();
        this.sysVersion = DeviceUtils.getOsVersion();
        this.w_and_h = DeviceUtils.getScreenWidth(CentaIO.application) + "*" + DeviceUtils.getScreenHeight(CentaIO.application);
        this.appVersion = DeviceUtils.getAppVersionName(CentaIO.application);
        this.appCode = DeviceUtils.getAppVersionCode(CentaIO.application);
        this.deviceOS = DeviceUtils.getPlatform();
        this.netWorkType = DeviceUtils.getNetworkType(CentaIO.application);
        this.deviceIP = DeviceUtils.getLocalIpAddress(CentaIO.application);
        this.devicesID=DeviceUtils.getAndroidId(CentaIO.application);
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

    public String getDevicesID() {
        return devicesID;
    }

    public void setDevicesID(String devicesID) {
        this.devicesID = devicesID;
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

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }

    public String getDeviceIP() {
        return deviceIP;
    }

    public void setDeviceIP(String deviceIP) {
        this.deviceIP = deviceIP;
    }

    public String getNetWorkType() {
        return netWorkType;
    }

    public void setNetWorkType(String netWorkType) {
        this.netWorkType = netWorkType;
    }
}
