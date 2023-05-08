package com.contata.qrcodedemo;

import java.awt.image.BufferedImage;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.contata.qrcodedemo.service.AttendanceSystemService;
import com.contata.qrcodedemo.service.AttendanceSystemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import java.util.*;
import java.io.*;
import com.contata.qrcodedemo.util.UserRequstTimeInTimeOutUtil;

@RestController
public class QRController {

    public AttendanceSystemService attendanceSystemService=new AttendanceSystemServiceImpl();
	private static final Logger logger = LoggerFactory.getLogger(QRController.class);
	@Autowired
	private Environment env;
    // get mapping and produce result as PNG image
    @GetMapping(path = "/getqrcode", produces = MediaType.IMAGE_PNG_VALUE)
	public BufferedImage generateQRCodeImage(@RequestParam String url) throws Exception {
        //QRcode generator logic
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 250, 250);
		logger.info("your qr code is ready");
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}


	@GetMapping(path = "/attendance")
	public String updateAttendance(@RequestParam String requestType,@RequestParam String empID,@RequestParam String longitude,@RequestParam String latitude) throws Exception {
		logger.info("Attendance type: "+requestType+"empID :"+empID+"longitude :"+longitude+"latitude :"+latitude);
		//logger.info("connection host:"+env.getProperty("datasource.url"));
		String city="";
		String state="";
		String country="";
		String inIPAddress="";
		String outIPAddress="";
		String deviceType=" ";
		Properties prop=new Properties();
		prop.setProperty("host",env.getProperty("datasource.url"));
		prop.setProperty("username",env.getProperty("datasource.username"));
		prop.setProperty("password",env.getProperty("datasource.password"));
		prop.setProperty("databasedrivername",env.getProperty("datasource.databasedrivername"));
		prop.setProperty("databasename",env.getProperty("datasource.databasename"));
		String status="";//attendanceSystemService.insertTimeInTimeOutData( requestType, empID, longitude, latitude, city, state, country,inIPAddress,outIPAddress,deviceType,prop);
		logger.info("Service response"+status);
	    return status;
	}
	@PostMapping(path = "/attendancesystem",consumes = "application/json")
	public String updateAttendance(@RequestBody UserRequstTimeInTimeOutUtil userRequest){
        logger.info("userRequest data"+userRequest.toString());
		Properties prop=new Properties();
		prop.setProperty("host",env.getProperty("datasource.url"));
		prop.setProperty("username",env.getProperty("datasource.username"));
		prop.setProperty("password",env.getProperty("datasource.password"));
		prop.setProperty("databasedrivername",env.getProperty("datasource.databasedrivername"));
		prop.setProperty("databasename",env.getProperty("datasource.databasename"));
		String status=attendanceSystemService.insertTimeInTimeOutData(userRequest,prop);
		logger.info("Service response"+status);
		return status;
	}
}
