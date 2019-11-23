package com.dunghn.toeictest.model;

import androidx.annotation.NonNull;

public class NguoiDung {
    private int ID;
    private String username;
    private String password;
    private String fullname;
    private String email;

    public NguoiDung() {
    }

    public NguoiDung(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public NguoiDung(int ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public NguoiDung(String username, String password, String fullname, String email) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    @NonNull
//    @Override
//    public String toString() {
//        return "NguoiDung{"+
//                "id='"+ ID +'\''+
//                ", username='"+username +'\''+
//                ", password='"+password +'\''+
//                ", fullname='"+fullname +'\''+
//                ", email='"+email +'\''+
//                '}';
//
//    }
}
