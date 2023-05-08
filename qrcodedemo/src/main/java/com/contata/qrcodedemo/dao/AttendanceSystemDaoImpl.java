package com.contata.qrcodedemo.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.contata.qrcodedemo.util.DatabaseConnectionUtil;
import com.contata.qrcodedemo.util.TimeInTimeOutDataUtil;
import com.contata.qrcodedemo.util.EmployeeDetailsUtil;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.io.*;
import java.math.BigDecimal;

@Repository
public class AttendanceSystemDaoImpl implements AttendanceSystemDao {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceSystemDaoImpl.class);
    public boolean insertTimeInData(String employeeId, String employeeAttNumber,String inIPAddress,String outIPAddress,Integer managerId,Properties prop) {
        boolean status=false;
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            conn = DatabaseConnectionUtil.getInstance().getConnection(prop);
            if (conn != null) {
                   logger.info("connection is established::"+conn);
                   int genericNullInt=0;
                    prepStmt=conn.prepareStatement("INSERT INTO MIMS_TEST.dbo.EmployeeAttendence (EmployeeId,EmployeeAttNumber,ManagerId,IsActive,Time_In,Time_Out,TimeSpent,AttendenceDate,CreatedBy,CreatedOn,ModifiedBy,ModifiedOn,InIPAddress,OutIPAddress,CrossOverDay,WFH,WFHStatus,CrossOverDayStatus,Remarks,ApprovedAuthority,ApprovedDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    prepStmt.setString(1, employeeId);
                    prepStmt.setString(2, employeeAttNumber);
                    prepStmt.setInt(3,managerId);
                    prepStmt.setInt(4,1);
                    prepStmt.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
                    prepStmt.setTimestamp(6, null);
                    prepStmt.setBigDecimal(7,null);
                    prepStmt.setTimestamp(8, new java.sql.Timestamp(new java.util.Date().getTime()));
                    prepStmt.setString(9, employeeAttNumber);
                    prepStmt.setTimestamp(10, new java.sql.Timestamp(new java.util.Date().getTime()));
                    prepStmt.setString(11, employeeAttNumber);
                    prepStmt.setTimestamp(12, new java.sql.Timestamp(new java.util.Date().getTime()));
                    prepStmt.setString(13, inIPAddress);
                    prepStmt.setString(14, outIPAddress);
                    prepStmt.setInt(15,genericNullInt);
                    prepStmt.setInt(16,genericNullInt);
                    prepStmt.setString(17,null);
                    prepStmt.setString(18, null);
                    prepStmt.setString(19, null);
                    prepStmt.setString(20, null);
                    prepStmt.setDate(21,null);
                    int numberOfRowsInserted = prepStmt.executeUpdate();
                    if(numberOfRowsInserted==1){
                        status=true;
                    }else{
                        status=false;
                        logger.info("Your In Time Failed To Update numberOfRowsInserted=" + numberOfRowsInserted);
                    }
                    logger.info("Your In Time Updated Successfully numberOfRowsInserted=" + numberOfRowsInserted);
                }else{
                status=false;
                logger.info("Unablto conneect Database");
            }
        } catch (SQLException ex) {
            logger.info("Your In Time Failed To Update"+ex.getMessage());
            status=false;
        }
        return status;
    }


    public boolean insertTimeOutData(String employeeAttNumber,BigDecimal timeSpent,String inIPAddress,String outIPAddress,Properties prop){
        boolean status=false;
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            conn = DatabaseConnectionUtil.getInstance().getConnection(prop);
            if (conn != null) {
                logger.info("connection is established::"+conn);
                prepStmt=conn.prepareStatement("update MIMS_TEST.dbo.EmployeeAttendence set Time_Out=?,TimeSpent=?,ModifiedBy=?,ModifiedOn=?,InIPAddress=?,OutIPAddress=? where EmployeeAttNumber=? and CONVERT(DATE, AttendenceDate)=  CONVERT(DATE, GETDATE());");
                prepStmt.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
                prepStmt.setBigDecimal(2,timeSpent);
                prepStmt.setString(3,employeeAttNumber);
                prepStmt.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
                prepStmt.setString(5,inIPAddress);
                prepStmt.setString(6,outIPAddress);
                prepStmt.setString(7,employeeAttNumber);
                int numberOfRowsInserted = prepStmt.executeUpdate();
                if(numberOfRowsInserted==1){
                    status=true;
                    logger.info("Your Out Time Updated Successfully numberOfRowsInserted=" + numberOfRowsInserted);
                }else{
                    status=false;
                    logger.info("Your Out Time Failed To Update numberOfRowsInserted=" + numberOfRowsInserted);
                }

            }else{
                status=false;
                logger.info("Unable to connect Database");
            }
        } catch (SQLException ex) {
            logger.info("Your Out Time Failed To Update due to "+ex.getMessage());
            status=false;
        }
        return status;
    }

    public TimeInTimeOutDataUtil getEmployeTimeInTimeOutData(String employeeAttNumber,Properties prop) {
        TimeInTimeOutDataUtil attendenceFlagDateData = null;
        try {
            Connection conn = DatabaseConnectionUtil.getInstance().getConnection(prop);
            if (conn != null) {
                logger.info("connection is established::"+conn);
                String QUERY = "select * from MIMS_TEST.dbo.EmployeeAttendence where EmployeeAttNumber='" + employeeAttNumber + "'and CONVERT(DATE, AttendenceDate)=  CONVERT(DATE, GETDATE());";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);
                while (rs.next()) {
                    attendenceFlagDateData=new TimeInTimeOutDataUtil();
                    attendenceFlagDateData.setTime_In_data(rs.getTimestamp("Time_In"));
                    attendenceFlagDateData.setTime_Out_data(rs.getTimestamp("Time_Out"));
                    attendenceFlagDateData.setAttendenceFlagDate_data(rs.getString("AttendenceDate"));
                    attendenceFlagDateData.setEmployeeAttendenceId(rs.getInt("EmployeeAttendenceId"));
                    logger.info("AttendenceFlagDate: " + attendenceFlagDateData.toString());
                }
            }

        }catch (Exception ex){
            logger.info(ex.getMessage());
        }
        return attendenceFlagDateData;
    }
    public boolean updateTimeInData(String employeeAttNumber,Properties prop){
        boolean status=false;
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            conn = DatabaseConnectionUtil.getInstance().getConnection(prop);
            if (conn != null) {
                logger.info("connection is established::"+conn);
                prepStmt=conn.prepareStatement("update MIMS_TEST.dbo.EmployeeAttendence set Time_In=? where EmployeeAttNumber=? and  CONVERT(DATE, AttendenceDate)=  CONVERT(DATE, GETDATE());");
                prepStmt.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
                prepStmt.setString(2,employeeAttNumber);
                int numberOfRowsInserted = prepStmt.executeUpdate();
                if(numberOfRowsInserted==1){
                    logger.info("Your In Time Updated Successfully numberOfRowsInserted=" + numberOfRowsInserted);
                    status=true;
                }else{
                    logger.info("Your In Time Failed To Update numberOfRowsInserted=" + numberOfRowsInserted);
                    status=false;
                }
            }else{
                logger.info("Unable to connect Database");
                status=false;
            }
        } catch (SQLException ex) {
            logger.info("Your In Time Failed To Update due to "+ex.getMessage());
            status=false;
        }
        return status;
    }
    public EmployeeDetailsUtil getEmployeeData(String employeeAttNumber,Properties prop){
        EmployeeDetailsUtil employeeData = null;
        try {
            Connection conn = DatabaseConnectionUtil.getInstance().getConnection(prop);
            if (conn != null) {
                logger.info("connection is established::"+conn);
                String QUERY = "select * from MIMS_TEST.dbo.Employee  where EmployeeNumber='" + employeeAttNumber + "';";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);
                while (rs.next()) {
                    employeeData=new EmployeeDetailsUtil();
                    employeeData.setEmployeeNumber(rs.getString("EmployeeNumber"));
                    employeeData.setEmployeeStatusId(rs.getInt("EmployeeStatusId"));
                    employeeData.setIsActive(rs.getInt("IsActive"));
                    employeeData.setManagerId(rs.getInt("ManagerId"));
                    employeeData.setFirstName(rs.getString("FirstName"));
                    employeeData.setMiddleName(rs.getString("MiddleName"));
                    employeeData.setLastName(rs.getString("LastName"));
                    logger.info("employeeData: " + employeeData.toString());
                }
            }

        }catch (Exception ex){
            logger.info(ex.getMessage());
        }
        return employeeData;
    }

    public  boolean insertTimeLocationData(Integer employeeAttendenceId,String inIPAddress,String outIPAddress,String deviceType,String latitude,String longitude,String city,String state,String country,String requestType, Properties prop){
        boolean status=false;
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            conn = DatabaseConnectionUtil.getInstance().getConnection(prop);
            if (conn != null) {
                logger.info("connection is established::"+conn);
                Integer genericNullInt=null;
                prepStmt=conn.prepareStatement("INSERT INTO MIMS_TEST.dbo.EmployeeAttendanceLocation (EmployeeAttendenceId,InIPAddress,OutIPAddress,longitude,latitude,city,state,country,enteryType,deviceType) VALUES (?,?,?,?,?,?,?,?,?,?)");
                prepStmt.setInt(1, employeeAttendenceId);
                prepStmt.setString(2, inIPAddress);
                prepStmt.setString(3, outIPAddress);
                prepStmt.setString(4, longitude);
                prepStmt.setString(5, latitude);
                prepStmt.setString(6, city);
                prepStmt.setString(7, state);
                prepStmt.setString(8, country);
                prepStmt.setString(9, requestType);
                prepStmt.setString(10, deviceType);
                int numberOfRowsInserted = prepStmt.executeUpdate();
                if(numberOfRowsInserted==1){
                    status=true;
                    logger.info("Your In Time Updated Successfully numberOfRowsInserted=" + numberOfRowsInserted);
                }else{
                    logger.info("Your In Time Failed To Update numberOfRowsInserted=" + numberOfRowsInserted);
                    status=false;
                }

            }else{
                logger.info("Unable to connect Database");
                status=false;
            }
        } catch (SQLException ex) {
            logger.error("Your In Time Failed To Update"+ex.getMessage());
            status=false;
        }
        return status;
    }

}