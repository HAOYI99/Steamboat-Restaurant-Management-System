/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Model;

/**
 *
 * @author IM10
 */
public class WorkSection {

    private int ewID;
    private int sectionID;
    private String empID;
    private String empName;
    private String sectionName;
    private String sectionJob;
    private String duty;

    public WorkSection() {
    }

    //work section
        public WorkSection(String sectionName, String sectionJob, int sectionID) {
            this.sectionID = sectionID;
            this.sectionName = sectionName;
            this.sectionJob = sectionJob;
        }

    //create use
    public WorkSection(int sectionID, String empID, String duty) {
        this.sectionID = sectionID;
        this.empID = empID;
        this.duty = duty;
    }

    //update use
    public WorkSection(int ewID, int sectionID, String empID, String duty) {
        this.ewID = ewID;
        this.sectionID = sectionID;
        this.empID = empID;
        this.duty = duty;
    }

    //default 
    public WorkSection(int ewID, int sectionID, String empID, String empName, String sectionName, String sectionJob, String duty) {
        this.ewID = ewID;
        this.sectionID = sectionID;
        this.empID = empID;
        this.empName = empName;
        this.sectionName = sectionName;
        this.sectionJob = sectionJob;
        this.duty = duty;
    }

    public int getEwID() {
        return ewID;
    }

    public void setEwID(int ewID) {
        this.ewID = ewID;
    }

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
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

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionJob() {
        return sectionJob;
    }

    public void setSectionJob(String sectionJob) {
        this.sectionJob = sectionJob;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

}
