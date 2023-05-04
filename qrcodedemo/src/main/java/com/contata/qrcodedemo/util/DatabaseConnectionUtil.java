package com.contata.qrcodedemo.util;

import java.sql.Connection;
import java.util.*;
import java.io.*;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Component
public class DatabaseConnectionUtil {

    private static DatabaseConnectionUtil dbIsntance;
    private static Connection con;
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionUtil.class);

    @Autowired
    private Environment env;

    private DatabaseConnectionUtil() {
        // private constructor //
    }

    public static DatabaseConnectionUtil getInstance() {
        if (dbIsntance == null) {
            dbIsntance = new DatabaseConnectionUtil();
        }
        return dbIsntance;
    }

    public Connection getConnection(Properties prop) {

        if (con == null) {
            try {
                String host = prop.getProperty("host");//"jdbc:sqlserver://KRUPASINDHUM-LA\\SQLEXPADV19K;databaseName=MIMS_TEST;encrypt=false;";
                String username = prop.getProperty("username");//"sa";
                String password = prop.getProperty("password");//"Bulu@2009";
                Class.forName(prop.getProperty("databasedrivername"));//"com.microsoft.sqlserver.jdbc.SQLServerDriver");
                DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
                logger.info("connection host::"+host);
                con = DriverManager.getConnection(host, username, password);
                logger.info("connection is::"+con);
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.info("failed to connect db :"+DatabaseConnectionUtil.class.getName()+ex.getMessage());
            }
        }

        return con;
    }
    private Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }
}