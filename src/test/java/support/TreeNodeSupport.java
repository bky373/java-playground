package support;

import org.example.TreeNode;

public class TreeNodeSupport {

    public static final TreeNode NODE_0 = new TreeNode(0);
    public static final TreeNode NODE_1 = new TreeNode(1);
    public static final TreeNode NODE_2 = new TreeNode(2);
    public static final TreeNode NODE_3 = new TreeNode(3);
    public static final TreeNode NODE_4 = new TreeNode(4);
    public static final TreeNode NODE_5 = new TreeNode(5);
    public static final TreeNode NODE_6 = new TreeNode(6);
    public static final TreeNode NODE_7 = new TreeNode(7);
    public static final TreeNode NODE_8 = new TreeNode(8);
    public static final TreeNode NODE_9 = new TreeNode(9);
    public static final TreeNode NODE_10 = new TreeNode(10);

    public static TreeNode linkLeftAndRight(int parent, int left, int right) {
        TreeNode node = new TreeNode(parent);
        node.left = new TreeNode(left);
        node.right = new TreeNode(right);
        return node;
    }

    public static TreeNode linkLeftAndRight(TreeNode parent, int left, int right) {
        parent.left = new TreeNode(left);
        parent.right = new TreeNode(right);
        return parent;
    }

    public static TreeNode linkLeftAndRight(TreeNode node, TreeNode left, TreeNode right) {
        node.left = left;
        node.right = right;
        return node;
    }
}
