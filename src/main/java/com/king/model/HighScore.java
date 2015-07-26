package com.king.model;

public class HighScore {

    private User user;
    private Integer highScore;

    public HighScore(User user, Integer highScore) {
        this.user = user;
        this.highScore = highScore;
    }

    public Integer getHighScore() {
        return highScore;
    }
}
