package edu.upc.eetac.dsa.models;

public class Stats {
    private String username;
    private int points;
    private int enemiesKilled;
    private int level;
    private String avatar;

    public Stats(String username) {
        this.username = username;
        this.points = 0;
        this.enemiesKilled = 0;
        this.level = 0;
        this.avatar = "https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png";
    }

    public Stats(){

    }

    public Stats (String username, int points,String avatar)
    {
        this.username=username;
        this.points=points;
        this.avatar=avatar;
        this.enemiesKilled=0;
        this.level=0;

    }

    public int getPoints()
    {
        return this.points;
    }

    public void setPoints(int Points)
    {
        this.points=Points;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public int getEnemiesKilled()
    {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled)
    {
        this.enemiesKilled=enemiesKilled;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level=level;
    }

    public String getAvatar()
    {
        return this.avatar;
    }

    public void setAvatar(String avatar){
        this.avatar=avatar;
    }

}
