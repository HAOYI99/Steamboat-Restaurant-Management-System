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
public class Menu {

    private String menuID;
    private String menuName;
    private String menuDesc;
    private String menuCategory;
    private double menuPrice;
    private String menuImage;
    private InputStream inputStreamImage;
    private String menuAvailable;

    public Menu() {
    }

    //update use(without image)
    public Menu(String menuID, String menuName, String menuDesc, String menuCategory, double menuPrice, String menuAvailable) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.menuDesc = menuDesc;
        this.menuCategory = menuCategory;
        this.menuPrice = menuPrice;
        this.menuAvailable = menuAvailable;
    }

    //create use
    public Menu(String menuID, String menuName, String menuDesc, String menuCategory, double menuPrice, InputStream inputStreamImage, String menuAvailable) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.menuDesc = menuDesc;
        this.menuCategory = menuCategory;
        this.menuPrice = menuPrice;
        this.inputStreamImage = inputStreamImage;
        this.menuAvailable = menuAvailable;
    }

    //retrieve use
    public Menu(String menuID, String menuName, String menuDesc, String menuCategory, double menuPrice, String menuImage, String menuAvailable) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.menuDesc = menuDesc;
        this.menuCategory = menuCategory;
        this.menuPrice = menuPrice;
        this.menuImage = menuImage;
        this.menuAvailable = menuAvailable;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(double menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    public InputStream getInputStreamImage() {
        return inputStreamImage;
    }

    public void setInputStreamImage(InputStream inputStreamImage) {
        this.inputStreamImage = inputStreamImage;
    }

    public String getMenuAvailable() {
        return menuAvailable;
    }

    public void setMenuAvailable(String menuAvailable) {
        this.menuAvailable = menuAvailable;
    }

}
