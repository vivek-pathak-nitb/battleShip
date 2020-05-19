package com.battleship.entities;

import java.util.LinkedList;
import java.util.Queue;

public class Game {

    private final Queue<Player> players;
    private GameStatus gameStatus;
    private GameResult gameResult;

    public Game(final Queue<Player> players) {
        this.players = players;
        gameStatus = GameStatus.IN_PROGRESS;
    }

    public ShotStatus takeShot(final Point point) {
        final Player player = players.poll();
        final Board board = players.peek().getBoard();
        final ShotStatus shotStatus = board.mark(point);
        if (board.getRemainingCareers() == 0) {
            gameResult = new GameResult(player);
            gameStatus = GameStatus.FINISHED;
        }

        players.offer(player);
        return shotStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public Queue<Player> getPlayers() {
        return new LinkedList<>(players);
    }
}
