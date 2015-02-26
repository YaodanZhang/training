package com.thoughtworks.ns.practice;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Splitter.on;
import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.ImmutableList.of;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.String.format;

public class UglyTennisGame implements TennisGame {
    private static final String POINT_0 = "Love";
    private static final String POINT_1 = "Fifteen";
    private static final String POINT_2 = "Thirty";
    private static final String POINT_3 = "Forty";
    private static final String ARE_EQUAL = "-All";
    private static final String DEUCE = "Deuce";
    private static final String WIN_FOR = "Win for ";
    private static final String ADVANTAGE_FOR = "Advantage ";
    private static final String SEPARATOR = "-";
    private static final Map<List<Integer>, String> resultMap =
            ImmutableMap.<List<Integer>, String>builder()
                    .put(of(0, 0), POINT_0 + ARE_EQUAL)
                    .put(of(1, 0), POINT_1 + SEPARATOR + POINT_0)
                    .put(of(2, 0), POINT_2 + SEPARATOR + POINT_0)
                    .put(of(3, 0), POINT_3 + SEPARATOR + POINT_0)
                    .put(of(4, 0), WIN_FOR + "%s")
                    .put(of(1, 1), POINT_1 + ARE_EQUAL)
                    .put(of(2, 1), POINT_2 + SEPARATOR + POINT_1)
                    .put(of(3, 1), POINT_3 + SEPARATOR + POINT_1)
                    .put(of(4, 1), WIN_FOR + "%s")
                    .put(of(2, 2), POINT_2 + ARE_EQUAL)
                    .put(of(3, 2), POINT_3 + SEPARATOR + POINT_2)
                    .put(of(4, 2), WIN_FOR + "%s")
                    .put(of(3, 3), DEUCE)
                    .put(of(4, 3), ADVANTAGE_FOR + "%s")
                    .build();
    private static final Splitter SPLITTER = on(SEPARATOR).limit(2).omitEmptyStrings().trimResults();
    private int P1point = 0;
    private int P2point = 0;
    private String player1Name;
    private String player2Name;

    public UglyTennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        String result = null;
        int max = max(P1point, P2point);
        int min = min(P1point, P2point);
        for (; result == null; max--, min--) {
            result = resultMap.get(of(max, min));
        }
        result = format(result, getHigherPointPlayerName(), getLowerPointPlayerName());
        if (P1point < P2point) {
            result = flipResult(result);
        }
        return result;
    }

    private String flipResult(String result) {
        ImmutableList<String> playerNames = copyOf(SPLITTER.split(result));
        return playerNames.size() == 2
                ? playerNames.get(1) + SEPARATOR + playerNames.get(0)
                : result;
    }

    private String getHigherPointPlayerName() {
        if (P1point > P2point) {
            return player1Name;
        } else if (P2point > P1point) {
            return player2Name;
        } else {
            return "";
        }
    }

    private String getLowerPointPlayerName() {
        if (P2point > P1point) {
            return player1Name;
        } else if (P1point > P2point) {
            return player2Name;
        } else {
            return "";
        }
    }

    private void P1Score() {
        P1point++;
    }

    private void P2Score() {
        P2point++;
    }

    public void wonPoint(String player) {
        if (player.equals(player1Name)) {
            P1Score();
        } else {
            P2Score();
        }
    }
}
