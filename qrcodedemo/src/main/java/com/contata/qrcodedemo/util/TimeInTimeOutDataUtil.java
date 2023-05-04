package com.contata.qrcodedemo.util;
public class TimeInTimeOutDataUtil {
    public String Time_In_data;
    public String Time_Out_data;
    public String AttendenceFlagDate_data;

    public String getTime_In_data() {
        return Time_In_data;
    }

    public void setTime_In_data(String time_In_data) {
        Time_In_data = time_In_data;
    }

    public String getTime_Out_data() {
        return Time_Out_data;
    }

    public void setTime_Out_data(String time_Out_data) {
        Time_Out_data = time_Out_data;
    }

    public String getAttendenceFlagDate_data() {
        return AttendenceFlagDate_data;
    }

    public void setAttendenceFlagDate_data(String attendenceFlagDate_data) {
        AttendenceFlagDate_data = attendenceFlagDate_data;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "TimeInTimeOutDataUtil{" +
                "Time_In_data='" + Time_In_data + '\'' +
                ", Time_Out_data='" + Time_Out_data + '\'' +
                ", AttendenceFlagDate_data='" + AttendenceFlagDate_data + '\'' +
                '}';
    }
}
