package com.tw.step.rover;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void shouldPrintFinalRoverPosition() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            App.main();
        } finally {
            System.setOut(originalOut);
        }

        assertEquals("1 4 W Active\n-1 3 N Lost\n" + System.lineSeparator(), output.toString());
    }
}
