package com.example;

public class user {
    
private String usename,email,password,valid_email;


public String getValid_email() {
    return valid_email;
}



public void setValid_email(String valid_email) {
    this.valid_email = valid_email;
}



public user(String usename, String email, String password,String valid_email) {
    this.usename = usename;
    this.email = email;
    this.password = password;
    this.valid_email=valid_email;
}



public String getUsename() {
    return usename;
}

public void setUsename(String usename) {
    this.usename = usename;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}




}
