/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controller;

import com.Model.Menu;
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
public class MenuDAO {

    private final String INSERT_MENU = "INSERT INTO `menu`(`menuID`, `menuName`, `menuDesc`, "
            + "`menuCategory`, `menuPrice`, `menuImage`, `menuAvailable`) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_MENU = "UPDATE `menu` SET `menuName`=?,`menuDesc`=?,"
            + "`menuCategory`=?,`menuPrice`=?,`menuAvailable`=? WHERE `menuID`=?";
    private final String UPDATE_MENU_IMG = "UPDATE `menu` SET `menuImage`=? WHERE `menuID`=?";
    private final String DELETE_MENU = "DELETE FROM `menu` WHERE `menuID`=?";
    private final String SELECT_ALL_MENUS = "SELECT * FROM `menu`";
    private final String SELECT_AVAILABLE_MENU = "SELECT * FROM `menu` WHERE `menuAvailable`='available'";
    private final String SELECT_MENU = "SELECT * FROM `menu` WHERE `menuID`=?";

    public boolean insertMenu(Menu menu) throws SQLException {
        boolean result = false;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(INSERT_MENU)) {

            ps.setString(1, menu.getMenuID());
            ps.setString(2, menu.getMenuName());
            ps.setString(3, menu.getMenuDesc());
            ps.setString(4, menu.getMenuCategory());
            ps.setDouble(5, menu.getMenuPrice());
            ps.setBlob(6, menu.getInputStreamImage());
            ps.setString(7, menu.getMenuAvailable());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return result;
    }

    public boolean updateMenu(Menu menu) throws SQLException {
        boolean rowUpdated;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE_MENU)) {

            ps.setString(1, menu.getMenuName());
            ps.setString(2, menu.getMenuDesc());
            ps.setString(3, menu.getMenuCategory());
            ps.setDouble(4, menu.getMenuPrice());
            ps.setString(5, menu.getMenuAvailable());
            ps.setString(6, menu.getMenuID());

            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean updateMenuImage(String menuID, InputStream menuImage) throws SQLException {
        boolean rowUpdated;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE_MENU_IMG)) {
            ps.setBlob(1, menuImage);
            ps.setString(2, menuID);
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteMenu(String menuID) throws SQLException {
        boolean rowDeleted;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE_MENU)) {
            ps.setString(1, menuID);
            rowDeleted = ps.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List<Menu> selectAllMenus() throws IOException {
        List<Menu> menus = new ArrayList<>();
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_MENUS)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String menuID = rs.getString("menuID");
                String menuName = rs.getString("menuName");
                String menuDesc = rs.getString("menuDesc");
                String menuCategory = rs.getString("menuCategory");
                double menuPrice = rs.getDouble("menuPrice");
                String menuImage = Util.convertImage(rs.getBlob("menuImage"));
                String menuAvailable = rs.getString("menuAvailable");

                menus.add(new Menu(menuID, menuName, menuDesc, menuCategory, menuPrice, menuImage, menuAvailable));
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return menus;
    }
    
    public List<Menu> selectAvailableMenu() throws IOException {
        List<Menu> menus = new ArrayList<>();
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_AVAILABLE_MENU)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String menuID = rs.getString("menuID");
                String menuName = rs.getString("menuName");
                String menuDesc = rs.getString("menuDesc");
                String menuCategory = rs.getString("menuCategory");
                double menuPrice = rs.getDouble("menuPrice");
                String menuImage = Util.convertImage(rs.getBlob("menuImage"));
                String menuAvailable = rs.getString("menuAvailable");

                menus.add(new Menu(menuID, menuName, menuDesc, menuCategory, menuPrice, menuImage, menuAvailable));
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return menus;
    }

    public Menu selectMenu(String menuID) throws IOException {
        Menu menu = null;
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_MENU)) {
            preparedStatement.setString(1, menuID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                
                String menuName = rs.getString("menuName");
                String menuDesc = rs.getString("menuDesc");
                String menuCategory = rs.getString("menuCategory");
                double menuPrice = rs.getDouble("menuPrice");
                String menuImage = Util.convertImage(rs.getBlob("menuImage"));
                String menuAvailable = rs.getString("menuAvailable");

                menu = new Menu(menuID, menuName, menuDesc, menuCategory, menuPrice, menuImage, menuAvailable);
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return menu;
    }

}
