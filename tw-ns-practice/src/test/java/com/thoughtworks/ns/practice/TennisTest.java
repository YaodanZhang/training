package com.thoughtworks.ns.practice;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TennisTest {
    private static final String PLAYER1_NAME = "John";
    private static final String PLAYER2_NAME = "Jerry";

    private int player1Score;
    private int player2Score;
    private String expectedScore;

    public TennisTest(int player1Score, int player2Score, String expectedScore) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.expectedScore = expectedScore;
    }

    @Parameters
    public static Collection<Object[]> getAllScores() {
        return Arrays.asList(new Object[][] {
                { 0, 0, "Love-All" },
                { 1, 1, "Fifteen-All" },
                { 2, 2, "Thirty-All"},
                { 3, 3, "Deuce"},
                { 4, 4, "Deuce"},

                { 1, 0, "Fifteen-Love"},
                { 0, 1, "Love-Fifteen"},
                { 2, 0, "Thirty-Love"},
                { 0, 2, "Love-Thirty"},
                { 3, 0, "Forty-Love"},
                { 0, 3, "Love-Forty"},
                { 4, 0, format("Win for %s", PLAYER1_NAME)},
                { 0, 4, format("Win for %s", PLAYER2_NAME)},

                { 2, 1, "Thirty-Fifteen"},
                { 1, 2, "Fifteen-Thirty"},
                { 3, 1, "Forty-Fifteen"},
                { 1, 3, "Fifteen-Forty"},
                { 4, 1, format("Win for %s", PLAYER1_NAME)},
                { 1, 4, format("Win for %s", PLAYER2_NAME)},

                { 3, 2, "Forty-Thirty"},
                { 2, 3, "Thirty-Forty"},
                { 4, 2, format("Win for %s", PLAYER1_NAME)},
                { 2, 4, format("Win for %s", PLAYER2_NAME)},

                { 4, 3, format("Advantage %s", PLAYER1_NAME)},
                { 3, 4, format("Advantage %s", PLAYER2_NAME)},
                { 5, 4, format("Advantage %s", PLAYER1_NAME)},
                { 4, 5, format("Advantage %s", PLAYER2_NAME)},
                { 15, 14, format("Advantage %s", PLAYER1_NAME)},
                { 14, 15, format("Advantage %s", PLAYER2_NAME)},

                { 6, 4, format("Win for %s", PLAYER1_NAME)},
                { 4, 6, format("Win for %s", PLAYER2_NAME)},
                { 16, 14, format("Win for %s", PLAYER1_NAME)},
                { 14, 16, format("Win for %s", PLAYER2_NAME)},
        });
    }

    public void checkAllScores(TennisGame game) {
        int highestScore = Math.max(this.player1Score, this.player2Score);
        for (int i = 0; i < highestScore; i++) {
            if (i < this.player1Score)
                game.wonPoint(PLAYER1_NAME);
            if (i < this.player2Score)
                game.wonPoint(PLAYER2_NAME);
        }
        assertEquals(this.expectedScore, game.getScore());
    }

    @Test
    public void checkAllScoresOfUglyTennisGame() {
        UglyTennisGame game = new UglyTennisGame(PLAYER1_NAME, PLAYER2_NAME);
        checkAllScores(game);
    }
}

