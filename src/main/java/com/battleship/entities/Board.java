package com.battleship.entities;

import com.battleship.helpers.GameHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    private final Square[][] board;
    private int remainingCareers;

    public Board(int remainingCareers) {
        this.remainingCareers = remainingCareers;
        board = new Square[Constants.BOARD_ROW][Constants.BOARD_COL];
        // Initialized board with square.
        for (int i = 0; i < Constants.BOARD_ROW; i++) {
            for (int j = 0; j < Constants.BOARD_COL; j++) {
                board[i][j] = new Square(new Point(i, j));
            }
        }
    }

    public boolean put(final Position position, final Ship ship) {
        if (!GameHelper.isValidPosition(position)) {
            return false;
        }

        final Point fromPoint = position.getFrom();
        final Point toPoint = position.getTo();
        final List<Square> squares = getSquares(fromPoint, toPoint);
        // check squares between from & to has ship or not where from and to are inclusive.
        for (final Square square : squares) {
            if (square.doesContainsShip()) {
                return false;
            }
        }

        // Set ship in all those squares.
        for (final Square square : squares) {
            square.setShip(ship);
        }

        return true;
    }

    private List<Square> getSquares(final Point fromPoint,
                                    final Point toPoint) {
        final List<Square> squares = new ArrayList<>();
        squares.add(board[fromPoint.getX() - 1][fromPoint.getY() - 1]);
        squares.add(board[toPoint.getX() - 1][toPoint.getY() - 1]);

        int xDiff = toPoint.getX() - fromPoint.getX();
        int yDiff = toPoint.getY() - fromPoint.getY();
        if (xDiff == 0) {
            int order = yDiff > 0 ? 1 : -1;
            for (int i = 1, j = order; i <= Math.abs(yDiff); i++, j += order) {
                squares.add(board[fromPoint.getX() - 1][fromPoint.getY() - 1 + j]);
            }
        }

        if (yDiff == 0) {
            int order = xDiff > 0 ? 1 : -1;
            for (int i = 1, j = order; i <= Math.abs(xDiff); i++, j += order) {
                squares.add(board[fromPoint.getX() - 1 + j][fromPoint.getY() - 1]);
            }
        }

        return squares;
    }

    public ShotStatus mark(final Point point) {
        final Square square = board[point.getX() - 1][point.getY() - 1];
        if (square.isHit()) {
            if (square.doesContainsShip()) {
                return ShotStatus.HIT;
            }
            return ShotStatus.MISS;
        }

        square.setHit(true);
        if (square.doesContainsShip()) {
            final Ship ship = square.getShip();
            if (ship.markAndCheckDamaged(point)) {
                remainingCareers--;
            }
            return ShotStatus.HIT;
        }
        return ShotStatus.MISS;
    }

    public void draw() {
        System.out.println(Constants.YOUR_BOARD);
        for (final Square[] squares : board) {
            for (int j = 0; j < board[0].length; j++) {
                Square square = squares[j];
                System.out.print(getIcon(square) + " ");
            }
            System.out.println();
        }
    }

    public void draw(final Map<Point, ShotStatus> shotHistory) {
        System.out.println(Constants.YOUR_SHOT_HISTORY);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                final Point point = new Point(i + 1, j + 1);
                if (shotHistory.containsKey(point)) {
                    System.out.print(getIcon(shotHistory.get(point)) + " ");
                } else {
                    System.out.print('0' + " ");
                }
            }
            System.out.println();
        }
    }

    public int getRemainingCareers() {
        return remainingCareers;
    }


    private char getIcon(final ShotStatus shotStatus) {
        if (shotStatus == ShotStatus.HIT) {
            return Icon.HIT;
        }
        return Icon.MISS;
    }

    private char getIcon(final Square square) {
        if (square.isHit()) {
            if (square.doesContainsShip()) {
                return Icon.HIT;
            }
            return Icon.MISS;
        } else if (square.doesContainsShip()) {
            return Icon.SHIP;
        }
        return Icon.EMPTY;
    }
}
