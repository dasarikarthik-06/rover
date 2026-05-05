package com.tw.step.rover.roversystem;

import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.rover.Rover;

import java.util.HashMap;

public class RoverSystem {
    private Rover rover;
    private RoverCommands roverCommands;
    private HashMap<String, Rover> rovers = new HashMap<>();
    private HashMap<String, RoverCommands> roverInstructions = new HashMap<>();

    public void addRover(Rover rover) {
        this.rover = rover;
    }

    public void addCommands(RoverCommands roverCommands) {
        this.roverCommands = roverCommands;
    }

    public void execute() {
        this.roverCommands.execute(this.rover);
    }

    @Override
    public String toString() {
        return rover.toString();
    }

    public void addRover(String roverId, Rover rover) {
        rovers.put(roverId, rover);
    }

    public void assignInstructions(String roverId, RoverCommands roverCommands) {
        roverInstructions.put(roverId, roverCommands);
    }
}
