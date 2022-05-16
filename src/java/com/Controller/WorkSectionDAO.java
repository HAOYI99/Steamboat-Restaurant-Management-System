/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controller;

import com.Model.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IM10
 */
public class WorkSectionDAO {

    private final String SELECT_ALL_EW = "SELECT employeework.ewID, employeework.sectionID, "
            + "employeework.empID, employee.empName, worksection.sectionName, "
            + "worksection.sectionJob, employeework.duty from employeework "
            + "INNER JOIN worksection USING(sectionID) "
            + "INNER JOIN employee USING(empID)";
    private final String SELECT_EW = "SELECT employeework.ewID, employeework.sectionID, "
            + "employeework.empID, employee.empName, worksection.sectionName, "
            + "worksection.sectionJob, employeework.duty from employeework "
            + "INNER JOIN worksection USING(sectionID) "
            + "INNER JOIN employee USING(empID) WHERE ewID=?";
    private final String INSERT_EW = "INSERT INTO `employeework`(`sectionID`, `empID`, `duty`) VALUES (?, ?, ?)";
    private final String DELETE_EW = "DELETE FROM `employeework` WHERE ewID=?";
    private final String UPDATE_EW = "UPDATE `employeework` SET `sectionID`=?,`empID`=?,`duty`=? WHERE ewID=?";
    private final String SELECT_WS = "SELECT * FROM `worksection`";

    public boolean insertEmpWork(WorkSection workSection) throws SQLException {
        boolean result = false;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(INSERT_EW)) {

            ps.setInt(1, workSection.getSectionID());
            ps.setString(2, workSection.getEmpID());
            ps.setString(3, workSection.getDuty());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return result;
    }

    public boolean updateEmpWork(WorkSection workSection) throws SQLException {
        boolean rowUpdated;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE_EW)) {
            ps.setInt(1, workSection.getSectionID());
            ps.setString(2, workSection.getEmpID());
            ps.setString(3, workSection.getDuty());
            ps.setInt(4, workSection.getEwID());

            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteWorkSection(int ewID) throws SQLException {
        boolean rowDeleted;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE_EW)) {
            ps.setInt(1, ewID);
            rowDeleted = ps.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List<WorkSection> selectAllEmpWork() throws IOException {
        List<WorkSection> empWorkList = new ArrayList<>();
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_EW)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ewID = rs.getInt("ewID");
                int sectionID = rs.getInt("sectionID");
                String empID = rs.getString("empID");
                String empName = rs.getString("empName");
                String sectionName = rs.getString("sectionName");
                String sectionJob = rs.getString("sectionJob");
                String duty = rs.getString("duty");

                empWorkList.add(new WorkSection(ewID, sectionID, empID, empName, sectionName, sectionJob, duty));
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return empWorkList;
    }

    public WorkSection selectEmpWork(int ewID) throws IOException {
        WorkSection empWork = null;
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_EW)) {
            preparedStatement.setInt(1, ewID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int sectionID = rs.getInt("sectionID");
                String empID = rs.getString("empID");
                String empName = rs.getString("empName");
                String sectionName = rs.getString("sectionName");
                String sectionJob = rs.getString("sectionJob");
                String duty = rs.getString("duty");

                empWork = new WorkSection(ewID, sectionID, empID, empName, sectionName, sectionJob, duty);
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return empWork;
    }

    public List<WorkSection> selectAllWorkSection() throws IOException {
        List<WorkSection> workSections = new ArrayList<>();
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_WS)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int sectionID = rs.getInt("sectionID");
                String sectionName = rs.getString("sectionName");
                String sectionJob = rs.getString("sectionJob");

                workSections.add(new WorkSection(sectionName, sectionJob, sectionID));
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return workSections;
    }
}
