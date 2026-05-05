package com.tw.step.rover;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.boundary.InfinitePlateau;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.roversystem.RoverSystem;
import com.tw.step.rover.roversystem.RoverSystemParser;
import com.tw.step.rover.roversystem.RoverSystemScanner;

public class App {
    static void main() {
//        String text = """
//1 5 N
//LFFRFLFFFR
//                """;
        String text = """
5 5
R1 1 3 N
R2 1 4 N
R1: LFFRFLFFFR
R2: LRL
                """;

        RoverSystemScanner scanner = RoverSystemScanner.from(text);
        Navigator navigator = Navigator.create();
        Boundary boundary = new InfinitePlateau();
        CommandCreator commandCreator = new CommandCreator();
//        RoverSystemParser roverSystemParser = new RoverSystemParser(scanner, navigator, boundary, commandCreator);
        RoverSystemParser roverSystemParser = new RoverSystemParser(scanner, navigator, commandCreator);
        RoverSystem system = roverSystemParser.parse();
        system.execute();
        System.out.println(system);
    }
}
