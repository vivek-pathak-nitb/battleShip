package com.battleship.entities;

import com.battleship.exceptions.InvalidPositionException;

public class Cruiser extends Ship {

    public Cruiser(Position position) throws InvalidPositionException {
        super(position, 3, new boolean[3], ShipType.CRUISER);
    }
}
