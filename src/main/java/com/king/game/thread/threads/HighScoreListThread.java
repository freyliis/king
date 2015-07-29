package com.king.game.thread.threads;

import com.king.game.service.GameOfThroneService;
import com.king.model.Score;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * Created by freyliis
 */
public class HighScoreListThread implements Callable<String> {

    private Integer levelId;
    private GameOfThroneService gameOfThroneService;

    public HighScoreListThread(Integer levelId, GameOfThroneService gameOfThroneService) {
        this.levelId = levelId;
        this.gameOfThroneService = gameOfThroneService;
    }

    @Override
    public String call() throws Exception {
        Set<Score> highscoreList = gameOfThroneService.getHighscoreList(levelId);
        final String highScoreListString = parseToCommaSeparatedString(highscoreList);
        return highScoreListString;
    }

    public String parseToCommaSeparatedString(Set<Score> highscoreList) {
        return highscoreList.stream().map(i -> i.toString()).collect(Collectors.joining(","));
    }
}
