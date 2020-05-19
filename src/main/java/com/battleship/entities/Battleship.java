package com.battleship.entities;

import com.battleship.exceptions.InvalidPositionException;

public class Battleship extends Ship {

    public Battleship(Position position) throws InvalidPositionException {
        super(position, 4, new boolean[4], ShipType.BATTLE_SHIP);
    }
}
