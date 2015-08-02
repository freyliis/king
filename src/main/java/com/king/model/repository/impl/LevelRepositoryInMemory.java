package com.king.model.repository.impl;

import com.king.model.Level;
import com.king.model.ScoreList;
import com.king.model.comparator.highscore.HighScoreComparator;
import com.king.model.repository.LevelRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by freyliis
 */
public class LevelRepositoryInMemory implements LevelRepository{

    private Map<Integer, Level> levelList;
    private int highScoreListSize;
    private HighScoreComparator highScoreComparator;

    public LevelRepositoryInMemory(int highScoreListSize, HighScoreComparator highScoreComparator) {
        this.levelList = new HashMap<>();
        this.highScoreListSize = highScoreListSize;
        this.highScoreComparator = highScoreComparator;
    }

    public Level createOrGetLevel(Integer levelId) {
        Level level = levelList.get(levelId);
        synchronized(levelList) {
            level = levelList.get(levelId);
            if (level == null) {
                level = new Level(levelId, new ScoreList(new ArrayList<>(), highScoreListSize, highScoreComparator));
                levelList.put(level.getLevelId(), level);
                System.out.format("Level %s created", levelId).println();
            }
        }
        return level;
    }


}
