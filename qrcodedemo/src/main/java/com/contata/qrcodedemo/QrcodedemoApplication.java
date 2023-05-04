package com.contata.qrcodedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.contata.qrcodedemo","com.contata.qrcodedemo.util", "com.contata.qrcodedemo.service","com.contata.qrcodedemo.dao"})
public class QrcodedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrcodedemoApplication.class, args);
	}

}
