package com.example.smart_recycle;

public class User {
    public String name, age, email;
    public int garbagePoints;
    public User(){

    }
    public User(String name, String age, String email,int garbagePoints){
        this.name = name;
        this.age=age;
        this.email=email;
        this.garbagePoints =garbagePoints;
    }

}