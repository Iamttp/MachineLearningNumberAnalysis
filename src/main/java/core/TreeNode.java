package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 树
 *
 * @param <T>
 */
public class TreeNode<T> {
    private T name;
    public long deep;
    public ArrayList<TreeNode> sonNode = new ArrayList<>();
    public List<List<Integer>> label = new ArrayList<>();

    public TreeNode(long deep) {
        this.deep = deep;
    }

    public TreeNode(T name, long deep) {
        this.name = name;
        this.deep = deep;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public static String repeatString(String str, long n, String seg) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(str).append(seg);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "TreeNode 特征选取行 = " + name + "," + "分类序号 = " + label + ":\n " +
                repeatString("\t", deep * 3, " ") + sonNode + "\n" +
                repeatString("\t", deep * 3, " ");
    }
}