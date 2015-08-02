package com.king.model.repository.impl;

import com.king.model.Level;
import com.king.model.ScoreList;
import com.king.model.comparator.highscore.HighScoreComparator;
import com.king.model.repository.LevelRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by freyliis
 */
public class LevelRepositoryInMemory implements LevelRepository{

    private final ConcurrentMap<Integer, Level> levelList;
    private final int highScoreListSize;
    private final HighScoreComparator highScoreComparator;

    public LevelRepositoryInMemory(int highScoreListSize, HighScoreComparator highScoreComparator) {
        this.levelList = new ConcurrentHashMap<>();
        this.highScoreListSize = highScoreListSize;
        this.highScoreComparator = highScoreComparator;
    }

    public Level createOrGetLevel(Integer levelId) {
        Level level = levelList.get(levelId);
           if (level == null) {
                level = new Level(levelId, new ScoreList( highScoreListSize, highScoreComparator));
                levelList.put(level.getLevelId(), level);
                System.out.format("Level %s created", levelId).println();
        }
        return level;
    }


}
