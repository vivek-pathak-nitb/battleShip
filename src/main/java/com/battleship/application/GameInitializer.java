package com.battleship.application;

import com.battleship.entities.*;
import com.battleship.exceptions.InvalidPositionException;
import com.battleship.factory.ShipFactory;
import com.battleship.helpers.GameHelper;
import com.battleship.manager.GameManager;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Responsible for initializing the game.
 */
public class GameInitializer {

    private final ShipFactory shipFactory;
    private final Scanner scanner;

    public GameInitializer(final Scanner scanner) {
        this.shipFactory = new ShipFactory();
        this.scanner = scanner;
    }

    public Game initializeGame() {
        System.out.println(Constants.GAME_BANNER);

        // Create player 1 and take ship positions for player 1
        final Player player1 = getPlayer(Constants.PLEASE_ENTER_THE_NAME_OF_PLAYER_1);
        takeShipPositions(player1.getBoard());

        // Create player 2 and take ship positions for player 2
        final Player player2 = getPlayer(Constants.PLEASE_ENTER_THE_NAME_OF_PLAYER_2);
        takeShipPositions(player2.getBoard());

        // Create game object
        final Queue<Player> players = new LinkedList<>();
        players.offer(player1);
        players.offer(player2);
        final Game game = new Game(new LinkedList<>(players));

        // Create game manager.
        final GameManager gameManager = new GameManager(game);
        player1.setGameManager(gameManager);
        player2.setGameManager(gameManager);

        return game;
    }

    private void takeShipPositions(final Board board) {
        System.out.println(Constants.INPUT_SHIP_COORDINATES_MESSAGE);
        for (final ShipType shipType : ShipType.values()) {
            placeShip(board, shipType);
        }
    }

    private void placeShip(final Board board,
                           final ShipType shipType) {
        while (true) {
            final Point fromPoint = GameHelper.getPoint(scanner,
                    String.format(Constants.ENTER_SHIP_START_COORDINATES_FORMAT, shipType));

            final Point toPoint = GameHelper.getPoint(scanner,
                    String.format(Constants.ENTER_SHIP_END_COORDINATES_FORMAT, shipType));

            final Position position = new Position(fromPoint, toPoint);
            Ship ship;
            try {
                ship = shipFactory.getShip(shipType, position);
            } catch (final InvalidPositionException ex) {
                System.out.println(Constants.INVALID_POSITION_MESSAGE);
                continue;
            }
            final boolean status = board.put(position, ship);
            if (!status) {
                System.out.println(Constants.NON_EMPTY_POSITION_MESSAGE);
                continue;
            }

            break;
        }
    }


    private Player getPlayer(final String message) {
        System.out.println(message);
        String name;
        while ((name = scanner.nextLine()).isEmpty()) {
            System.out.println(Constants.ENTER_VALID_NAME);
        }
        return new Player(name);
    }
}
