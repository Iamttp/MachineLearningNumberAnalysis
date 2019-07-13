package core;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeNodeTest {

    @Test
    public void repeatString() {
        TreeNode<String> treeNode = new TreeNode<>(1, "top", 0);
        TreeNode<String> treeNode3 = new TreeNode<>(2, "top3");
        TreeNode<String> treeNode4 = new TreeNode<>(5, "top4");

        TreeNode<String> treeNode312 = new TreeNode<>(312, "top312");
        TreeNode<String> treeNode1234 = new TreeNode<>(1234, "top1234");
        TreeNode<String> treeNode3112 = new TreeNode<>(3112, "top3112");
        TreeNode<String> treeNode4123 = new TreeNode<>(4123, "top4123");
        treeNode.addSonNode(treeNode3);
        treeNode.addSonNode(treeNode4);
        treeNode3.addSonNode(treeNode312);
        treeNode3.addSonNode(treeNode3112);
        treeNode4.addSonNode(treeNode4123);
        treeNode4.addSonNode(treeNode1234);

        System.out.println(treeNode);
    }
}