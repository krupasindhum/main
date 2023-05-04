package com.contata.qrcodedemo.service;

import com.contata.qrcodedemo.dao.AttendanceSystemDao;
import com.contata.qrcodedemo.dao.AttendanceSystemDaoImpl;
import java.sql.Date;
import com.contata.qrcodedemo.util.TimeInTimeOutDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;
import java.io.*;

@Service
public class AttendanceSystemServiceImpl implements AttendanceSystemService {

    public AttendanceSystemDao attendanceSystemDao=new AttendanceSystemDaoImpl();
    private static final Logger logger = LoggerFactory.getLogger(AttendanceSystemServiceImpl.class);
    public String insertTimeInTimeOutData(String requestType,String empID,String longitude,String latitude,String city,String state,String country,Properties prop){
        String status="";
        if(empID!=null) {
            empID = empID.trim();
            if (!empID.isEmpty()) {
                String employeeId = empID.substring(2, empID.length());
                String employeeAttNumber = empID;
                long millis = System.currentTimeMillis();
                java.sql.Date attendenceFlagDate = new java.sql.Date(millis);
                TimeInTimeOutDataUtil attendenceFlagDateData = attendanceSystemDao.getEmployeTimeInTimeOutData(employeeAttNumber, attendenceFlagDate,prop);
                if (requestType.equals("timeout")) {
                    if (attendenceFlagDateData == null) {
                        status = "Please do the Time in first.";
                    }else{
                        if (attendenceFlagDateData.getTime_Out_data() != null) {
                            logger.info("Time out data" + attendenceFlagDateData.getTime_In_data());
                            status = "Sorry! Re-Time out not allowed.";
                        }else{
                            status = attendanceSystemDao.insertTimeOutData(employeeAttNumber,attendenceFlagDate,prop);
                        }
                    }
                } else if (requestType.equals("timein")) {
                    if (attendenceFlagDateData == null || attendenceFlagDateData.getAttendenceFlagDate_data()== null) {
                        status = attendanceSystemDao.insertTimeInData(employeeId, employeeAttNumber, attendenceFlagDate, longitude, latitude, city, state, country,prop);
                    } else {
                        if (attendenceFlagDateData.getTime_In_data() != null) {
                            logger.info("Time In data" + attendenceFlagDateData.getTime_In_data());
                            status = "Sorry! Re-Time in not allowed.";
                        }else{
                            status = attendanceSystemDao.updateTimeInData(employeeAttNumber,attendenceFlagDate,prop);
                        }
                    }
                }
            } else {
                status = "employee Id is blank";
            }
          } else{
                status="employee Id is null";
          }
        logger.info("Dao response"+status);
        return  status;
    }
}