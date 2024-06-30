import org.example.Trie;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/implement-trie-prefix-tree/
public class TrieTest {

    @Test
    void test() {
//        TrieNode node = TrieNodeSupport.linkLeftsInOrder("apple");
//        System.out.println("node = " + node);
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        boolean search = trie.search("app");
        System.out.println("search = " + search);
        boolean startsWith = trie.startsWith("apple");
        System.out.println("startsWith = " + startsWith);

//        TrieNode node = new TrieNode();
//        node.put();
//        TrieNode a = node.getSubNode('a');
//        System.out.println("a = " + a);

//        trie
//        boolean check = trie.check(node, "apple", 0);
//        System.out.println("check = " + check);
    }
}
