package com.contata.qrcodedemo.util;
public class EmployeeDetailsUtil {
    private String employeeNumber;
    private Integer employeeStatusId;
    private Integer isActive;
    private Integer managerId;

    private String firstName="";
    private String middleName="";
    private String lastName="";

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Integer getEmployeeStatusId() {
        return employeeStatusId;
    }

    public void setEmployeeStatusId(Integer employeeStatusId) {
        this.employeeStatusId = employeeStatusId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "EmployeeDetailsUtil{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", employeeStatusId=" + employeeStatusId +
                ", isActive=" + isActive +
                ", managerId=" + managerId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}