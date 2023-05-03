package com.contata.qrcodedemo.service;

import com.contata.qrcodedemo.dao.AttendanceSystemDao;
import com.contata.qrcodedemo.dao.AttendanceSystemDaoImpl;
import java.sql.Date;


public class AttendanceSystemServiceImpl implements AttendanceSystemService {

    public AttendanceSystemDao attendanceSystemDao=new AttendanceSystemDaoImpl();
    public String insertTimeInTimeOutData(String requestType,String empID,String longitude,String latitude,String city,String state,String country){
        String status="";
        if(requestType.equals("timeout")){
          //  status=attendanceSystemDao.insertTimeOutData( requestType, empID, longitude, latitude, city, state, country);
        }else if(requestType.equals("timein")){
            if(empID!=null){
                empID=empID.trim();
                if(!empID.isEmpty()){
                    String employeeId=empID.substring(2,empID.length());
                    String employeeAttNumber=empID;
                    long millis=System.currentTimeMillis();
                    java.sql.Date attendenceFlagDate=new java.sql.Date(millis);
                    String attendenceFlagDateData=attendanceSystemDao.getEmployeTimeInTimeOutData(employeeAttNumber,attendenceFlagDate);
                    if(attendenceFlagDateData==null){
                        status=attendanceSystemDao.insertTimeInData(employeeId, employeeAttNumber,attendenceFlagDate, longitude, latitude, city, state, country);
                    }else{

                    }

                }else{
                    status="employee Id is blank";
                }
            }else{
                status="employee Id is null";
            }

        }

        System.out.println("Dao response"+status);
        return  status;
    }
}