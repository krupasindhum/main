package com.contata.qrcodedemo.dao;
import java.sql.Date;
public interface AttendanceSystemDao {

    public String insertTimeInData(String employeeId, String employeeAttNumber,Date attendenceFlagDate, String longitude, String latitude, String city, String state, String country);
    public String insertTimeOutData(String employeeId, String employeeAttNumber,Date attendenceFlagDate, String longitude, String latitude, String city, String state, String country);
    public String getEmployeTimeInTimeOutData(String employeeAttNumber,Date attendenceFlagDate);
}