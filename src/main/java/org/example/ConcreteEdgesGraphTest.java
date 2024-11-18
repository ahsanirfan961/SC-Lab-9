/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package org.example;


public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }

    
}
