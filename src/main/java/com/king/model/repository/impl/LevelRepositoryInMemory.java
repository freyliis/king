package com.king.model.repository.impl;

import com.king.model.Level;
import com.king.model.repository.LevelRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by freyliis
 */
public class LevelRepositoryInMemory implements LevelRepository{

    private Map<Integer, Level> levelList;

    public LevelRepositoryInMemory() {
        levelList = new HashMap<Integer, Level>();
    }

    public Level getLevel(Integer levelId) {
        return levelList.get(levelId);
    }
}
