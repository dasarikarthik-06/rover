package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.boundary.Plateau;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.commands.RoverCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;

public class RoverSystemParser {
    private final RoverSystemScanner scanner;
    private final Navigator navigator;
    private final CommandCreator commandCreator;

    public RoverSystemParser(RoverSystemScanner scanner, Navigator navigator, CommandCreator commandCreator) {
        this.scanner = scanner;
        this.navigator = navigator;
        this.commandCreator = commandCreator;
    }

    private Rover parseRover() {
        String roverId = scanner.consume();
        Coordinate coordinate = scanner.scanCoordinate();
        Direction heading = scanner.scanDirection();
        return new Rover(roverId, coordinate, heading);
    }

    public Boundary parseBoundary() {
        int maxX = scanner.scanNumber();
        int maxY = scanner.scanNumber();
        return new Plateau(new Coordinate(0, 0), new Coordinate(maxX, maxY));
    }


    public RoverSystem parse() {
        RoverSystem roverSystem = new RoverSystem();
        Boundary boundary = parseBoundary();
        while (scanner.peek() != null && !scanner.peek().contains(":")) {
            Rover rover = parseRover();
            String roverId = rover.getId();
            roverSystem.addRover(roverId, rover);
        }

        while (scanner.peek() != null) {
            parseInstructions(roverSystem, boundary);
        }
        return roverSystem;
    }

    private void parseInstructions(RoverSystem roverSystem, Boundary boundary) {
        String token = scanner.consume();
        String roverId = token.replace(":", "");
        RoverCommands roverCommands = parseRoverCommands(boundary);
        roverSystem.assignInstructions(roverId, roverCommands);
    }

    private RoverCommands parseRoverCommands(Boundary boundary) {
        RoverCommands roverCommands = new RoverCommands();
        String instructions = scanner.consume();
        for (int i = 0; i < instructions.length(); i++) {
            RoverCommand roverCommand = commandCreator.create(instructions.charAt(i), navigator, boundary);
            roverCommands.add(roverCommand);
        }

        return roverCommands;
    }
}
