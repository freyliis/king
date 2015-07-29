package com.king.model.comparator.highscore;

import com.king.model.Score;
import com.king.model.User;
import com.king.model.comparator.highscore.impl.HighScoreReverseOrderComparator;
import org.hamcrest.CoreMatchers;
import org.hamcrest.number.OrderingComparison;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by freyliis
 */
@RunWith(MockitoJUnitRunner.class)
public class HighScoreReverseOrderComparatorTest {

    @Mock
    User user;
    Score score1 = new Score(user, 100);
    Score score2 = new Score(user, 100);
    Score score3 = new Score(user, 200);
    HighScoreReverseOrderComparator objectUndertest = new HighScoreReverseOrderComparator();

    @Test
    public void testEquals() {
        int compare = objectUndertest.compare(score1, score2);
        Assert.assertThat(compare, CoreMatchers.is(0));
    }

    @Test
    public void testHigherscore() {
        int compare = objectUndertest.compare(score1, score3);
        Assert.assertThat(compare, OrderingComparison.greaterThan(0));
    }

    @Test
    public void testLowerscore() {
        int compare = objectUndertest.compare(score3, score1);
        Assert.assertThat(compare, OrderingComparison.lessThan(0));
    }
}