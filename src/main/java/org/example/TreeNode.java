package org.example;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "TreeNode{" + val + "}";
    }

    public String toStringWithLR() {
        return """
                TreeNode{v=%s, l=%s, r=%s}""".formatted(val,
                left != null ? left.toStringWithLR() : null,
                right != null ? right.toStringWithLR() : null);
    }
}
