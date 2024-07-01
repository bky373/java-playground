package org.example;

// https://leetcode.com/problems/implement-trie-prefix-tree/
public class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.contains(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.getSubNode(ch);
        }
        node.isEndOfWord = true;
    }


    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String word) {
        return searchPrefix(word) != null;
    }

    public TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node == null) {
                return null;
            }
            if (!node.contains(word.charAt(i))) {
                return null;
            }
            node = node.getSubNode(word.charAt(i));
        }
        return node;
    }


    @Override
    public String toString() {
        return "Trie{" +
                "root=" + root +
                '}';
    }
}
