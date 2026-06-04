package com.tw.step.rover.roversystem;

import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.rover.Rover;

import java.util.HashMap;

public class RoverSystem {
    private HashMap<String, Rover> rovers = new HashMap<>();
    private HashMap<String, RoverCommands> commands = new HashMap<>();

    public void execute() {
        for (String roverId : rovers.keySet()) {
            Rover rover = rovers.get(roverId);
            RoverCommands roverCommands = commands.get(roverId);
            roverCommands.execute(rover);
        }
    }

    @Override
    public String toString() {
        StringBuilder roversPosition = new StringBuilder("");
        for (String roverId : rovers.keySet()) {
            Rover rover = rovers.get(roverId);
            roversPosition.append(rover.toString());
            roversPosition.append("\n");
        }
        return roversPosition.toString();
    }

    public void addRover(String roverId, Rover rover) {
        rovers.put(roverId, rover);
    }

    public void addCommands(String roverId, RoverCommands roverCommands) {
        commands.put(roverId, roverCommands);
    }
}
