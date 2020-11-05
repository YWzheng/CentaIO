package com.example.centaio.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /*
    获取带日期的时间
     */
    public String getDateWithTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterTime = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        Date curDateTime = new Date(System.currentTimeMillis());//获取当前时间
        return formatterTime.format(curDateTime);
    }

    /*
    获取格式化日期
     */
    public String getFormatDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }
}
