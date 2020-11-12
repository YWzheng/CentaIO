package com.example.centaio.net;

import java.util.List;

public class ResponseData {


    /**
     * code : 200
     * message : 成功
     * data : [{"iosidentification":"","keyId":"","parentKeyId":"","burPointName":"","identification":"","andIdentification":""}]
     */

    private int code;
    private String message;
    /**
     * iosidentification :
     * keyId :
     * parentKeyId :
     * burPointName :
     * identification :
     * andIdentification :
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String iosidentification;
        private String keyId;
        private String parentKeyId;
        private String burPointName;
        private String identification;
        private String andIdentification;

        public String getIosidentification() {
            return iosidentification;
        }

        public void setIosidentification(String iosidentification) {
            this.iosidentification = iosidentification;
        }

        public String getKeyId() {
            return keyId;
        }

        public void setKeyId(String keyId) {
            this.keyId = keyId;
        }

        public String getParentKeyId() {
            return parentKeyId;
        }

        public void setParentKeyId(String parentKeyId) {
            this.parentKeyId = parentKeyId;
        }

        public String getBurPointName() {
            return burPointName;
        }

        public void setBurPointName(String burPointName) {
            this.burPointName = burPointName;
        }

        public String getIdentification() {
            return identification;
        }

        public void setIdentification(String identification) {
            this.identification = identification;
        }

        public String getAndIdentification() {
            return andIdentification;
        }

        public void setAndIdentification(String andIdentification) {
            this.andIdentification = andIdentification;
        }
    }
}
