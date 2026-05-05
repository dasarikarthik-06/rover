package com.tw.step.rover.boundary;

import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.roversystem.RoverSystemScanner;

public class Plateau implements Boundary {
    private final Coordinate bottomLeft;
    private final Coordinate topRight;

    public Plateau(Coordinate bottomLeft, Coordinate topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public static Boundary extractBoundary(RoverSystemScanner scanner) {
        Coordinate bottomLeft = new Coordinate(0, 0);
        Coordinate topLeft = scanner.scanBoundary();
        return new Plateau(bottomLeft, topLeft);
    }

    @Override
    public boolean isWithin(Coordinate coord) {
        return coord.isWithin(bottomLeft, topRight);
    }
}
