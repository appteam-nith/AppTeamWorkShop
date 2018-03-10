package com.appteam.appteamworkshop;

/**
 * Created by sahil on 10/3/18.
 */

public class Post {

    private String title;

    private String message;


    public Post(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
