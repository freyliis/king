package com.king.model;

import java.util.Set;

public class Level {

    private Integer levelId;
    private ScoreList scoreList;

    public Level(Integer levelId, ScoreList highScoreList) {
        this.levelId = levelId;
        this.scoreList = highScoreList;
    }

    public ScoreList getScoreList() {
        return scoreList;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public Set<Score> getHighScoreList() {
        return scoreList.getHighScoreList();
    }

    public void postScore(Integer scoreInt, User user) {
            Score score = new Score(user, scoreInt);
            scoreList.addScoreToScoreList(score);
            System.out.format("Score %d added to level %d for user %d", scoreInt, levelId, user.getUserId()).println();
    }
}
