package com.king.model.repository;

import com.king.model.Level;

/**
 * Created by freyliis
 */
public interface LevelRepository {

    Level createOrGetLevel(Integer levelId);

}
