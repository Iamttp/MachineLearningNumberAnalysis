package ML;

import matrix.DoubleMatrix2D;

import java.util.*;

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
        // 1. 求取信息熵
        DoubleMatrix2D y = this._y;
        List<Double> p = new ArrayList<>();
        for (int i = 0; i < y.columns(); i++) {
            p.add(i, y.getQuick(0, i));
        }
        double entD = getEnt(p);

        // 2. 求取每个特征的信息增益
        List<Double> resUp = new ArrayList<>();
        DoubleMatrix2D train_X = this._fit_X;
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
        System.out.println("信息增益:\t" + resUp);

        // TODO 3. 根据最大信息增益，选为划分属性, 需要建一颗树


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
        System.out.println(res);
        double sum = 0;
        for (Double aDouble : res) {
            sum += aDouble * (Math.log(aDouble) / Math.log((double) 2));
        }
        return -sum;
    }

    @Override
    public double score(DoubleMatrix2D testX, DoubleMatrix2D testY) throws Exception {
        return 0;
    }
}
