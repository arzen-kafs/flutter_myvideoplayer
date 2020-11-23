package com.example.hikaioffline.login;

public class User {
    int id;
    private String name,standard,stream,password,email;

    public User(int id, String name, String standard,String stream, String password, String email) {
        this.id = id;
        this.standard= standard;
        this.stream=stream;
        this.name = name;
        this.password=password;
        this.email=email;

    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}
