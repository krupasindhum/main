package com.contata.qrcodedemo.service;

import com.contata.qrcodedemo.dao.AttendanceSystemDao;
import com.contata.qrcodedemo.dao.AttendanceSystemDaoImpl;
import java.sql.Date;
import java.sql.Timestamp;
import com.contata.qrcodedemo.util.TimeInTimeOutDataUtil;
import com.contata.qrcodedemo.util.EmployeeDetailsUtil;
import com.contata.qrcodedemo.util.UserRequstTimeInTimeOutUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;
import java.io.*;
import java.math.BigDecimal;

@Service
public class AttendanceSystemServiceImpl implements AttendanceSystemService {

    public AttendanceSystemDao attendanceSystemDao=new AttendanceSystemDaoImpl();
    private static final Logger logger = LoggerFactory.getLogger(AttendanceSystemServiceImpl.class);
    public String insertTimeInTimeOutData(UserRequstTimeInTimeOutUtil userRequest,Properties prop){
        String status="";
        if(userRequest.getEmployeeId()!=null) {
            String empID= userRequest.getEmployeeId().trim();
            if (!empID.isEmpty()) {
                String employeeId = empID.substring(2, empID.length());
                String employeeAttNumber = empID.toUpperCase();
                EmployeeDetailsUtil employeeData = attendanceSystemDao.getEmployeeData(employeeAttNumber,prop);
                String employeeName="";
                if(employeeData!=null && employeeData.getFirstName()!=null && employeeData.getFirstName().isEmpty()){
                    employeeName=employeeName+employeeData.getFirstName();
                }
                if(employeeData!=null && employeeData.getMiddleName()!=null && employeeData.getMiddleName().isEmpty()){
                    employeeName=employeeName+employeeData.getMiddleName();
                }
                if(employeeData!=null && employeeData.getLastName()!=null && employeeData.getLastName().isEmpty()){
                    employeeName=employeeName+employeeData.getLastName();
                }
                if(employeeData!=null && employeeData.getEmployeeStatusId()!=3 && employeeData.getIsActive()==1) {
                    long millis = System.currentTimeMillis();
                    java.sql.Date attendenceFlagDate = new java.sql.Date(millis);
                    TimeInTimeOutDataUtil attendenceFlagDateData = attendanceSystemDao.getEmployeTimeInTimeOutData(employeeAttNumber, prop);
                    if (userRequest.getRequestType()!=null && userRequest.getRequestType().equals("timeout")) {
                        if (attendenceFlagDateData == null) {
                            status = "Please do the Time in first.";
                        } else {
                            if (attendenceFlagDateData.getTime_Out_data() != null) {
                                logger.info("Time out data" + attendenceFlagDateData.getTime_Out_data());
                                status = "Sorry! Re-Time out not allowed.";
                            } else {
                               long diff = new java.sql.Timestamp(new java.util.Date().getTime()).getTime() - attendenceFlagDateData.getTime_In_data().getTime();
                                int seconds = (int) diff / 1000;
                                BigDecimal timeSpent=BigDecimal.valueOf(seconds/3600);
                                logger.info("time spent"+timeSpent);
                               boolean attendaceInsertflag= attendanceSystemDao.insertTimeOutData(employeeAttNumber,timeSpent,userRequest.getInIPAddress(),userRequest.getOutIPAddress(), prop);
                                logger.info("EployeeAttendace table insert status"+attendaceInsertflag);
                                TimeInTimeOutDataUtil attendenceData = attendanceSystemDao.getEmployeTimeInTimeOutData(employeeAttNumber, prop);
                                Integer employeeAttendenceId=attendenceData.getEmployeeAttendenceId();
                                boolean attendaceLocationInsertflag = attendanceSystemDao.insertTimeLocationData(employeeAttendenceId,userRequest.getInIPAddress(),userRequest.getOutIPAddress(),userRequest.getDeviceType(),userRequest.getLatitude(),userRequest.getLongitude(),userRequest.getCity(),userRequest.getState(),userRequest.getCountry(),userRequest.getRequestType(), prop);
                                logger.info("EployeeAttendaceLocation table insert status"+attendaceLocationInsertflag);
                                if(attendaceInsertflag && attendaceLocationInsertflag){

                                    status=employeeName+"! Your Out Time Updated Successfully";
                                }else{
                                    status=employeeName+"! Your Out Time Failed To Update";
                                }
                            }
                        }
                    } else if (userRequest.getRequestType()!=null && userRequest.getRequestType().equals("timein")) {
                        if (attendenceFlagDateData == null || attendenceFlagDateData.getAttendenceFlagDate_data() == null) {
                            Integer managerId=employeeData.getManagerId();
                            boolean attendaceInsertflag = attendanceSystemDao.insertTimeInData(employeeId, employeeAttNumber,userRequest.getInIPAddress(),userRequest.getOutIPAddress(),managerId, prop);
                            logger.info("EployeeAttendace table insert status"+attendaceInsertflag);
                            TimeInTimeOutDataUtil attendenceData = attendanceSystemDao.getEmployeTimeInTimeOutData(employeeAttNumber, prop);
                            Integer employeeAttendenceId=attendenceData.getEmployeeAttendenceId();
                            boolean attendaceLocationInsertflag = attendanceSystemDao.insertTimeLocationData(employeeAttendenceId,userRequest.getInIPAddress(),userRequest.getOutIPAddress(),userRequest.getDeviceType(),userRequest.getLatitude(),userRequest.getLongitude(),userRequest.getCity(),userRequest.getState(),userRequest.getCountry(),userRequest.getRequestType(), prop);
                            logger.info("EployeeAttendaceLocation table insert status"+attendaceLocationInsertflag);
                            if(attendaceInsertflag && attendaceLocationInsertflag){
                                status=employeeName+"! In Time Updated Successfully";
                            }else{
                                status=employeeName+"! Your In Time Failed To Update";
                            }
                        } else {
                            if (attendenceFlagDateData.getTime_In_data() != null) {
                                logger.info("Time In data" + attendenceFlagDateData.getTime_In_data());
                                status = "Sorry! Re-Time in not allowed.";
                            } else {
                                boolean attendaceInsertflag = attendanceSystemDao.updateTimeInData(employeeAttNumber, prop);
                                if(attendaceInsertflag){
                                    status=employeeName+"! In Time Updated Successfully";
                                }else{
                                    status=employeeName+"! Your In Time Failed To Update";
                                }
                            }
                        }
                    }else{
                        status = "please provide a valid request type";
                    }
                }else{
                    status = "please provide a valid employee Id";
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