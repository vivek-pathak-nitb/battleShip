package com.battleship.entities;

public class GameResult {

    private final Player winner;

    public GameResult(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }
}
