import static support.TreeNodeSupport.NODE_0;
import static support.TreeNodeSupport.NODE_2;
import static support.TreeNodeSupport.NODE_3;
import static support.TreeNodeSupport.NODE_4;
import static support.TreeNodeSupport.NODE_5;
import static support.TreeNodeSupport.NODE_6;
import static support.TreeNodeSupport.NODE_7;
import static support.TreeNodeSupport.NODE_8;
import static support.TreeNodeSupport.NODE_9;

import java.util.LinkedList;
import java.util.Queue;

import org.example.TreeNode;
import org.junit.jupiter.api.Test;
import support.TreeNodeSupport;

public class TreeTest {

    @Test
    void lowestCommonAncestor() {
//                   6,
//                2,    8,
//              0,  4, 7, 9,
//        null,null,3,5
// p=0, q=5, LCA=2
// p=0, q=8, LCA=6
// 자신을 포함한 p의 부모 목록 pOrParents
// 자신을 포함한 q의 부모 목록 qOrParents size 비교 후 작은 목록 인덱스부터 탐색
        TreeNode root = TreeNodeSupport.linkLeftAndRight(NODE_6, NODE_2, NODE_8);
        TreeNodeSupport.linkLeftAndRight(NODE_2, NODE_0, NODE_4);
        TreeNodeSupport.linkLeftAndRight(NODE_8, NODE_7, NODE_9);
        TreeNodeSupport.linkLeftAndRight(NODE_4, NODE_3, NODE_5);
        TreeNode p = NODE_0;
        TreeNode q = NODE_8;

        Queue<TreeNode> pOrParents = new LinkedList<>();
        Queue<TreeNode> qOrParents = new LinkedList<>();

        pOrParents.add(root);
        qOrParents.add(root);

        dfs(root, NODE_4, pOrParents);
        System.out.println("pOrParents = " + pOrParents);
//        while (!pOrParents.isEmpty()) {
//            TreeNode cur = pOrParents.poll();
//
//            if (cur == p) {
//                pOrParents.add(cur);
//                break;
//            }
//
//        }
        System.out.println("root = " + root);
    }

    TreeNode dfs(TreeNode node, TreeNode target, Queue<TreeNode> nodes) {
        if (node == null) {
            return null;
        }
        if (node == target) {
            return target;
        }
        nodes.add(node);
        TreeNode left = dfs(node.left, target, nodes);
        TreeNode right = dfs(node.right, target, nodes);
        if (left == null || right == null) {
            return null;
        }
//        if (left == null) {
//            nodes.remove(node.right);
//        }
        return null;
    }
}
