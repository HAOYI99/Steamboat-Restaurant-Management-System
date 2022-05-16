/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;

/**
 *
 * @author IM10
 */
public class Util {
    
    private static final String jdbcDriver = "com.mysql.jdbc.Driver";
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/srms";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "admin";
    
    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL ,jdbcUsername , jdbcPassword);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
    
    public static void printSQLException(SQLException ex){
        for(Throwable e : ex){
            if(e instanceof SQLException){
                e.printStackTrace(System.err);
                System.out.println("SQLState : " + ((SQLException) e).getSQLState());
                System.out.println("Error Code : " + ((SQLException) e).getErrorCode());
                System.out.println("Message : " + e.getMessage());
                Throwable t = ex.getCause();
                while(t != null){
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    public static String convertImage(Blob blob) throws SQLException, IOException {
        InputStream inputStream = blob.getBinaryStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        byte[] imageBytes = outputStream.toByteArray();
        String convertedImage = Base64.getEncoder().encodeToString(imageBytes);
        inputStream.close();
        outputStream.close();
        
        return convertedImage;
    }
    
}
