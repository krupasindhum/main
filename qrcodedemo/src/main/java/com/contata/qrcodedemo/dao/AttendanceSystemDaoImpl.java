package com.contata.qrcodedemo.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.contata.qrcodedemo.DatabaseConnectionUtil;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.sql.Date;

public class AttendanceSystemDaoImpl implements AttendanceSystemDao {


    public String insertTimeInData(String employeeId, String employeeAttNumber,Date attendenceFlagDate, String longitude, String latitude, String city, String state, String country) {
        String status="";
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            conn = DatabaseConnectionUtil.getInstance().getConnection();
            if (conn != null) {
                   System.out.println("hello"+conn);
                   // String sql="INSERT INTO MIMS_TEST.dbo.EmployeeAttendence (EmployeeId,EmployeeAttNumber,ManagerId,IsActive,Time_In,Time_Out,TimeSpent,AttendenceDate,CreatedBy,CreatedOn,ModifiedBy,ModifiedOn,InIPAddress,OutIPAddress,CrossOverDay,WFH,WFHStatus,CrossOverDayStatus,Remarks,ApprovedAuthority,ApprovedDate,longitude,latitude,city,state,country) VALUES ('342','PV256',NULL,NULL,'2011-04-07 09:15:28.250','2011-04-07 19:26:34.460',NULL,'2011-04-07 09:15:28.250',NULL,NULL,0,'2011-04-07 19:26:34.460','receptionc.cont','receptionc.cont',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);";
                   // stmt.executeUpdate(sql);
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
                    System.out.println("numberOfRowsInserted=" + numberOfRowsInserted);
                }else{
                status="Unable to connect Database";
            }
        } catch (SQLException ex) {
            status="Your In Time Failed To Update"+ex.getMessage();
        }
        return status;
    }


    public String insertTimeOutData(String employeeId, String employeeAttNumber,Date attendenceFlagDate, String longitude, String latitude, String city, String state, String country){
           String xyz="";
            return "success";
    }

    public String getEmployeTimeInTimeOutData(String employeeAttNumber,Date attendenceFlagDate) {
        String attendenceFlagDateData = null;
        try {
            Connection conn = DatabaseConnectionUtil.getInstance().getConnection();
            if (conn != null) {
                System.out.println("hello" + conn);
                String QUERY = "select * from MIMS_TEST.dbo.EmployeeAttendence where EmployeeAttNumber=" + employeeAttNumber + " and AttendenceFlagDate=" + attendenceFlagDate;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);

                while (rs.next()) {
                    //Display values
                    attendenceFlagDateData = rs.getString("Time_In") + "," + rs.getString("Time_Out") + "," + rs.getString("AttendenceFlagDate");
                    System.out.print("AttendenceFlagDate: " + attendenceFlagDateData);
                }
            }

        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
        return attendenceFlagDateData;
    }

}