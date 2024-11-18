/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package org.example;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GraphPoetTest {

    @Test
    public void basicPoemGeneration() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/java/org/example/mugar-omni-theater.txt"));
        String input = "Test the system.";
        String expected = "Test of the the system system";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void noBridgeWords() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/java/org/example/mugar-omni-theater.txt"));
        String input = "Hello world.";
        String expected = "Hello world world";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void emptyInput() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/java/org/example/mugar-omni-theater.txt"));
        String input = "";
        String expected = "";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void caseInsensitivity() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/java/org/example/mugar-omni-theater.txt"));
        String input = "Hello HELLO hello.";
        String expected = "Hello HELLO HELLO hello hello";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void specialCharacters() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/java/org/example/mugar-omni-theater.txt"));
        String input = "Hello, world!";
        String expected = "Hello world world";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void bridgeWordsInMiddle() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/java/org/example/mugar-omni-theater.txt"));
        String input = "This is a test.";
        String expected = "This is is a a test test";
        assertEquals(expected, poet.poem(input));
    }
}
