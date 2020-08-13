package com.example.e2;

public class MemoModel {
    private String time;
    private String text;
    private String email;

    public MemoModel() {}

    public MemoModel(String time, String text, String email) {
        this.time = time;
        this.text = text;
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public String getEmail() {
        return email;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
