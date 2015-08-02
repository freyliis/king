package com.king.model;

import java.util.List;

public class Level {

    private final Integer levelId;
    private final ScoreList scoreList;

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

    public List<Score> getHighScoreList() {
        return scoreList.getHighScoreList();
    }

    public void postScore(Integer scoreInt, Integer userId) {
            Score score = new Score(userId, scoreInt);
            scoreList.addScoreToScoreList(score);
            System.out.format("Score %d added to level %d for user %d", scoreInt, levelId, userId).println();
    }
}
