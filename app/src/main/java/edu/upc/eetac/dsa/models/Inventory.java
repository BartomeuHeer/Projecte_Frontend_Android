package edu.upc.eetac.dsa.models;

public class Inventory {
    public String name;
    public String username;
    public int quantity;
    public String urlPic;

    public Inventory() {}
    public Inventory(String name, String username, String urlPic)
    {
        this.name = name;
        this.username = username;
        this.urlPic=urlPic;
    }

    public Inventory(String name, String username)
    {
        this.name = name;
        this.username = username;
        //this.quantity=1;

    }

    public String getName()
    {
        return name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setQuantity(int quantity){ this.quantity=quantity;}

    public String getUrlPic(){ return urlPic;}

    public void setUrlPic(String urlPic){ this.urlPic=urlPic;}
}
