/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author WINDOWS 10
 */
public class database_neve {
    private static String host;
    private static String username;
    private static String password;
    private static String database;
    private static int port;
    private static Connection connect;

    public static Connection connectDB() {
        host = "localhost:";
        username = "root";
        password = "";
        database = "JDBC";
        port = 3306;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

             connect = DriverManager.getConnection("jdbc:mysql://"+host+port+"/"+database, username, password); 
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
