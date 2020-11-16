package com.example.centaio.db.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.centaio.CentaIO;
import com.example.centaio.db.Constants;
import com.example.centaio.util.DateUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.io.Serializable;


@Entity(tableName = Constants.TABLE_NAME_PAGE)
public class Page implements Serializable {

    @Expose()
    @PrimaryKey(autoGenerate = true)
    private long page_id;

    private String title;//标题

    private String uploadType;

    private String happenTime;//页面打开时间

    private String webMonitorId;//项目id

    private String customerKey;//活跃ID

    private String uri;//页面名称

    private String userId;//用户ID

    private String deptId;//部门ID

    private String devicesInfo;//设备信息

    private String os;//统计平台

    public Page(String name) {
        this.title = name;
        this.uploadType = "CUSTOMER_PV";
        this.happenTime = DateUtils.getDateWithTime();
        this.webMonitorId = CentaIO.webMonitorId;
        this.uri = name;
        this.devicesInfo = new Gson().toJson(new Devices());
        this.os = "android";
        //TODO
        this.customerKey = "e292b2c6-7331-cf31-3cff-08d6e88f83ab";
        this.userId = "e292b2c6-7331-cf31-3cff-08d6e88f83ab";
        this.deptId = "0e70fb71-c4b4-41b3-8c6f-441d576d8a78";
    }

    public Page() {
    }

    public long getPage_id() {
        return page_id;
    }

    public void setPage_id(long page_id) {
        this.page_id = page_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    public String getWebMonitorId() {
        return webMonitorId;
    }

    public void setWebMonitorId(String webMonitorId) {
        this.webMonitorId = webMonitorId;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDevicesInfo() {
        return devicesInfo;
    }

    public void setDevicesInfo(String devicesInfo) {
        this.devicesInfo = devicesInfo;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
