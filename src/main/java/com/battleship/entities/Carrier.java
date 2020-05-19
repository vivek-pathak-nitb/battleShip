package com.battleship.entities;

import com.battleship.exceptions.InvalidPositionException;

public class Carrier extends Ship {

    public Carrier(Position position) throws InvalidPositionException {
        super(position, 5, new boolean[5], ShipType.CARRIER);
    }
}
