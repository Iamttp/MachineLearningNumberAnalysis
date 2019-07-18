package core;

import java.util.ArrayList;
import java.util.List;

/**
 * 树，树的边的值由子节点的edgeVal存储， T即为边值的类型
 * 也可以设置T为结构体，记录边值和节点值
 * <p>
 * // 用于toString 记录分组
 * //    public List<List<Integer>> label = new ArrayList<>();
 * // 用于记录叶子节点记录类型
 * //    public Double res = null;
 *
 * @author ttp
 */
public class TreeNode<T> {
    private int parentId;
    private int selfId;
    public T edgeVal;
    public List<TreeNode> sonNode;

    /**
     * 用于toString 层次
     */
    private long deep;

    public TreeNode(int selfId) {
        this.selfId = selfId;
        sonNode = new ArrayList<>();
    }

    public TreeNode(int selfId, long deep) {
        this.selfId = selfId;
        this.deep = deep;
        sonNode = new ArrayList<>();
    }

    public TreeNode(int selfId, T edgeVal) {
        this.selfId = selfId;
        this.edgeVal = edgeVal;
        sonNode = new ArrayList<>();
    }

    public TreeNode(int selfId, T edgeVal, long deep) {
        this.selfId = selfId;
        this.deep = deep;
        this.edgeVal = edgeVal;
        sonNode = new ArrayList<>();
    }

    public void addSonNode(TreeNode treeNode) {
        treeNode.parentId = this.selfId;
        treeNode.deep = this.deep + 1;
        this.sonNode.add(treeNode);
    }

    public boolean isLeaf() {
        if (sonNode == null) {
            return true;
        } else {
            return sonNode.isEmpty();
        }
    }

    @Override
    public String toString() {
        return iteratorTree(this);
    }

    public static String repeatString(String str, long n, String seg) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(str).append(seg);
        }
        return sb.toString();
    }

    /**
     * 遍历多叉树
     */
    public String iteratorTree(TreeNode manyTreeNode) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("|").append(repeatString("_", ((TreeNode) manyTreeNode).deep * 3, " ")).append("TreeNode: ").append(((TreeNode) manyTreeNode).selfId).append(",edgeVal: ").append(((TreeNode) manyTreeNode).edgeVal).append(":\n ");
        for (Object index : manyTreeNode.sonNode) {
            if (((TreeNode) index).sonNode != null) {
                buffer.append(iteratorTree(((TreeNode) index)));
            }
        }
        return buffer.toString();
    }

    public int getSelfId() {
        return selfId;
    }
}