package com.contata.qrcodedemo.dao;
import java.sql.Date;
import com.contata.qrcodedemo.util.TimeInTimeOutDataUtil;
import com.contata.qrcodedemo.util.EmployeeDetailsUtil;
import java.util.*;
import java.io.*;
import java.math.BigDecimal;
public interface AttendanceSystemDao {

    public boolean insertTimeInData(String employeeId, String employeeAttNumber,String inIPAddress,String outIPAddress,Integer managerId,Properties prop);
    public boolean insertTimeOutData(String employeeAttNumber,BigDecimal timeSpent,String inIPAddress,String outIPAddress,Properties prop);
    public TimeInTimeOutDataUtil getEmployeTimeInTimeOutData(String employeeAttNumber,Properties prop);
    public EmployeeDetailsUtil getEmployeeData(String employeeAttNumber,Properties prop);
    public boolean updateTimeInData(String employeeAttNumber,Properties prop);
    public  boolean insertTimeLocationData(Integer employeeAttendenceId,String inIPAddress,String outIPAddress,String deviceType,String latitude,String longitude,String city,String state,String country,String requestType, Properties prop);
}