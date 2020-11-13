package com.example.centaio.db.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.centaio.CentaIO;
import com.example.centaio.db.Constants;
import com.example.centaio.util.DateUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.io.Serializable;


@Entity(tableName = Constants.TABLE_NAME_EVENT)
public class Event implements Serializable {

    @Expose()
    @PrimaryKey(autoGenerate = true)
    private long event_id;


    /**
     * uploadType : ELE_BEHAVIOR
     * behaviorType : click
     * happenTime : 2020-11-12 17:48:43.466378
     * webMonitorId : 0b0910cc-1f61-4ea8-8f21-ddc71829fe73
     * customerKey : edfbd09e-573d-4346-80ca-69cc23dd9ebb
     * uri : CentaIOUtil
     * userId : b210c6f1-8d69-cc03-9b78-08d3546d5618
     * deptId : 09060209-4224-82cf-ebba-a488302a1cb6
     * devicesInfo : {"deviceName":"vivo X21A","deviceId":"7552279f86fedec9","deviceVendor":"vivo","deviceModel":"PD1728","deviceOS":"android","deviceOSVersion":"9","deviceIP":"192.168.1.105"}
     * os : android
     * burPointName : 租赁
     * burPointId : 79b462a5-73b8-4733-a115-77c8b3f6bc36
     * innerText : house_zl
     * title : CentaIOUtil
     * completeUrl : null
     */

    private String uploadType;//上传类型
    private String behaviorType;//行为类型
    private String happenTime;//时间
    private String webMonitorId;//项目ID
    private String customerKey;//活跃ID
    private String uri;//页面
    private String userId;//用户ID
    private String deptId;//部门ID
    private String devicesInfo;//设备信息
    private String os;//统计平台
    private String burPointName;//埋点名称
    private String burPointId;//埋点ID
    private String innerText;//按钮文字
    private String title;//标题
    private String completeUrl;//完整路径

//    @Ignore
//    public Event() {
//    }

    public Event(String uri, String innerText, String title, String completeUrl) {
        this.uploadType = "ELE_BEHAVIOR";
        this.behaviorType = "click";
        this.happenTime = DateUtils.getDateWithTime();
        this.webMonitorId = CentaIO.webMonitorId;
        this.customerKey = customerKey;
        this.uri = uri;
        this.userId = userId;
        this.deptId = deptId;
        this.devicesInfo = new Gson().toJson(new Devices());
        this.os = "android";
        this.burPointName = burPointName;
        this.burPointId = burPointId;
        this.innerText = innerText;
        this.title = title;
        this.completeUrl = completeUrl;
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(String behaviorType) {
        this.behaviorType = behaviorType;
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

    public String getBurPointName() {
        return burPointName;
    }

    public void setBurPointName(String burPointName) {
        this.burPointName = burPointName;
    }

    public String getBurPointId() {
        return burPointId;
    }

    public void setBurPointId(String burPointId) {
        this.burPointId = burPointId;
    }

    public String getInnerText() {
        return innerText;
    }

    public void setInnerText(String innerText) {
        this.innerText = innerText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompleteUrl() {
        return completeUrl;
    }

    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }
}
