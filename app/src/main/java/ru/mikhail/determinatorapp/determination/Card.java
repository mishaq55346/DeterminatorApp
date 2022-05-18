package ru.mikhail.determinatorapp.determination;

import java.util.List;

public class Card {
    private final int key;
    private final List<Node> nodes;

    public Card(int key, List<Node> nodes) {
        this.key = key;
        this.nodes = nodes;
    }

    public int getKey() {
        return key;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
