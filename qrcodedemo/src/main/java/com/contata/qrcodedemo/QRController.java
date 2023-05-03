package com.contata.qrcodedemo;

import java.awt.image.BufferedImage;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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

@RestController
public class QRController {

    public AttendanceSystemService attendanceSystemService=new AttendanceSystemServiceImpl();
    // get mapping and produce result as PNG image
    @GetMapping(path = "/api/v1/getqrcode", produces = MediaType.IMAGE_PNG_VALUE)
	public BufferedImage generateQRCodeImage(@RequestParam String url) throws Exception {
        //QRcode generator logic
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 250, 250);
        System.out.println("helloo qr code");
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}


	@GetMapping(path = "/attendance")
	public String generateApi(@RequestParam String requestType,@RequestParam String empID,@RequestParam String longitude,@RequestParam String latitude) throws Exception {
		System.out.println("Attendance type: "+requestType+" helloo qr code api call empID :"+empID+"longitude :"+longitude+"latitude :"+latitude);

		/*Connection conn = null;

		try {

			//String dbURL = "jdbc:sqlserver://KRUPASINDHUM-LA\\SQLEXPRESSSQLDB;databaseName=MIMS_TEST";
			//String user = "sa";
			//String pass = "Bulu@2009";
			//conn = DriverManager.getConnection(dbURL, user, pass);
			conn = DatabaseConnectionUtil.getInstance().getConnection();
			if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}*/
    String city="";
	String state="";
	String country="";
	   String status=attendanceSystemService.insertTimeInTimeOutData( requestType, empID, longitude, latitude, city, state, country);
	  System.out.println("Service response"+status);

	   return "succesfully "+requestType;
	}

}
