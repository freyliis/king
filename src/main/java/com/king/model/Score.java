package com.king.model;

public class Score {

    private User user;
    private Integer score;

    public Score(User user, Integer score) {
        this.user = user;
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }
}
