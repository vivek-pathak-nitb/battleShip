package com.battleship.application;

import com.battleship.entities.*;
import com.battleship.helpers.GameHelper;

import java.util.Queue;
import java.util.Scanner;

/**
 *  Main application for the game.
 */
public class BattleShipApplication {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final GameInitializer gameInitializer = new GameInitializer(scanner);
        final Game game = gameInitializer.initializeGame();
        final Queue<Player> players = game.getPlayers();
        while (game.getGameStatus() != GameStatus.FINISHED) {
            final Player player = players.poll();
            final Point point = GameHelper.getPoint(scanner, String.format(Constants.SHOT_COORDINATES,
                    player.getName()));
            player.takeShot(point);
            draw(player);
            players.offer(player);
        }

        System.out.println("Winner is " + game.getGameResult().getWinner().getName());
    }

    private static void draw(final Player player) {
        final Board board = player.getBoard();
        board.draw();
        board.draw(player.getShotHistory());
    }
}
