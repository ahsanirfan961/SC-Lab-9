package org.example;

import java.util.Collections;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class GraphStaticTest {

    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals(Collections.emptySet(), Graph.empty().vertices());
    }
    
}
