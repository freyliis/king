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
    List<Score> scores;

    HighScoreComparator highScoreComparator = new HighScoreReverseOrderComparator();
    @Mock
    User user;
    Score highScore;
    ScoreList objectUnderTest;

    @Before
    public void setUp() {
        scores = new ArrayList<Score>();
        highScore = new Score(user, 100);
    }


    @Test
    public void testAddScoreInEmptyHighScoreList() {
        objectUnderTest = new ScoreList(scores, 2, highScoreComparator);
        objectUnderTest.addScoreToScoreList(highScore);
        Assert.assertThat(objectUnderTest.getHighScoreList().contains(highScore), CoreMatchers.is(Boolean.TRUE));
    }

    @Test
    public void testNonHighScoreInFullHighScoreList() {
        scores.add(new Score(user, 200));
        scores.add(new Score(user, 300));
        objectUnderTest = new ScoreList(scores, 2, highScoreComparator);
        objectUnderTest.addScoreToScoreList(highScore);
        Assert.assertThat(objectUnderTest.getHighScoreList().contains(highScore), CoreMatchers.is(Boolean.FALSE));
    }

    @Test
    public void testHighScoreInFullHighScoreList() {
        scores.add(new Score(user, 12));
        scores.add(new Score(user, 13));
        objectUnderTest = new ScoreList(scores, 2, highScoreComparator);
        objectUnderTest.addScoreToScoreList(highScore);
        Assert.assertThat(objectUnderTest.getHighScoreList().contains(highScore), CoreMatchers.is(Boolean.TRUE));
    }
}