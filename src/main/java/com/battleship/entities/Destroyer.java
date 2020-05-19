package com.battleship.entities;

import com.battleship.exceptions.InvalidPositionException;

public class Destroyer extends Ship {

    public Destroyer(Position position) throws InvalidPositionException {
        super(position, 2, new boolean[2], ShipType.DESTROYER);
    }
}
