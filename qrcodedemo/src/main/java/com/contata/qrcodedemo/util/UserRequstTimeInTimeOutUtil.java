package com.contata.qrcodedemo.util;
public class UserRequstTimeInTimeOutUtil {
    private String requestType;
    private String employeeId;
    private String longitude="";
    private String latitude="";
    private String city="";
    private String state="";
    private String country="";
    private String inIPAddress="";
    private String outIPAddress="";
    private String deviceType="";

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInIPAddress() {
        return inIPAddress;
    }

    public void setInIPAddress(String inIPAddress) {
        this.inIPAddress = inIPAddress;
    }

    public String getOutIPAddress() {
        return outIPAddress;
    }

    public void setOutIPAddress(String outIPAddress) {
        this.outIPAddress = outIPAddress;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "UserRequstTimeInTimeOutUtil{" +
                "requestType='" + requestType + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", inIPAddress='" + inIPAddress + '\'' +
                ", outIPAddress='" + outIPAddress + '\'' +
                ", deviceType='" + deviceType + '\'' +
                '}';
    }
}