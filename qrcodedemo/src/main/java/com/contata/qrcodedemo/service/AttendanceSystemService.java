package com.contata.qrcodedemo.service;

import java.util.*;
import java.io.*;
public interface AttendanceSystemService {

    public String insertTimeInTimeOutData(String requestType,String empID,String longitude,String latitude,String city,String state,String country,Properties prop);
}