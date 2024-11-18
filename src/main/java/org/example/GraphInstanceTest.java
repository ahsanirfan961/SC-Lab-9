package org.example;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class GraphInstanceTest {

    public abstract Graph<String> emptyInstance();

    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("test for empty vertices", Collections.emptySet(), emptyInstance().vertices());
    }

    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("test"));
        assertTrue(graph.vertices().contains("test"));
    }

    @Test
    public void testAddDuplicateVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("test"));
        assertFalse(graph.add("test"));
    }

    @Test
    public void testSetEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("test1");
        graph.add("test2");
        int previousWeight = graph.set("test1", "test2", 5);
        assertEquals(0, previousWeight);
        assertEquals(5, (int) graph.targets("test1").get("test2"));

        int updatedWeight = graph.set("test1", "test2", 10);
        assertEquals(5, updatedWeight);
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("test1");
        graph.add("test2");
        graph.set("test1", "test2", 5);
        assertTrue(graph.remove("test1"));
        assertFalse(graph.vertices().contains("test1"));
        assertFalse(graph.targets("test1").containsKey("test2"));
    }

    @Test
    public void testSourcesAndTargets() {
        Graph<String> graph = emptyInstance();
        graph.add("testA");
        graph.add("testB");
        graph.add("testC");
        graph.set("testA", "testB", 5);
        graph.set("testC", "testB", 3);

        Map<String, Integer> sourcesToB = graph.sources("testB");
        assertEquals(2, sourcesToB.size());
        assertEquals(5, (int) sourcesToB.get("testA"));
        assertEquals(3, (int) sourcesToB.get("testC"));

        Map<String, Integer> targetsFromA = graph.targets("testA");
        assertEquals(1, targetsFromA.size());
        assertEquals(5, (int) targetsFromA.get("testB"));
    }
}
