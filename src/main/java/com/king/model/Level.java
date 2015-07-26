package com.king.model;

import java.util.List;

public class Level {

    private Integer levelId;
    private HighScoreList highScoreList;

    public Level(Integer levelId, List<HighScore> highScore, HighScoreList highScoreList) {
        this.levelId = levelId;
        this.highScoreList = highScoreList;
    }

    public HighScoreList getHighScoreList() {
        return highScoreList;
    }
}
