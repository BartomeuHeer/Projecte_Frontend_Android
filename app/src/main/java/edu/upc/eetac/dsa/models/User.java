package edu.upc.eetac.dsa.models;

public class User {
    private String username;
    private String password;
    private String email;
    private String language;
    private int coins;

    public User(String userName, String mail, String password) {
        this.username = userName;
        this.password = password;
        this.email = mail;
        this.coins = 50;
        this.language ="English";
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {return language;}
    public void setLanguage(String language) {this.language = language;}

    public int getCoins() {return coins;}
    public void setCoins(int coins) {this.coins = coins;}
}
