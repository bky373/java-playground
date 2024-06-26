import org.example.TreeNode;
import org.junit.jupiter.api.Test;
import support.TreeNodeSupport;

public class InorderTraversalTest {

    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

    /*
     * inorder traversal (중위 순회)
     * - 재귀적으로 왼쪽 서브 트리 순회
     * - 현재 노드 방문 (예, 값 출력)
     * - 재귀적으로 오른쪽 서브 트리 순회
     */
    @Test
    void testInorder() {
//        9,3,15,20,7
        TreeNode node = TreeNodeSupport.linkLeftAndRight(3, 9, 20);
        TreeNodeSupport.linkLeftAndRight(node.right, 15, 7);

        inorder(node);
    }

    void inorder(TreeNode cur) {
        if (cur == null) {
            return;
        }
        inorder(cur.left);
        System.out.println("cur = " + cur);
        inorder(cur.right);
    }
}
