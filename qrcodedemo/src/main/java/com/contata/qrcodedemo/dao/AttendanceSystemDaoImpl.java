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
import java.math.BigInteger;
import java.math.BigDecimal;
import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.io.*;

@Repository
public class AttendanceSystemDaoImpl implements AttendanceSystemDao {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceSystemDaoImpl.class);
    public String insertTimeInData(String employeeId, String employeeAttNumber,Date attendenceFlagDate, String longitude, String latitude, String city, String state, String country,Properties prop) {
        String status="";
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            conn = DatabaseConnectionUtil.getInstance().getConnection(prop);
            if (conn != null) {
                   logger.info("connection is established::"+conn);
                   int genericNullInt=0;
                    prepStmt=conn.prepareStatement("INSERT INTO MIMS_TEST.dbo.EmployeeAttendence (EmployeeId,EmployeeAttNumber,ManagerId,IsActive,Time_In,Time_Out,TimeSpent,AttendenceDate,CreatedBy,CreatedOn,ModifiedBy,ModifiedOn,InIPAddress,OutIPAddress,CrossOverDay,WFH,WFHStatus,CrossOverDayStatus,Remarks,ApprovedAuthority,ApprovedDate,longitude,latitude,city,state,country,AttendenceFlagDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    prepStmt.setString(1, employeeId);
                    prepStmt.setString(2, employeeAttNumber);
                    prepStmt.setInt(3,genericNullInt);
                    prepStmt.setInt(4,genericNullInt);
                    prepStmt.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
                    prepStmt.setTimestamp(6, null);
                    prepStmt.setBigDecimal(7,null);
                    prepStmt.setTimestamp(8, new java.sql.Timestamp(new java.util.Date().getTime()));
                    prepStmt.setString(9, employeeAttNumber);
                    prepStmt.setTimestamp(10, new java.sql.Timestamp(new java.util.Date().getTime()));
                    prepStmt.setString(11, employeeAttNumber);
                    prepStmt.setTimestamp(12, new java.sql.Timestamp(new java.util.Date().getTime()));
                    prepStmt.setString(13, "receptionc.cont");
                    prepStmt.setString(14, "receptionc.cont");
                    prepStmt.setInt(15,genericNullInt);
                    prepStmt.setInt(16,genericNullInt);
                    prepStmt.setString(17,null);
                    prepStmt.setString(18, null);
                    prepStmt.setString(19, null);
                    prepStmt.setString(20, null);
                    prepStmt.setDate(21,null);
                    prepStmt.setString(22, longitude);
                    prepStmt.setString(23, latitude);
                    prepStmt.setString(24, city);
                    prepStmt.setString(25, state);
                    prepStmt.setString(26, country);
                    prepStmt.setDate(27, attendenceFlagDate);
                    int numberOfRowsInserted = prepStmt.executeUpdate();
                    if(numberOfRowsInserted==1){
                        status="Your In Time Updated Successfully";
                    }else{
                        status="Your In Time Failed To Update";
                    }
                    logger.info("numberOfRowsInserted=" + numberOfRowsInserted);
                }else{
                status="Unable to connect Database";
            }
        } catch (SQLException ex) {
            logger.info("Your In Time Failed To Update"+ex.getMessage());
            status="Your In Time Failed To Update"+ex.getMessage();
        }
        return status;
    }


    public String insertTimeOutData(String employeeAttNumber,Date attendenceFlagDate,Properties prop){
        String status="";
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            conn = DatabaseConnectionUtil.getInstance().getConnection(prop);
            if (conn != null) {
                logger.info("connection is established::"+conn);
                prepStmt=conn.prepareStatement("update MIMS_TEST.dbo.EmployeeAttendence set Time_Out=? where EmployeeAttNumber=? and AttendenceFlagDate=?");
                prepStmt.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
                prepStmt.setString(2,employeeAttNumber);
                prepStmt.setDate(3, attendenceFlagDate);
                int numberOfRowsInserted = prepStmt.executeUpdate();
                if(numberOfRowsInserted==1){
                    status="Your Out Time Updated Successfully";
                }else{
                    status="Your Out Time Failed To Update";
                }
                logger.info("numberOfRowsInserted=" + numberOfRowsInserted);
            }else{
                status="Unable to connect Database";
            }
        } catch (SQLException ex) {
            logger.info("Your Out Time Failed To Update due to "+ex.getMessage());
            status="Your Out Time Failed To Update due to "+ex.getMessage();
        }
        return status;
    }

    public TimeInTimeOutDataUtil getEmployeTimeInTimeOutData(String employeeAttNumber,Date attendenceFlagDate,Properties prop) {
        TimeInTimeOutDataUtil attendenceFlagDateData = null;
        try {
            Connection conn = DatabaseConnectionUtil.getInstance().getConnection(prop);
            if (conn != null) {
                logger.info("connection is established::"+conn);
                String QUERY = "select * from MIMS_TEST.dbo.EmployeeAttendence where EmployeeAttNumber='" + employeeAttNumber + "' and AttendenceFlagDate='" + attendenceFlagDate+"'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);
                while (rs.next()) {
                    attendenceFlagDateData=new TimeInTimeOutDataUtil();
                    attendenceFlagDateData.setTime_In_data(rs.getString("Time_In"));
                    attendenceFlagDateData.setTime_Out_data(rs.getString("Time_Out"));
                    attendenceFlagDateData.setAttendenceFlagDate_data(rs.getString("AttendenceFlagDate"));
                    //attendenceFlagDateData = rs.getString("Time_In") + "," + rs.getString("Time_Out") + "," + rs.getString("AttendenceFlagDate");
                    logger.info("AttendenceFlagDate: " + attendenceFlagDateData.toString());
                }
            }

        }catch (Exception ex){
            logger.info(ex.getMessage());
        }
        return attendenceFlagDateData;
    }
    public String updateTimeInData(String employeeAttNumber,Date attendenceFlagDate,Properties prop){
        String status="";
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            conn = DatabaseConnectionUtil.getInstance().getConnection(prop);
            if (conn != null) {
                logger.info("connection is established::"+conn);
                prepStmt=conn.prepareStatement("update MIMS_TEST.dbo.EmployeeAttendence set Time_In=? where EmployeeAttNumber=? and AttendenceFlagDate=?");
                prepStmt.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
                prepStmt.setString(2,employeeAttNumber);
                prepStmt.setDate(3, attendenceFlagDate);
                int numberOfRowsInserted = prepStmt.executeUpdate();
                if(numberOfRowsInserted==1){
                    status="Your In Time Updated Successfully";
                }else{
                    status="Your In Time Failed To Update";
                }
                logger.info("numberOfRowsInserted=" + numberOfRowsInserted);
            }else{
                status="Unable to connect Database";
            }
        } catch (SQLException ex) {
            logger.info("Your In Time Failed To Update due to "+ex.getMessage());
            status="Your In Time Failed To Update due to "+ex.getMessage();
        }
        return status;
    }

}