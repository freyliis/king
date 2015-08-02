package com.king.model;

import com.king.model.comparator.highscore.HighScoreComparator;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by freyliis
 */
public class ScoreList {
    private SortedSet<Score> scoreSet;
    private int highScoreListSize;
    private HighScoreComparator highScoreComparator;

    public ScoreList(List<Score> scoreList, int highScoreListSize, HighScoreComparator highScoreComparator) {
        this.scoreSet = new TreeSet<Score>(highScoreComparator);
        this.scoreSet.addAll(scoreList);
        this.highScoreListSize = highScoreListSize;
        this.highScoreComparator = highScoreComparator;
    }

    public Set<Score> getHighScoreList() {
        Supplier<SortedSet<Score>> supplier = () -> new ConcurrentSkipListSet<>(highScoreComparator);
        Set<Score> scores = scoreSet.stream().limit(highScoreListSize).collect(Collectors.toCollection(supplier));
        return scores;
    }

    public void addScoreToScoreList(Score score) {
        scoreSet.add(score);
    }

}
