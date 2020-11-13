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

    private String deviceName;//设备型号

    private String deviceId;//devicesID;

    private String deviceVendor;//设备品牌

    private String deviceModel;//设备名称

    private String deviceOS;//统计平台

    private String deviceOSVersion;//系统版本

    private String deviceIP;//设备IP

    public Devices() {
        this.deviceName = DeviceUtils.getDeviceName();
        this.deviceId = DeviceUtils.getAndroidId(CentaIO.application);
        this.deviceVendor = DeviceUtils.getDeviceVendor();
        this.deviceModel = DeviceUtils.getDeviceModel();
        this.deviceOSVersion = DeviceUtils.getOsVersion();
        this.deviceOS = DeviceUtils.getPlatform();
        this.deviceIP = DeviceUtils.getLocalIpAddress(CentaIO.application);
    }

    public long getDevices_id() {
        return devices_id;
    }

    public void setDevices_id(long devices_id) {
        this.devices_id = devices_id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceOSVersion() {
        return deviceOSVersion;
    }

    public void setDeviceOSVersion(String deviceOSVersion) {
        this.deviceOSVersion = deviceOSVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public String getDeviceVendor() {
        return deviceVendor;
    }

    public void setDeviceVendor(String deviceVendor) {
        this.deviceVendor = deviceVendor;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
}
