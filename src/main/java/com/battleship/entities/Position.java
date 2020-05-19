package com.battleship.entities;

public class Position {

    private final Point from;
    private final Point to;

    public Position(final Point from,
                    final Point to) {
        this.from = from;
        this.to = to;
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }
}
