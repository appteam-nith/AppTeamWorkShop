package com.appteam.appteamworkshop;

import java.util.ArrayList;

/**
 * Created by jatin on 11/3/18.
 */

public class PostList {
    private boolean success;
    private ArrayList<Post> confessions;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Post> getConfessions() {
        return confessions;
    }

    public void setConfessions(ArrayList<Post> confessions) {
        this.confessions = confessions;
    }

    public PostList(boolean success, ArrayList<Post> confessions) {

        this.success = success;
        this.confessions = confessions;
    }
}
