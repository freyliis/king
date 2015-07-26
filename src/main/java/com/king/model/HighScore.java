package com.king.model;

public class HighScore {

    private User user;
    private Integer highscore;

    public HighScore(User user, Integer highscore) {
        this.user = user;
        this.highscore = highscore;
    }

    public Integer getHighScore() {
        return highscore;
    }
}
