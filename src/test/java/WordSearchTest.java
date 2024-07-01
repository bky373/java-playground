import org.example.Trie;
import org.example.WordDictionary;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class WordSearchTest {

    // https://leetcode.com/problems/implement-trie-prefix-tree/
    @Test
    void trie() {
//        TrieNode node = TrieNodeSupport.linkLeftsInOrder("apple");
//        System.out.println("node = " + node);
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        boolean search = trie.search("app");
        System.out.println("search = " + search);
        boolean startsWith = trie.startsWith("apple");
        System.out.println("startsWith = " + startsWith);
    }

    // https://leetcode.com/problems/design-add-and-search-words-data-structure/
    @Test
    void wordDictionary() {
// "addWord", ["at"],
// "addWord", ["and"],
// "addWord", ["an"],
// "addWord", ["add"],
// "search", ["a"],
// "search", [".at"],
// "addWord", ["bat"],
// "search", [".at"],
// "search", ["an."],
// "search", ["a.d."],
// "search", ["b."],
// "search", ["a.d"],
// "search"] ["."]]

        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        List<Boolean> result = new ArrayList<>();
        result.add(wordDictionary.search("a")); // false
        result.add(wordDictionary.search(".at")); // false
        wordDictionary.addWord("bat");
        result.add(wordDictionary.search(".at")); // true
        result.add(wordDictionary.search("an.")); // true
        result.add(wordDictionary.search("a.d.")); // false
        result.add(wordDictionary.search("b.")); // false
        result.add(wordDictionary.search("a.d")); // true
        result.add(wordDictionary.search(".")); // false

        result.forEach(System.out::println);
    }
}
