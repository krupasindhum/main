package com.contata.qrcodedemo.service;

import java.util.*;
import java.io.*;
import com.contata.qrcodedemo.util.UserRequstTimeInTimeOutUtil;
public interface AttendanceSystemService {

    public String insertTimeInTimeOutData(UserRequstTimeInTimeOutUtil userRequest,Properties prop);
}