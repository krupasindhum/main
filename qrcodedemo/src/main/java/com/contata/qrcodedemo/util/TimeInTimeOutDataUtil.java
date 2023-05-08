package com.contata.qrcodedemo.util;
import java.sql.Timestamp;
public class TimeInTimeOutDataUtil {
    private Timestamp Time_In_data;
    private Timestamp Time_Out_data;
    private String AttendenceFlagDate_data;

    private Integer employeeAttendenceId;

    public Timestamp getTime_In_data() {
        return Time_In_data;
    }

    public void setTime_In_data(Timestamp time_In_data) {
        Time_In_data = time_In_data;
    }

    public Timestamp getTime_Out_data() {
        return Time_Out_data;
    }

    public void setTime_Out_data(Timestamp time_Out_data) {
        Time_Out_data = time_Out_data;
    }

    public String getAttendenceFlagDate_data() {
        return AttendenceFlagDate_data;
    }

    public void setAttendenceFlagDate_data(String attendenceFlagDate_data) {
        AttendenceFlagDate_data = attendenceFlagDate_data;
    }

    public Integer getEmployeeAttendenceId() {
        return employeeAttendenceId;
    }

    public void setEmployeeAttendenceId(Integer employeeAttendenceId) {
        this.employeeAttendenceId = employeeAttendenceId;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "TimeInTimeOutDataUtil{" +
                "Time_In_data=" + Time_In_data +
                ", Time_Out_data=" + Time_Out_data +
                ", AttendenceFlagDate_data='" + AttendenceFlagDate_data + '\'' +
                ", employeeAttendenceId=" + employeeAttendenceId +
                '}';
    }
}
