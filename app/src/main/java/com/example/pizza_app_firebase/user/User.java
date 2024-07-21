package com.example.pizza_app_firebase.user;

public class User {
    public String name;
    public String email;
    public String password;
    public String phone;
    public String address;
    public int role;

    public User(){
        this.name = "";
        this.email = "";
        this.password = "";
        this.phone = "";
        this.address = "";
        this.role = 1; //Client role
    }

    public User(String name, String email, String password, String phone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = 1; //Client role
    }
}
