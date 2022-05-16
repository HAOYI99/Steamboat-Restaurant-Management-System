/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controller;

import com.Model.*;
import com.Model.Util;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IM10
 */
public class OrderDAO {

    private final String CREATE_ORDER = "INSERT INTO `orderlist`(`menuID`, `quantity`, `tableNo`) VALUES (?, ?, ?)";
    private final String SELECT_ORDER_LIST = "SELECT `menu`.`menuID`, orderlist.orderID, "
            + "`menu`.`menuName`, orderlist.quantity , `menu`.`menuPrice`, "
            + "`menu`.`menuPrice`*`orderlist`.`quantity` AS total_price FROM orderlist "
            + "INNER JOIN menu using(menuID) WHERE orderlist.status='ordered' and orderlist.tableNo=?";
    private final String SHOW_ORDER_BASED_ON_TABLE = "SELECT `menu`.`menuID`, orderlist.orderID, "
            + "`menu`.`menuName`, orderlist.quantity , `menu`.`menuPrice`, "
            + "`menu`.`menuPrice`*`orderlist`.`quantity` AS total_price FROM orderlist "
            + "INNER JOIN menu using(menuID) WHERE orderlist.status='processing' and orderlist.tableNo=?";
    private final String DELETE_ORDER = "DELETE FROM `orderlist` WHERE `orderID`=?";
    private final String CONFIRM_ORDER = "UPDATE `orderlist` SET `timeStamp`=?,`status`='processing' WHERE `status`='ordered' and `tableNo`=?";
    private final String CONFIRMED_ORDERLIST = "SELECT `menu`.`menuName`, orderlist.quantity FROM `orderlist` "
            + "INNER JOIN `menu` USING(menuID) WHERE `status`='processing' AND `tableNo`=?";
    private final String CANCEL_ONE_ORDER = "UPDATE `orderlist` SET `status`='cancelled' WHERE `orderID`=? and `tableNo`=?";
    private final String CANCEL_ALL_ORDER = "UPDATE `orderlist` SET `status`='cancelled' WHERE `tableNo`=?";

    private final String GET_TOTAL_PRICE = "SELECT orderlist.tableNo, SUM(`menu`.`menuPrice`*`orderlist`.`quantity`) AS total_price "
            + "FROM orderlist INNER JOIN menu USING(menuID) WHERE `orderlist`.`status`<>'paid' AND "
            + "`orderlist`.`status`<>'cancelled' AND `orderlist`.`tableNo`=?";
    private final String COMPLETE_PAYMENT = "UPDATE `orderlist` SET `status`='paid' WHERE `status`='processing' AND `tableNo`=?";
    private final String GET_PAYMENT_DETAIL = "SELECT `menu`.`menuName`, orderlist.quantity , `menu`.`menuPrice`, "
            + "`menu`.`menuPrice`*`orderlist`.`quantity` AS total_price FROM orderlist INNER JOIN menu using(menuID) "
            + "WHERE orderlist.status='processing' and orderlist.tableNo=?";
    private final String GENERATE_RECEIPT_DATA = "INSERT INTO `receipt`(`ref_No`, `balance`, `paymentAmount`) VALUES (?, ?, ?)";

    public boolean insertMenu(Order order) throws SQLException {
        boolean result = false;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(CREATE_ORDER)) {

            ps.setString(1, order.getMenuID());
            ps.setInt(2, order.getQuantity());
            ps.setString(3, order.getTableNo());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return result;
    }

    public List<Order> selectOrderList(String tableID) throws IOException {
        List<Order> orderlist = new ArrayList<>();
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_ORDER_LIST)) {
            preparedStatement.setString(1, tableID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                int orderID = Integer.parseInt(rs.getString("orderID"));
                String menuID = rs.getString("menuID");
                String menuName = rs.getString("menuName");
                double menuPrice = rs.getDouble("menuPrice");
                double totalprice = rs.getDouble("total_price");
                int quantity = Integer.parseInt(rs.getString("quantity"));

                orderlist.add(new Order(orderID, menuID, menuName, menuPrice, totalprice, quantity, tableID));
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return orderlist;
    }

    public boolean deleteOrder(int orderID) throws SQLException {
        boolean rowDeleted;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE_ORDER)) {
            ps.setInt(1, orderID);
            rowDeleted = ps.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean confirmOrder(String tableNo) throws SQLException {
        boolean rowUpdated;
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(CONFIRM_ORDER)) {
            ps.setString(1, timeStamp);
            ps.setString(2, tableNo);
            rowUpdated = ps.executeUpdate() > 0;
        }
        printOrderList(timeStamp, tableNo);
        return rowUpdated;
    }

    public void printOrderList(String timestamp, String tableNo) {

        List<Order> orderlist = new ArrayList<>();
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(CONFIRMED_ORDERLIST)) {
            preparedStatement.setString(1, tableNo);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                String menuName = rs.getString("menuName");
                int quantity = Integer.parseInt(rs.getString("quantity"));

                orderlist.add(new Order(menuName, quantity, timestamp, tableNo));
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }

        String filepath = "C:\\Users\\IM10\\Desktop\\SEM4\\CSE3999\\FinalSRMS\\web\\asset\\";
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filepath + timestamp + tableNo + ".pdf"));
            document.open();
            String line = new String(new char[130]).replace('\0', '-');
            String header
                    = "                                                                 SRMS SYSTEM\n"
                    + "                                                         Restaurant Ori Thai Mookata\n"
                    + "\n"
                    + "              Order Time: " + timestamp + "                                      Table No : " + tableNo + "\n"
                    + "                                                                    Start of Order\n"
                    + line + "\n"
                    + "                                   Food                                                             Quantity\n";

            String footer = line + "\n"
                    + "                                                                    End of Order";
            document.add(new Paragraph(header));
            document.add(new Paragraph(line));
            for (Order i : orderlist) {
                String food = i.getMenuName();
                int quantity = i.getQuantity();
                document.add(new Paragraph(String.format("                            %-60s\n", food)));
                document.add(new Paragraph(String.format("%110d\n", quantity)));
            }
            document.add(new Paragraph(footer));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            System.err.println(e);
        }
    }

    public List<Order> showCustomerOrder(String tableID) throws IOException {
        List<Order> orderlist = new ArrayList<>();
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(SHOW_ORDER_BASED_ON_TABLE)) {
            preparedStatement.setString(1, tableID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                int orderID = Integer.parseInt(rs.getString("orderID"));
                String menuID = rs.getString("menuID");
                String menuName = rs.getString("menuName");
                double menuPrice = rs.getDouble("menuPrice");
                double totalprice = rs.getDouble("total_price");
                int quantity = Integer.parseInt(rs.getString("quantity"));

                orderlist.add(new Order(orderID, menuID, menuName, menuPrice, totalprice, quantity, tableID));
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return orderlist;
    }

    public boolean cancelOneOrder(int orderID, String tableID) throws SQLException {
        boolean rowUpdated;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(CANCEL_ONE_ORDER)) {
            ps.setInt(1, orderID);
            ps.setString(2, tableID);
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean cancelAllOrder(String tableID) throws SQLException {
        boolean rowUpdated;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(CANCEL_ALL_ORDER)) {
            ps.setString(1, tableID);
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public double getReceiptTotalPrice(String tableID) throws IOException {
        double menuPrice = 0;
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(GET_TOTAL_PRICE)) {
            preparedStatement.setString(1, tableID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                menuPrice = rs.getDouble("total_price");
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return menuPrice;
    }

    public List<Order> getReceiptList(String tableID) throws IOException {
        List<Order> receiptlist = new ArrayList<>();
        try (Connection con = Util.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(GET_PAYMENT_DETAIL)) {
            preparedStatement.setString(1, tableID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                String menuName = rs.getString("menuName");
                int quantity = Integer.parseInt(rs.getString("quantity"));
                double menuPrice = rs.getDouble("menuPrice");
                double totalprice = rs.getDouble("total_price");

                receiptlist.add(new Order(menuName, menuPrice, totalprice, quantity));
            }
        } catch (SQLException e) {
            Util.printSQLException(e);
        }
        return receiptlist;
    }

    public boolean completePayment(String tableNo) throws SQLException {
        boolean rowUpdated;
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(COMPLETE_PAYMENT)) {
            ps.setString(1, tableNo);
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public void generateReceipt(String tableNo, double balance, double paymentAmount) throws SQLException, IOException {
        boolean result = false;
        List<Order> receiptlist = getReceiptList(tableNo);
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        String ref_No = "R" + tableNo + timeStamp + "R";
        
        String filepath = "C:\\Users\\IM10\\Desktop\\SEM4\\CSE3999\\FinalSRMS\\web\\asset\\";
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filepath + ref_No + ".pdf"));
                document.open();
                String line = new String(new char[130]).replace('\0', '-');
                String header
                        = "                                                                 SRMS SYSTEM\n"
                        + "                                                         Restaurant Ori Thai Mookata\n"
                        + line + "\n"
                        + "                                                                    Receipt\n"
                        + "              Reference No : " + ref_No + "\n"
                        + "              Payment Time : " + timeStamp + "                                      Table No : " + tableNo + "\n"
                        + line + "\n";
                document.add(new Paragraph(header));
                for (Order list : receiptlist) {
                    String food = list.getMenuName();
                    int quantity = list.getQuantity();
                    double price = list.getTotal_price();
                    //double price_per_item = list.getMenuPrice();
                    document.add(new Paragraph(String.format("                            %s x %d", food, quantity)));
//                    document.add(new Paragraph(String.format("x %120d\n", quantity)));
                    document.add(new Paragraph(String.format("%120.2f\n", price)));
                }
                document.add(new Paragraph("\n" + line));
                String total_line = "\n                                                                                                  Total Amount       : RM" + balance;
                String payment_line = "\n                                                                                                  Payment Amount : RM" + paymentAmount;
                double changes = paymentAmount-balance;
                String changes_line = "\n                                                                                                  changes               : RM" + changes;
                document.add(new Paragraph(total_line));
                document.add(new Paragraph(payment_line));
                document.add(new Paragraph(changes_line));
                document.add(new Paragraph("\n" + line));
                document.close();
            } catch (DocumentException | FileNotFoundException e) {
                System.err.println(e);
            }
        
        try (Connection con = Util.getConnection();
                PreparedStatement ps = con.prepareStatement(GENERATE_RECEIPT_DATA)) {
            ps.setString(1, ref_No);
            ps.setDouble(2, balance);
            ps.setDouble(3, paymentAmount);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Util.printSQLException(e);
        }

    }
}
