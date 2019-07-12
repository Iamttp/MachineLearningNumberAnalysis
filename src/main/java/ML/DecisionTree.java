package ML;

import core.MLBase;
import core.TreeNode;
import matrix.DoubleMatrix2D;
import matrix.impl.DenseDoubleMatrix2D;

import java.util.*;
import java.util.List;

/**
 * 决策树（decision tree）
 * ID3算法， 不能用于连续数据
 */
public class DecisionTree implements MLBase {
    private DoubleMatrix2D _fit_X;
    private DoubleMatrix2D _y;

    @Override
    public MLBase fit(DoubleMatrix2D trainX, DoubleMatrix2D trainY) throws Exception {
        if (trainX.rows() != trainY.columns()) {
            throw new Exception("Warning! your (trainX.rows())" + trainX.rows() + "!= (testY.columns())" + trainY.columns());
        }
        this._fit_X = trainX;
        this._y = trainY;
        return this;
    }

    @Override
    public double[] predict(DoubleMatrix2D testX) {
        List<Integer> labelRow = new ArrayList<>();
        for (int i = 0; i < this._y.columns(); i++) {
            labelRow.add(i + 1);
        }
        TreeNode<Integer> res = createTree(this._fit_X, this._y, labelRow, 0);
        // TODO 难看的很依旧QAQ
        System.out.println(res.toString().replaceAll("[\\[\\]]", " "));
        return new double[0];
    }

    /**
     * 获取信息熵, 传入数组后，进行分类
     */
    private double getEnt(List<Double> p) {
        List<Double> res = new ArrayList<>();
        Map<Double, Integer> map = new HashMap<>();
        for (int j = 0; j < p.size(); j++) {
            double num = p.get(j);
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (HashMap.Entry<Double, Integer> entry : map.entrySet()) {
            res.add((double) entry.getValue() / (double) p.size());
        }
        double sum = 0;
        for (Double aDouble : res) {
            sum += aDouble * (Math.log(aDouble) / Math.log((double) 2));
        }
        return -sum;
    }

    private TreeNode<Integer> createTree(DoubleMatrix2D train_X, DoubleMatrix2D y, List<Integer> labelRow, long deep) {
        double uniq = y.getQuick(0, 0);
        boolean flag = false;
        for (int i = 0; i < y.columns(); i++) {
            if (uniq != y.getQuick(0, i)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            TreeNode<Integer> node = new TreeNode<>(null, deep);
            node.res = uniq;
            return node;
        }

        // 1. 求取信息熵
        List<Double> p = new ArrayList<>();
        for (int i = 0; i < y.columns(); i++) {
            p.add(i, y.getQuick(0, i));
        }
        double entD = getEnt(p);
        // 2. 求取每个特征的信息增益
        List<Double> resUp = new ArrayList<>();
        for (int i = 0; i < train_X.columns(); i++) {
            // 对于每一个特征, 找到特征可以取的值
            double sum = 0;
            Set<Double> allFeat = new TreeSet<>();
            for (int j = 0; j < train_X.rows(); j++) {
                allFeat.add(train_X.getQuick(j, i));
            }

            for (double x : allFeat) {
                double count = 0;
                List<Double> list = new ArrayList<>();
                for (int j = 0; j < train_X.rows(); j++) {
                    if (train_X.getQuick(j, i) == x) {
                        list.add(y.getQuick(0, j));
                        count++;
                    }
                }
                sum += count / y.columns() * getEnt(list);
            }
            resUp.add(entD - sum);
        }
        // TODO 3. 根据最大信息增益，选为划分属性, 需要建一颗树
        System.out.print("信息增益:\t" + resUp);
        int maxIndex = 0;
        double max = -99999.0;
        for (int i = 0; i < resUp.size(); i++) {
            if (max < resUp.get(i)) {
                max = resUp.get(i);
                maxIndex = i;
            }
        }
        // 存储第几列
        System.out.print("\t分类特征：\t" + maxIndex);
        TreeNode<Integer> node = new TreeNode<>(maxIndex, deep);
        // 对于每一个特征, 找到特征可以取的值
        Set<Double> allFeat = new TreeSet<>();
        for (int j = 0; j < train_X.rows(); j++) {
            allFeat.add(train_X.getQuick(j, maxIndex));
        }

        List<List<Integer>> selList = new ArrayList<>();
        for (double x : allFeat) {
            List<Integer> list = new ArrayList<>();
            List<Integer> listLable = new ArrayList<>();

            for (int j = 0; j < train_X.rows(); j++) {
                if (train_X.getQuick(j, maxIndex) == x) {
                    listLable.add(labelRow.get(j));
                    list.add(j);
                }
            }
            selList.add(listLable);
            // 去除当前选中特征重新设置训练数据
            double[][] train_x = new double[list.size()][];
            double[][] train_y = new double[1][list.size()];
            for (int i = 0; i < list.size(); i++) {
                ArrayList<Double> res = new ArrayList<>();
                for (int j = 0; j < train_X.columns(); j++) {
                    if (j == maxIndex) {
                        continue;
                    }
                    res.add(train_X.getQuick(list.get(i), j));
                }
                double[] res1 = new double[res.size()];
                for (int j = 0; j < res.size(); j++) {
                    res1[j] = res.get(j);
                }
                train_x[i] = res1;
            }
            for (int i = 0; i < list.size(); i++) {
                train_y[0][i] = y.getQuick(0, list.get(i));
            }
            System.out.println("\t分类序号：\t" + listLable);
            node.sonNode.add(createTree(new DenseDoubleMatrix2D(train_x), new DenseDoubleMatrix2D(train_y), listLable, deep + 1));
        }
        node.label = selList;
        return node;
    }

    @Override
    public double score(DoubleMatrix2D testX, DoubleMatrix2D testY) throws Exception {
        return 0;
    }
}
