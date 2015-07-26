package com.king.model;

import com.king.model.comparator.highscore.HighScoreComparator;

import java.util.Collections;
import java.util.List;

/**
 * Created by freyliis
 */
public class HighScoreList {
    List<HighScore> highScoreList;
    private int highScoreListSize;
    private HighScoreComparator highScoreComparator;

    public HighScoreList(List<HighScore> highScoreList, int highScoreListSize, HighScoreComparator highScoreComparator) {
        this.highScoreList = highScoreList;
        this.highScoreListSize = highScoreListSize;
        this.highScoreComparator = highScoreComparator;
    }

    public List<HighScore> getHighScoreList() {
        Collections.sort(highScoreList, highScoreComparator);
        return Collections.unmodifiableList(highScoreList);
    }

    public boolean isHighScore(HighScore highScore) {
        int size = highScoreList.size();
        if (!(size == 0 || size < highScoreListSize)) {
            HighScore min = Collections.min(highScoreList, highScoreComparator);
            if (min.getHighScore() > highScore.getHighScore()) {
                return false;
            }
        }
        return true;
    }

    public void addScoreToHighScoreList(HighScore highScore) {
            highScoreList.add(highScore);
            Collections.sort(highScoreList, highScoreComparator);
            highScoreList = highScoreList.subList(0, highScoreListSize);
    }
}
