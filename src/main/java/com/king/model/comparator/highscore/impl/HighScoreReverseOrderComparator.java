package com.king.model.comparator.highscore.impl;

import com.king.model.HighScore;
import com.king.model.comparator.highscore.HighScoreComparator;

/**
 * Created by freyliis
 */
public class HighScoreReverseOrderComparator implements HighScoreComparator {
    public int compare(HighScore o1, HighScore o2) {
        return o2.getHighScore().compareTo(o1.getHighScore());
    }
}
