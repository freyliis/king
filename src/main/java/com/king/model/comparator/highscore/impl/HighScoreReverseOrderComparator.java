package com.king.model.comparator.highscore.impl;

import com.king.model.Score;
import com.king.model.comparator.highscore.HighScoreComparator;

/**
 * Created by freyliis
 */
public class HighScoreReverseOrderComparator implements HighScoreComparator {
    public int compare(Score o1, Score o2) {
        return o2.getScore().compareTo(o1.getScore());
    }
}
