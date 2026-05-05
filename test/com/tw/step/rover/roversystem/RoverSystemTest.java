package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.boundary.InfinitePlateau;
import com.tw.step.rover.boundary.Plateau;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.commands.MoveCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverSystemTest {
    @Test
    void shouldExecuteCommandsForAddedRover() {
        RoverSystem roverSystem = new RoverSystem();
        Rover rover = new Rover(new Coordinate(0, 0), Direction.N);
        RoverCommands commands = new RoverCommands();
        commands.add(new MoveCommand(Navigator.create(), new InfinitePlateau()));

        roverSystem.addRover("R", rover);
        roverSystem.addCommands("R", commands);
        roverSystem.execute();

        assertEquals("0 1 N Active\n", roverSystem.toString());
    }

    @Test
    void shouldExecuteCommandsForMultipleRovers() {
        RoverSystem roverSystem = new RoverSystem();

        Rover rover1 = new Rover(new Coordinate(0, 0), Direction.N);
        RoverCommands commands1 = new RoverCommands();
        commands1.add(new MoveCommand(Navigator.create(), new InfinitePlateau()));

        Rover rover2 = new Rover(new Coordinate(1, 1), Direction.E);
        RoverCommands commands2 = new RoverCommands();
        commands2.add(new MoveCommand(Navigator.create(), new InfinitePlateau()));

        roverSystem.addRover("R1", rover1);
        roverSystem.addCommands("R1", commands1);

        roverSystem.addRover("R2", rover2);
        roverSystem.addCommands("R2", commands2);

        roverSystem.execute();

        assertEquals("2 1 E Active\n0 1 N Active\n", roverSystem.toString());
    }

    @Test
    void shouldExecuteForOneRoverWithRoverId() {
        RoverSystem roverSystem = new RoverSystem();
        String text = """
                5 5
                2 3 N
                LRLRF
                """;

        RoverSystemScanner scanner = RoverSystemScanner.from(text);
        Navigator navigator = Navigator.create();

        Boundary boundary = Plateau.extractBoundary(scanner);
        CommandCreator commandCreator = new CommandCreator();

        RoverSystemParser roverSystemParser = new RoverSystemParser(scanner, navigator, boundary, commandCreator);
        RoverSystem system = roverSystemParser.parse();

        system.execute();
        assertEquals("2 4 N Active\n", system.toString());
    }
}
