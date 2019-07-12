package core;

import java.util.ArrayList;
import java.util.List;

/**
 * 树
 *
 * @param <T>
 */
public class TreeNode<T> {
    private T name;
    // 用于toString 层次
    public long deep;
    // 用于记录子节点
    public ArrayList<TreeNode> sonNode = new ArrayList<>();
    // 用于toString 记录分组
    public List<List<Integer>> label = new ArrayList<>();
    // 用于记录叶子节点记录类型
    public Double res = null;

    public TreeNode(T name, long deep) {
        this.name = name;
        this.deep = deep;
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
        if (res != null) {
            return repeatString("\t", deep * 3, " ") + "TreeNode 特征选取列 = " + name + "," + "分类序号 = " + label + "," + "结果 = " + res + ":\n " +
                    repeatString("\t", deep * 3, " ") + sonNode + "\n";
        } else {
            return repeatString("\t", deep * 3, " ") + "TreeNode 特征选取列 = " + name + "," + "分类序号 = " + label + ":\n " +
                    repeatString("\t", deep * 3, " ") + sonNode + "\n";
        }
    }
}