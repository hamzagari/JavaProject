package com.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;




public class database{
    
    private String host, username, password, database;
    private int port;
    private Connection cnx;

public database(){
        host = "localhost:";
        username = "root";
        password = "";
        database = "JDBC";
        port = 3306;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://"+host+port+"/"+database, username, password);
        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {
        
        }
        
    }



public void insertIn(user u){

try {
    PreparedStatement Ps=cnx.prepareStatement("insert into user values(?,?,?,?,?)");
Ps.setString(1, u.getUsename());
Ps.setString(2, u.getEmail());
Ps.setString(3, u.getPassword());
Ps.setString(4, null);
Ps.setString(5, null);
Ps.executeUpdate();
} catch (SQLException e) {
    e.printStackTrace();
}

}




    

public  Vector<user> getALL(){

Vector<user> ve=new Vector<>();
Statement st;
try {
    st = cnx.createStatement();
    ResultSet result=st.executeQuery("select * from user");
String username,email,password,valid_email;
while(result.next()){
username=result.getString("username");
email=result.getString("email");
password=result.getString("password");
valid_email=result.getString("email_verified");
user u=new user(username,email,password,valid_email);
ve.add(u);
}
} catch (SQLException e) {
 System.out.println("erorr");
}


return ve;

}


    }
 







   

