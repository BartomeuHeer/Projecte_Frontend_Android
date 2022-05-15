package edu.upc.eetac.dsa.models;

public class LoginParam {
    private String username;
    private String pass;

    public LoginParam(String username, String password) {
        this.username = username;
        this.pass = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
