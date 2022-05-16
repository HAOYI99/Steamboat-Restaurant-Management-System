/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controller;

import com.Model.Employee;
import com.Model.Util;
import java.io.IOException;
import java.io.InputStream;
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
public class EmployeeDAO {

    private final String INSERT_EMPLOYEE
            = "INSERT INTO `employee`(`empID`, `empName`, `empIC`, `empHPno`, "
            + "`empAddress`, `empImage`, `empEmail`, `empGender`, `empBank`, "
            + "`empBankAcc`, `empBasicSalary`, `empBranch`, `empPosition`, `empHireDate`) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_EMPLOYEE
            = "UPDATE `employee` SET `empName`=?,`empIC`=?,`empHPno`=?,"
            + "`empAddress`=?,`empEmail`=?,`empGender`=?,`empBank`=?,`empBankAcc`=?,"
            + "`empBasicSalary`=?,`empBranch`=?,`empPosition`=?,`empHireDate`=? WHERE `empID`=?";
    private final String UPDATE_EMP_IMG = "UPDATE `employee` SET `empImage`=? WHERE `empID`=?";
    private final String DELETE_EMPLOYEE = "DELETE FROM `employee` WHERE `empID`=?";
    private final String SELECT_ALL_EMPLOYEES = "SELECT * FROM `employee`";
    private final String SELECT_EMPLOYEE = "SELECT * FROM `employee` WHERE `empID`=?";
    private final String SELECT_NAME = "SELECT `empID`, `empName` FROM `employee`";
    
    public boolean insertEmployee(Employee employee) throws SQLException {
        boolean result = false;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(INSERT_EMPLOYEE)) {

            ps.setString(1, employee.getEmpID());
            ps.setString(2, employee.getEmpName());
            ps.setString(3, employee.getEmpIC());
            ps.setString(4, employee.getEmpHPno());
            ps.setString(5, employee.getEmpAddress());
            ps.setBlob(6, employee.getInputStreamImage());
            ps.setString(7, employee.getEmpEmail());
            ps.setString(8, employee.getEmpGender());
            ps.setString(9, employee.getEmpBank());
            ps.setString(10, employee.getEmpBankAcc());
            ps.setDouble(11, employee.getEmpBasicSalary());
            ps.setString(12, employee.getEmpBranch());
            ps.setString(13, employee.getEmpPosition());
            ps.setString(14, employee.getEmpHireDate());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return result;
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE_EMPLOYEE)) {

            ps.setString(1, employee.getEmpName());
            ps.setString(2, employee.getEmpIC());
            ps.setString(3, employee.getEmpHPno());
            ps.setString(4, employee.getEmpAddress());
            ps.setString(5, employee.getEmpEmail());
            ps.setString(6, employee.getEmpGender());
            ps.setString(7, employee.getEmpBank());
            ps.setString(8, employee.getEmpBankAcc());
            ps.setDouble(9, employee.getEmpBasicSalary());
            ps.setString(10, employee.getEmpBranch());
            ps.setString(11, employee.getEmpPosition());
            ps.setString(12, employee.getEmpHireDate());
            ps.setString(13, employee.getEmpID());

            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean updateEmpImage(String empID, InputStream empImage) throws SQLException {
        boolean rowUpdated;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE_EMP_IMG)) {
            ps.setBlob(1, empImage);
            ps.setString(2, empID);
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteEmployee(String empID) throws SQLException {
        boolean rowDeleted;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE_EMPLOYEE)) {
            ps.setString(1, empID);
            rowDeleted = ps.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List<Employee> selectAllEmployees() throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_EMPLOYEES)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String empID = rs.getString("empID");
                String empName = rs.getString("empName");
                String empIC = rs.getString("empIC");
                String empHPno = rs.getString("empHPno");
                String empAddress = rs.getString("empAddress");

                String empImage = Util.convertImage(rs.getBlob("empImage"));

                String empEmail = rs.getString("empEmail");
                String empGender = rs.getString("empGender");
                String empBank = rs.getString("empBank");
                String empBankAcc = rs.getString("empBankAcc");
                double empBasicSalary = rs.getDouble("empBasicSalary");
                String empBranch = rs.getString("empBranch");
                String empPosition = rs.getString("empPosition");
                String empHireDate = rs.getString("empHireDate");

                employees.add(new Employee(empID, empName, empIC, empHPno, empAddress,
                        empImage, empEmail, empGender, empBank, empBankAcc,
                        empBasicSalary, empBranch, empPosition, empHireDate));
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return employees;
    }

    public Employee selectEmployee(String empID) throws IOException {
        Employee employee = null;
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_EMPLOYEE)) {
            preparedStatement.setString(1, empID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String empName = rs.getString("empName");
                String empIC = rs.getString("empIC");
                String empHPno = rs.getString("empHPno");
                String empAddress = rs.getString("empAddress");

                String empImage = Util.convertImage(rs.getBlob("empImage"));

                String empEmail = rs.getString("empEmail");
                String empGender = rs.getString("empGender");
                String empBank = rs.getString("empBank");
                String empBankAcc = rs.getString("empBankAcc");
                double empBasicSalary = rs.getDouble("empBasicSalary");
                String empBranch = rs.getString("empBranch");
                String empPosition = rs.getString("empPosition");
                String empHireDate = rs.getString("empHireDate");

                employee = new Employee(empID, empName, empIC, empHPno, empAddress,
                        empImage, empEmail, empGender, empBank, empBankAcc,
                        empBasicSalary, empBranch, empPosition, empHireDate);
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return employee;
    }
    
    public List<Employee> selectEmpName() throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_NAME)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String empID = rs.getString("empID");
                String empName = rs.getString("empName");

                employees.add(new Employee(empID, empName));
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return employees;
    }
}
