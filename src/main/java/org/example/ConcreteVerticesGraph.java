/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package org.example;

import java.util.*;

import static org.junit.Assert.assertFalse;

public class ConcreteVerticesGraph implements Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();

    public ConcreteVerticesGraph() {
        checkRep();
    }

    private void checkRep() {
        assertFalse(vertices.contains(null));
    }

    @Override public boolean add(String vertex) {
        for (Vertex v : vertices) {
            if (v.getLabel().equals(vertex)) {
                return false;
            }
        }
        vertices.add(new Vertex(vertex));
        checkRep();
        return true;
    }

    @Override public int set(String source, String target, int weight) {
        Vertex sourceVertex = null;
        Vertex targetVertex = null;
        for (Vertex v : vertices) {
            if (v.getLabel().equals(source)) {
                sourceVertex = v;
            }
            if (v.getLabel().equals(target)) {
                targetVertex = v;
            }
        }
        if (sourceVertex == null) {
            sourceVertex = new Vertex(source);
            vertices.add(sourceVertex);
        }
        if (targetVertex == null) {
            targetVertex = new Vertex(target);
            vertices.add(targetVertex);
        }
        int previousWeight = sourceVertex.getEdges().getOrDefault(target, 0);
        if (weight == 0) {
            sourceVertex.removeEdge(target);
        } else {
            sourceVertex.addEdge(target, weight);
        }
        checkRep();
        return previousWeight;
    }

    @Override public boolean remove(String vertex) {
        Vertex toRemove = null;
        for (Vertex v : vertices) {
            if (v.getLabel().equals(vertex)) {
                toRemove = v;
                break;
            }
        }
        if (toRemove == null) {
            return false;
        }
        vertices.remove(toRemove);
        for (Vertex v : vertices) {
            v.removeEdge(vertex);
        }
        checkRep();
        return true;
    }

    @Override public Set<String> vertices() {
        Set<String> vertexLabels = new HashSet<>();
        for (Vertex v : vertices) {
            vertexLabels.add(v.getLabel());
        }
        return vertexLabels;
    }

    @Override public Map<String, Integer> sources(String target) {
        Map<String, Integer> sources = new HashMap<>();
        for (Vertex v : vertices) {
            if (v.getEdges().containsKey(target)) {
                sources.put(v.getLabel(), v.getEdges().get(target));
            }
        }
        return sources;
    }

    @Override public Map<String, Integer> targets(String source) {
        for (Vertex v : vertices) {
            if (v.getLabel().equals(source)) {
                return v.getEdges();
            }
        }
        return new HashMap<>();
    }
}


class Vertex {

    private final String label;
    private final Map<String, Integer> edges;

    public Vertex(String label) {
        this.label = label;
        this.edges = new HashMap<>();
        checkRep();
    }

    // Check the representation invariant
    private void checkRep() {
        assert label != null;
        assert edges != null;
        assert !edges.containsKey(null);
        assert !edges.containsValue(null);
    }

    public String getLabel() {
        return label;
    }

    public Map<String, Integer> getEdges() {
        return new HashMap<>(edges);
    }

    public void addEdge(String target, int weight) {
        edges.put(target, weight);
        checkRep();
    }

    public void removeEdge(String target) {
        edges.remove(target);
        checkRep();
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                ", edges=" + edges +
                '}';
    }
    
}
