package com.battleship.helpers;

import com.battleship.entities.Constants;
import com.battleship.entities.Point;
import com.battleship.entities.Position;

import java.util.Scanner;

public class GameHelper {

    public static boolean isValidPosition(final Position position) {
        return isValidPoint(position.getFrom()) && isValidPoint(position.getTo());
    }

    public static boolean isValidPoint(final Point point) {
        return point.getY() >= 1 && point.getX() >= 1 && point.getX() <= Constants.BOARD_ROW && point.getY()
                <= Constants.BOARD_COL;
    }

    public static boolean isValidInteger(final String string) {
        try {
            Integer.parseInt(string.trim());
            return true;
        } catch (final Exception ex) {
            return false;
        }
    }

    public static Point getPoint(final Scanner scanner,
                                 final String message) {
        String[] coordinatesArray;
        while (true) {
            System.out.println(message);
            final String coordinates = scanner.nextLine();
            coordinatesArray = coordinates.split(",");
            if (coordinatesArray.length < 2) {
                continue;
            }

            if (!GameHelper.isValidInteger(coordinatesArray[0])) {
                continue;
            }

            if (!GameHelper.isValidInteger(coordinatesArray[1])) {
                continue;
            }
            final Point point =
                    new Point(Integer.parseInt(coordinatesArray[0].trim()), Integer.parseInt(coordinatesArray[1].trim()));

            if (!isValidPoint(point)) {
                continue;
            }

            return point;
        }
    }
}
