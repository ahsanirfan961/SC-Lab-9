package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class GraphPoet {
    
    private final Graph<String> graph = new ConcreteVerticesGraph();

    public GraphPoet(File corpus) throws IOException {
        List<String> lines = java.nio.file.Files.readAllLines(corpus.toPath());
        for (String line : lines) {
            String[] words = line.split("\\W+");
            for (int i = 0; i < words.length - 1; i++) {
                String source = words[i].toLowerCase();
                String target = words[i + 1].toLowerCase();
                int weight = graph.targets(source).getOrDefault(target, 0) + 1;
                graph.set(source, target, weight);
            }
        }
        checkRep();
    }

    private void checkRep() {
        // Ensure that the graph's representation invariant holds
        for (String vertex : graph.vertices()) {
            assert vertex != null : "vertex is null";
        }
        for (String source : graph.vertices()) {
            for (Map.Entry<String, Integer> target : graph.targets(source).entrySet()) {
                assert target.getValue() > 0 : "edge weight is non-positive";
            }
        }
    }

    public String poem(String input) {
        String[] words = input.split("\\W+");
        StringBuilder poem = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            String word = words[i];
            poem.append(word).append(" ");

            String nextWord = words[i + 1];
            Map<String, Integer> targets = graph.targets(word.toLowerCase());
            if (targets.containsKey(nextWord.toLowerCase())) {
                poem.append(nextWord).append(" ");
            } else {
                String bridgeWord = null;
                int maxWeight = 0;
                for (Map.Entry<String, Integer> entry : targets.entrySet()) {
                    String candidate = entry.getKey();
                    if (graph.targets(candidate).containsKey(nextWord.toLowerCase()) && entry.getValue() > maxWeight) {
                        bridgeWord = candidate;
                        maxWeight = entry.getValue();
                    }
                }
                if (bridgeWord != null) {
                    poem.append(bridgeWord).append(" ");
                }
                poem.append(nextWord).append(" ");
            }
        }

        if (words.length > 0) {
            poem.append(words[words.length - 1]);
        }

        return poem.toString().trim();
    }

    @Override
    public String toString() {
        return "GraphPoet with graph: " + graph.toString();
    }
    
}
