package org.example;

import java.util.Arrays;

public class TrieNode {
    private static final int MAX_SIZE = 26;

    public TrieNode[] subNodes;
    public boolean isEndOfWord;

    public TrieNode() {
        subNodes = new TrieNode[MAX_SIZE];
    }

    public boolean contains(char ch) {
        return subNodes[ch - 'a'] != null;
    }

    public TrieNode getSubNode(char ch) {
        return subNodes[ch - 'a'];
    }

    public void put(char ch, TrieNode node) {
        subNodes[ch - 'a'] = node;
    }

    @Override
    public String toString() {
        return "TrieNode{sub=" + Arrays.toString(subNodes) + "}";
    }
}
