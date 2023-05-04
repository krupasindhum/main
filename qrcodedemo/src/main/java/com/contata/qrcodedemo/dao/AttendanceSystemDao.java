package com.contata.qrcodedemo.dao;
import java.sql.Date;
import com.contata.qrcodedemo.util.TimeInTimeOutDataUtil;
import java.util.*;
import java.io.*;
public interface AttendanceSystemDao {

    public String insertTimeInData(String employeeId, String employeeAttNumber,Date attendenceFlagDate, String longitude, String latitude, String city, String state, String country,Properties prop);
    public String insertTimeOutData(String employeeAttNumber,Date attendenceFlagDate,Properties prop);
    public TimeInTimeOutDataUtil getEmployeTimeInTimeOutData(String employeeAttNumber,Date attendenceFlagDate,Properties prop);
    public String updateTimeInData(String employeeAttNumber,Date attendenceFlagDate,Properties prop);
}