package com.battleship.entities;

import com.battleship.exceptions.InvalidPositionException;

public abstract class Ship {

    private final Position position;
    private final int length;
    private final boolean[] hits;
    private final ShipType shipType;

    public Ship(final Position position,
                final int length,
                final boolean[] hits,
                final ShipType shipType) throws InvalidPositionException {
        validate(position, length);
        this.position = position;
        this.length = length;
        this.hits = hits;
        this.shipType = shipType;
    }

    private void validate(final Position position, final int length) throws InvalidPositionException {
        final Point from = position.getFrom();
        final Point to = position.getTo();

        int xDiff = Math.abs(from.getX() - to.getX());
        int yDiff = Math.abs(from.getY() - to.getY());

        if (xDiff != 0 && yDiff != 0) {
            throw new InvalidPositionException("Invalid Positions");
        }

        if (xDiff == 0 && yDiff+1 < length) {
            throw new InvalidPositionException("Invalid Positions");
        }

        if (yDiff == 0 && xDiff+1 < length) {
            throw new InvalidPositionException("Invalid Positions");
        }

    }

    public Position getPosition() {
        return position;
    }


    public int getLength() {
        return length;
    }

    public boolean[] getHits() {
        return hits;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public boolean markAndCheckDamaged(final Point point) {
        int xDiff = Math.abs(position.getFrom().getX() - point.getX());
        if (xDiff != 0) {
            hits[xDiff] = true;
        } else {
            int yDiff = Math.abs(position.getFrom().getY() - point.getY());
            hits[yDiff] = true;
        }
        return isDamaged();
    }

    public boolean isDamaged() {
        for (boolean hit : hits) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }
}
