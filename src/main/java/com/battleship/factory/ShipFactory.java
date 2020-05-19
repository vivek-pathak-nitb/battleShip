package com.battleship.factory;

import com.battleship.entities.*;
import com.battleship.exceptions.InvalidPositionException;

/**
 * Factory to get an instance of ship.
 */
public class ShipFactory {

    public Ship getShip(final ShipType shipType, final Position position) throws InvalidPositionException {
        if (shipType == ShipType.BATTLE_SHIP) {
            return new Battleship(position);
        } else if (shipType == ShipType.CARRIER) {
            return new Carrier(position);
        } else if (shipType == ShipType.CRUISER) {
            return new Cruiser(position);
        } else if (shipType == ShipType.DESTROYER) {
            return new Destroyer(position);
        } else {
            return new Submarine(position);
        }
    }
}
