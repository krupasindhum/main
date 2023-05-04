package com.contata.qrcodedemo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnectionUtil {

    private static DatabaseConnectionUtil dbIsntance;
    private static Connection con;
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionUtil.class);



    private DatabaseConnectionUtil() {
        // private constructor //
    }

    public static DatabaseConnectionUtil getInstance() {
        if (dbIsntance == null) {
            dbIsntance = new DatabaseConnectionUtil();
        }
        return dbIsntance;
    }

    public Connection getConnection() {

        if (con == null) {
            try {
                String host = "jdbc:sqlserver://KRUPASINDHUM-LA\\SQLEXPADV19K;databaseName=MIMS_TEST;encrypt=false;";
                String username = "sa";
                String password = "Bulu@2009";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
                con = DriverManager.getConnection(host, username, password);
            } catch (Exception ex) {
                logger.info(DatabaseConnectionUtil.class.getName()+ex.getMessage());
            }
        }

        return con;
    }
}