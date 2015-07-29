package com.king.game.thread.threads;

import com.king.model.Score;
import com.king.model.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by freyliis
 */
public class HighScoreListThreadTest {

    HighScoreListThread objectUnderTest = new HighScoreListThread(null, null);

    @Test
    public void shouldProperlyParseScoreList(){
        final int userId1 = 1;
        final int userId2 = 1;
        User user1 = new User(userId1);
        User user2 = new User(userId2);
        Score score1 = new Score(user1, userId1);
        Score score2 = new Score(user2, userId2);
        StringBuilder result = new StringBuilder();
        result.append(userId1).append("=").append(userId1).append(",").append(userId2).append("=").append(userId2);
        Set<Score> scores = new HashSet<>();
        scores.add(score1);
        scores.add(score2);
        final String scoresCommaSeparatedString = objectUnderTest.parseToCommaSeparatedString(scores);
        Assert.assertThat(scoresCommaSeparatedString, CoreMatchers.is(result.toString()));
    }

}