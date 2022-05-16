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
public class Order {

    private int orderID;
    private String menuID;
    private String menuName;
    private double menuPrice;
    private double total_price;
    private int quantity;
    private String timeStamp;
    private String tableNo;
    private String status;

    public Order() {
    }

    //receiptlist
    public Order(String menuName, double menuPrice, double total_price, int quantity) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.total_price = total_price;
        this.quantity = quantity;
    }
    
    //orderlist that go kitchen
    public Order(String menuName, int quantity, String timeStamp, String tableNo) {
        this.menuName = menuName;
        this.quantity = quantity;
        this.timeStamp = timeStamp;
        this.tableNo = tableNo;
    }

    //orderlist
    public Order(int orderID, String menuID, String menuName, double menuPrice, double total_price, int quantity, String tableNo) {
        this.orderID = orderID;
        this.menuID = menuID;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.total_price = total_price;
        this.quantity = quantity;
        this.tableNo = tableNo;
    }
    
    public Order(int orderID, String menuID, int quantity, String timeStamp, String tableNo, String status) {
        this.orderID = orderID;
        this.menuID = menuID;
        this.quantity = quantity;
        this.timeStamp = timeStamp;
        this.tableNo = tableNo;
        this.status = status;
    }
    
    //create order
    public Order(String menuID, int quantity, String tableNo) {
        this.menuID = menuID;
        this.quantity = quantity;
        this.tableNo = tableNo;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(double menuPrice) {
        this.menuPrice = menuPrice;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
