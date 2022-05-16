/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Model;

import java.io.InputStream;

/**
 *
 * @author IM10
 */
public class Employee {

    private String empID;
    private String empName;
    private String empIC;
    private String empHPno;
    private String empAddress;
    private String empImage;
    private InputStream inputStreamImage;
    private String empEmail;
    private String empGender;
    private String empBank;
    private String empBankAcc;
    private double empBasicSalary;
    private String empBranch;
    private String empPosition;
    private String empHireDate;

    public Employee() {
    }
    
    //drop down list
    public Employee(String empID, String empName) {
        this.empID = empID;
        this.empName = empName;
    }

    //update use(without image)
    public Employee(String empID, String empName, String empIC, String empHPno, String empAddress, String empEmail, String empGender, String empBank, String empBankAcc, double empBasicSalary, String empBranch, String empPosition, String empHireDate) {
        this.empID = empID;
        this.empName = empName;
        this.empIC = empIC;
        this.empHPno = empHPno;
        this.empAddress = empAddress;
        this.empEmail = empEmail;
        this.empGender = empGender;
        this.empBank = empBank;
        this.empBankAcc = empBankAcc;
        this.empBasicSalary = empBasicSalary;
        this.empBranch = empBranch;
        this.empPosition = empPosition;
        this.empHireDate = empHireDate;
    }
    
    
    //create use
    public Employee(String empID, String empName, String empIC, String empHpno, String empAddress, InputStream inputStreamImage, String empEmail, String empGender, String empBank, String empBankAcc, double empBasicSalary, String empBranch, String empPosition, String empHireDate) {
        this.empID = empID;
        this.empName = empName;
        this.empIC = empIC;
        this.empHPno = empHpno;
        this.empAddress = empAddress;
        this.inputStreamImage = inputStreamImage;
        this.empEmail = empEmail;
        this.empGender = empGender;
        this.empBank = empBank;
        this.empBankAcc = empBankAcc;
        this.empBasicSalary = empBasicSalary;
        this.empBranch = empBranch;
        this.empPosition = empPosition;
        this.empHireDate = empHireDate;
    }

    //retrieve use
    public Employee(String empID, String empName, String empIC, String empHpno, String empAddress, String empImage, String empEmail, String empGender, String empBank, String empBankAcc, double empBasicSalary, String empBranch, String empPosition, String empHireDate) {
        this.empID = empID;
        this.empName = empName;
        this.empIC = empIC;
        this.empHPno = empHpno;
        this.empAddress = empAddress;
        this.empImage = empImage;
        this.empEmail = empEmail;
        this.empGender = empGender;
        this.empBank = empBank;
        this.empBankAcc = empBankAcc;
        this.empBasicSalary = empBasicSalary;
        this.empBranch = empBranch;
        this.empPosition = empPosition;
        this.empHireDate = empHireDate;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpIC() {
        return empIC;
    }

    public void setEmpIC(String empIC) {
        this.empIC = empIC;
    }

    public String getEmpHPno() {
        return empHPno;
    }

    public void setEmpHPno(String empHPno) {
        this.empHPno = empHPno;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpImage() {
        return empImage;
    }

    public void setEmpImage(String empImage) {
        this.empImage = empImage;
    }

    public InputStream getInputStreamImage() {
        return inputStreamImage;
    }

    public void setInputStreamImage(InputStream inputStreamImage) {
        this.inputStreamImage = inputStreamImage;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpGender() {
        return empGender;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }

    public String getEmpBank() {
        return empBank;
    }

    public void setEmpBank(String empBank) {
        this.empBank = empBank;
    }

    public String getEmpBankAcc() {
        return empBankAcc;
    }

    public void setEmpBankAcc(String empBankAcc) {
        this.empBankAcc = empBankAcc;
    }

    public double getEmpBasicSalary() {
        return empBasicSalary;
    }

    public void setEmpBasicSalary(double empBasicSalary) {
        this.empBasicSalary = empBasicSalary;
    }

    public String getEmpBranch() {
        return empBranch;
    }

    public void setEmpBranch(String empBranch) {
        this.empBranch = empBranch;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition;
    }

    public String getEmpHireDate() {
        return empHireDate;
    }

    public void setEmpHireDate(String empHireDate) {
        this.empHireDate = empHireDate;
    }

}
