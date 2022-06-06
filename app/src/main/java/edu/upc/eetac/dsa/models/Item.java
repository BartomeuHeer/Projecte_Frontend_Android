package edu.upc.eetac.dsa.models;

public class Item {
    private String name;
    private String description;
    private double coins;
    private String urlPic;

    public Item(String name, String description, double coins,String urlPic) {
        this.name = name;
        this.description = description;
        this.coins = coins;
        this.urlPic=urlPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public String getUrlPic() {
        return this.urlPic;
    }

    public void setUrlPic(String urlPic) {
        this.urlPic = urlPic;
    }
}
