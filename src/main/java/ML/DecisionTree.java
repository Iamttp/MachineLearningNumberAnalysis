package ML;

import core.MLBase;
import core.TreeNode;
import javafx.util.Pair;
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
    private Map<Integer, Set<Double>> mapForFeature = new HashMap<>();

    /**
     * pair: Integer 存储树的边值（特征选取的值），Double 存储节点的值（哪一列特征）
     */
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
        for (int maxIndex = 0; maxIndex < this._fit_X.columns(); maxIndex++) {
            Set<Double> allFeat = new TreeSet<>();
            for (int j = 0; j < this._fit_X.rows(); j++) {
                allFeat.add(this._fit_X.getQuick(j, maxIndex));
            }
            mapForFeature.put(maxIndex, allFeat);
        }

        TreeNode<Pair<Integer, Double>> res = createTree(this._fit_X, this._y, -1, 0);
        System.out.println(res);

        double[] retRes = new double[testX.rows()];
        for (int i = 0; i < testX.rows(); i++) {
            double[] oneRow = testX.getOneRow(i);
            // TODO 树记录分类条件
            retRes[i] = getValforTree(res, oneRow);
        }
        System.out.println(Arrays.toString(retRes));
        return retRes;
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

    private TreeNode<Pair<Integer, Double>> createTree(DoubleMatrix2D train_X, DoubleMatrix2D y, int selValue, int deep) {
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
        System.out.println("信息增益:\t" + resUp);
        int maxIndex = 0;
        double max = -99999.0;
        for (int i = 0; i < resUp.size(); i++) {
            if (max < resUp.get(i)) {
                max = resUp.get(i);
                maxIndex = i;
            }
        }
        // 存储第几列
//        System.out.print("\t分类特征：\t" + maxIndex);
        TreeNode<Pair<Integer, Double>> node = new TreeNode<>(-1, deep);
        // 对于每一个特征, 找到特征可以取的值
        Set<Double> allFeat = this.mapForFeature.get(maxIndex);
        System.out.println(allFeat);
        for (double x : allFeat) {
            // x 对于每一个选取特征中可以取的值，找到哪些行传入list中
            List<Integer> list = new ArrayList<>();

            for (int j = 0; j < train_X.rows(); j++) {
                if (train_X.getQuick(j, maxIndex) == x) {
                    list.add(j);
                }
            }
            // 去除当前选中特征重新设置训练数据
            double[][] train_x = new double[list.size()][];
            double[][] train_y = new double[1][list.size()];
            // 去除选取的特征行后，存入train_x
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
            // pair: Integer 存储树的边值（特征选取的值），Double 存储节点的值（哪一列特征）
            node.edgeVal = new Pair<>(selValue, (double) maxIndex);
            double[] uniq = (y).getOneRow(0);
            Arrays.sort(uniq);
            // 所有的样本都相等
            if (list.size() != 0) {
//                Pair<Integer, Double> pair = new Pair<>(selValue, -1.0);
//                node.sonNode.add(new TreeNode<>((int) 9, pair, deep + 1));
                if (uniq[0] == uniq[uniq.length - 1]) {
                    Pair<Integer, Double> pair = new Pair<>(selValue, -1.0);
                    node.sonNode.add(new TreeNode<>((int) uniq[0], pair, deep + 1));
                    break;
                } else {
                    node.sonNode.add(createTree(new DenseDoubleMatrix2D(train_x), new DenseDoubleMatrix2D(train_y), (int) x, deep + 1));
                }
            } else {
                // TODO 针对null情况
                Pair<Integer, Double> pair = new Pair<>(selValue, -1.0);
                node.sonNode.add(new TreeNode<>((int) 9, pair, deep + 1));
                break;
            }
        }
//        node.label = selList;
        return node;
    }

    private double getValforTree(TreeNode<Pair<Integer, Double>> res, double[] oneRow) {
        double index = res.edgeVal.getValue();
        double x = oneRow[(int) index];
        for (TreeNode son : res.sonNode) {
            Pair<Integer, Double> edgeVal = (Pair<Integer, Double>) son.edgeVal;
            if (edgeVal.getKey() == x) {
                if (son.getSelfId() != -1) {
                    return son.getSelfId();
                } else {
                    return getValforTree(son, oneRow);
                }
            }
            if (son.getSelfId() != -1) {
                return son.getSelfId();
            }
        }
        return -1;
    }

    @Override
    public double score(DoubleMatrix2D testX, DoubleMatrix2D testY) throws Exception {
        return 0;
    }
}
