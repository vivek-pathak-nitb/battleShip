package com.battleship.entities;

import com.battleship.exceptions.InvalidPositionException;

public class Submarine extends Ship {

    public Submarine(Position position) throws InvalidPositionException {
        super(position, 3, new boolean[3], ShipType.SUB_MARINE);
    }
}
