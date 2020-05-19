package com.battleship.entities;

public class Square {

    private final Point point;
    private Ship ship;
    private boolean isHit;

    public Square(final Point point) {
        this.point = point;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public Point getPoint() {
        return point;
    }

    public boolean doesContainsShip() {
        return ship != null;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
