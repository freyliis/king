package com.king.model;

import com.king.model.comparator.highscore.HighScoreComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by freyliis
 */
public class ScoreList {
    private final ConcurrentMap<Integer, Score> scoreSet;
    private final int highScoreListSize;
    private final HighScoreComparator highScoreComparator;

    public ScoreList( int highScoreListSize, HighScoreComparator highScoreComparator) {
        this.scoreSet = new ConcurrentHashMap<>();
        this.highScoreListSize = highScoreListSize;
        this.highScoreComparator = highScoreComparator;
    }

    public List<Score> getHighScoreList() {
        Supplier<List<Score>> supplier = () -> new ArrayList<Score>();
        List<Score> scores = scoreSet.values().stream().collect((Collectors.toCollection(supplier)));
        final List<Score> collected = scores.stream().sorted(highScoreComparator).limit(highScoreListSize).collect(Collectors.toCollection(supplier));
        return collected;
    }

    public void addScoreToScoreList(Score score) {
        scoreSet.put(score.getUserId(), score);
    }

}
