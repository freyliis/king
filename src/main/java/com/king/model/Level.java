package com.king.model;

import java.util.List;
import java.util.Set;

public class Level {

    private Integer levelId;
    private ScoreList highScoreList;

    public Level(Integer levelId, List<Score> highScore, ScoreList highScoreList) {
        this.levelId = levelId;
        this.highScoreList = highScoreList;
    }

    public ScoreList getScoreList() {
        return highScoreList;
    }

    public Set<Score> getHighScoreList() {
        return highScoreList.getHighScoreList();
    }

    public void postScore(Integer scoreInt, User user) {
            Score score = new Score(user, scoreInt);
            highScoreList.addScoreToScoreList(score);
    }
}
