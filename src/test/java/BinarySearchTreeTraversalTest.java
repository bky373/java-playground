import org.example.TreeNode;
import org.junit.jupiter.api.Test;
import support.TreeNodeSupport;

public class BinarySearchTreeTraversalTest {

    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    @Test
    void testBstTraversal() {
//        9,3,15,20,7
        TreeNode node = TreeNodeSupport.linkLeftAndRight(3, 9, 20);
        TreeNodeSupport.linkLeftAndRight(node.right, 15, 7);

//        inorder(node);
        preorder(node);
    }

    /*
     * Inorder traversal (중위 순회)
     * - 재귀적으로 왼쪽 서브 트리 순회
     * - 현재 노드 방문 (예, 값 출력)
     * - 재귀적으로 오른쪽 서브 트리 순회
     */
    void inorder(TreeNode cur) {
        if (cur == null) {
            return;
        }
        inorder(cur.left);
        System.out.println("cur = " + cur);
        inorder(cur.right);
    }

    /*
     * Preorder traversal (전위 순회)
     * - 현재 노드 방문 (예, 값 출력)
     * - 재귀적으로 왼쪽 서브 트리 순회
     * - 재귀적으로 오른쪽 서브 트리 순회
     */
    void preorder(TreeNode cur) {
        if (cur == null) {
            return;
        }
        System.out.println("cur = " + cur);
        preorder(cur.left);
        preorder(cur.right);
    }
}
