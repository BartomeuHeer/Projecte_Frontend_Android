package edu.upc.eetac.dsa.models;

public class ForumMessage implements Comparable<ForumMessage> {
    String id;
    String username;
    String message;
    Integer numseq;

    public ForumMessage(){}

    public ForumMessage(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public void setNumseq(Integer numseq) {
        this.numseq = numseq;
    }

    public Integer getNumseq() {
        return numseq;
    }

    @Override
    public int compareTo(ForumMessage forumMessage) {
        return (int) (this.numseq - forumMessage.numseq);
    }

}
