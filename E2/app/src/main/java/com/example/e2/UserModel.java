package com.example.e2;

public class UserModel {
    private String name;
    private String email;
    private String time;

    public UserModel() {}

    public UserModel(String name, String email, String time) {
        this.name = name;
        this.email = email;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
