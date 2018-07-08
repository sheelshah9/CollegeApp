package com.example.sheel9.nirmauniversityapp;

/**
 * Created by Sheel9 on 29-03-2017.
 */
public class DataFetchLogin {
    private String username,password;

    public DataFetchLogin(String username, String password) {
        this.username=username;
        this.password=password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
