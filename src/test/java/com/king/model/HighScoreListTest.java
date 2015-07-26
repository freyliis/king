package com.king.model;

import com.king.model.comparator.highscore.HighScoreComparator;
import com.king.model.comparator.highscore.HighScoreReverseOrderComparator;
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
    HighScore highScore = new HighScore(user, 100);
    HighScoreList objectUnderTest;

    @Before
    public void setUp() {
        highScores = new ArrayList<HighScore>();
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
        Assert.assertThat(isAdded, CoreMatchers.is(Boolean.TRUE));
    }

    @Test
    public void testSuccessdAddHighScoreInEmptyHighScoreList() {
        objectUnderTest.addScoreToHighScoreList(highScore);
        Assert.assertThat(highScores.contains(highScore), CoreMatchers.is(Boolean.TRUE));
    }

    @Test
    public void testFailedAddHighScoreInFullHighScoreList() {
        highScores.add(new HighScore(user, 200));
        highScores.add(new HighScore(user, 300));
        objectUnderTest.addScoreToHighScoreList(highScore);
        Assert.assertThat(highScores.contains(highScore), CoreMatchers.is(Boolean.FALSE));
    }

    @Test
    public void testSuccessAddHighScoreInFullHighScoreList() {
        highScores.add(new HighScore(user, 12));
        highScores.add(new HighScore(user, 13));
        objectUnderTest.addScoreToHighScoreList(highScore);
        Assert.assertThat(highScores.contains(highScore), CoreMatchers.is(Boolean.TRUE));
    }
}