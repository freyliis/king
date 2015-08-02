package com.king.model;

public class Score {

    private final Integer userId;
    private final Integer score;

    public Score(Integer userId, Integer score) {
        this.userId = userId;
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public String toString() {
        return ""+userId +
                "=" + score;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Score score = (Score) o;

        return userId.equals(score.userId);

    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
