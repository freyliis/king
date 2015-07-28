package com.king.model;

import com.king.model.comparator.highscore.HighScoreComparator;
import com.king.model.comparator.highscore.impl.HighScoreReverseOrderComparator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by freyliis
 */
@RunWith(MockitoJUnitRunner.class)
public class HighScoreListTest {

    Integer levelId = 1;
    List<HighScore> highScores;

    HighScoreComparator highScoreComparator = new HighScoreReverseOrderComparator();
    @Mock
    User user;
    HighScore highScore;
    HighScoreList objectUnderTest;

    @Before
    public void setUp() {
        highScores = new ArrayList<HighScore>();
        highScore = new HighScore(user, 100);
        objectUnderTest = new HighScoreList(highScores, 2, highScoreComparator);
    }

    @Test
    public void testIsHighScoreInListWithOneElement() {
        highScores.add(new HighScore(user, 12));
        boolean isAdded = objectUnderTest.isHighScore(highScore);
        Assert.assertThat(isAdded, CoreMatchers.is(Boolean.TRUE));
    }

    @Test
    public void testIsHighScoreInFullHighScoreList() {
        highScores.add(new HighScore(user, 12));
        highScores.add(new HighScore(user, 13));
        boolean isAdded = objectUnderTest.isHighScore(highScore);
        Assert.assertThat(isAdded, CoreMatchers.is(Boolean.TRUE));
    }

    @Test
    public void testIsNotHighScoreInFullHighScoreList() {
        highScores.add(new HighScore(user, 200));
        highScores.add(new HighScore(user, 300));
        boolean isAdded = objectUnderTest.isHighScore(highScore);
        Assert.assertThat(isAdded, CoreMatchers.is(Boolean.FALSE));
    }

    @Test
    public void testSuccessdAddHighScoreInEmptyHighScoreList() {
        objectUnderTest.addScoreToHighScoreListAndResize(highScore);
        Assert.assertThat(objectUnderTest.getHighScoreList().contains(highScore), CoreMatchers.is(Boolean.TRUE));
    }

    @Test
    public void testFailedAddHighScoreInFullHighScoreList() {
        highScores.add(new HighScore(user, 200));
        highScores.add(new HighScore(user, 300));
        objectUnderTest.addScoreToHighScoreListAndResize(highScore);
        Assert.assertThat(objectUnderTest.getHighScoreList().contains(highScore), CoreMatchers.is(Boolean.FALSE));
    }

    @Test
    public void testSuccessAddHighScoreInFullHighScoreList() {
        highScores.add(new HighScore(user, 12));
        highScores.add(new HighScore(user, 13));
        objectUnderTest.addScoreToHighScoreListAndResize(highScore);
        Assert.assertThat(objectUnderTest.getHighScoreList().contains(highScore), CoreMatchers.is(Boolean.TRUE));
    }
}