package com.tw.step.rover.commands;

import com.tw.step.rover.rover.Rover;

import java.util.ArrayList;

public class RoverCommands extends ArrayList<RoverCommand> implements RoverCommand {
    @Override
    public void execute(Rover rover) {
        for (RoverCommand roverCommand : this) {
            roverCommand.execute(rover);
        }
    }
}
