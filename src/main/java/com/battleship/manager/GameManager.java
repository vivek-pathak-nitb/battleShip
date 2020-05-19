package com.battleship.manager;

import com.battleship.entities.Game;
import com.battleship.entities.Point;
import com.battleship.entities.ShotStatus;

public class GameManager {

    private final Game game;

    public GameManager(final Game game) {
        this.game = game;
    }

    public ShotStatus takeShot(final Point point) {
        return game.takeShot(point);
    }
}
