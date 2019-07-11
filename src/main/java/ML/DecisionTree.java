package ML;

import matrix.DoubleMatrix2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        DoubleMatrix2D y = this._y;
        List<Double> p = new ArrayList<>();
        for (int i = 0; i < y.columns(); i++) {
            p.add(i, y.getQuick(0, i));
        }
        double entD = getEnt(p);
        System.out.println(entD);
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
