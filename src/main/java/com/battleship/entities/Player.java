package com.battleship.entities;

import com.battleship.manager.GameManager;

import java.util.HashMap;
import java.util.Map;

public class Player {

    private final Board board;
    private final String name;
    private final Map<Point, ShotStatus> shotHistory;
    private GameManager gameManager;

    public Player(final String name) {
        this.name = name;
        this.board = new Board(5);
        shotHistory = new HashMap<>();
    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public void takeShot(final Point point) {
        final ShotStatus shotStatus = gameManager.takeShot(point);
        updateShotHistory(point, shotStatus);
    }

    public Map<Point, ShotStatus> getShotHistory() {
        return shotHistory;
    }

    public void setGameManager(final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private void updateShotHistory(final Point point, final ShotStatus shotStatus) {
        shotHistory.put(point, shotStatus);
    }
}
