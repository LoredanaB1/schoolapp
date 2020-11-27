package com.example.myapptest;

public class User {
    String fn,sn;

    public User(String fn, String sn) {
        this.fn = fn;
        this.sn = sn;
    }

    public User() {
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
