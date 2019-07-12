package ML;

import core.MLBase;
import matrix.DoubleMatrix2D;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 朴素贝叶斯算法，朴素：特征条件独立；贝叶斯：基于贝叶斯定理
 * 在统计资料的基础上，依据某些特征，计算各个类别的概率，从而实现分类。
 * --------------------------------------------------------------------------拉普拉斯平滑
 * 假设在文本分类中，有3个类，C1、C2、C3，在指定的训练样本中，某个词语F1，在各个类中观测计数分别为=0，990，10，即概率为P(F1/C1)=0，P(F1/C2)=0.99，P(F1/C3)=0.01，对这三个量使用拉普拉斯平滑的计算方法如下：
 * 1/1003 = 0.001，991/1003=0.988    ，11/1003=0.011
 *
 * @author ttp
 */
public class NaiveBayes implements MLBase {
    private DoubleMatrix2D _fit_X;
    private DoubleMatrix2D _y;
    private double e;

    public NaiveBayes(double e) {
        // 误差e, 在判断特征时，允许处于一定范围内
        this.e = e;
    }

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
        //1.计算先验概率 P(A) 如：P(Iris的类别1)、P(是Iris的类别2)
        HashMap<Double, Double> map_category = new HashMap<>();
        DoubleMatrix2D y = this._y;
        for (int j = 0; j < y.columns(); j++) {
            if (map_category.containsKey(y.getQuick(0, j))) {
                map_category.put(y.getQuick(0, j), map_category.get(y.getQuick(0, j)) + 1);
            } else {
                map_category.put(y.getQuick(0, j), 0.0);
            }
        }
        Double allNum = 0.0;
        for (HashMap.Entry<Double, Double> entry : map_category.entrySet()) {
            allNum += entry.getValue();
        }
        for (HashMap.Entry<Double, Double> entry : map_category.entrySet()) {
            entry.setValue(entry.getValue() / allNum);
        }

        double[] res = new double[testX.rows()];
        for (int i = 0; i < testX.rows(); i++) {
            // TODO 优化， 使DoubleMatrix2D直接返回数组
            ArrayList<Double> in = new ArrayList<>();
            for (int j = 0; j < testX.columns(); j++) {
                in.add(testX.getQuick(i, j));
            }

            double[] res2 = new double[map_category.size()];
            int k = 0;
            for (HashMap.Entry<Double, Double> entry : map_category.entrySet()) {
                res2[k] = getP(in, entry.getKey());
                // 3. 对于类别1 的贝叶斯分子为： P(Iris的类别1) * P(Iris特征1| Iris的类别1) * P(Iris特征2| Iris的类别1) 全部取log
                res2[k] += Math.log(entry.getValue());
                k++;
            }
//            System.out.println(Arrays.toString(res2));
            // 4. 最终结果为： 谁的贝叶斯分子大，就是那个类别
            int indx = 0;
            double max = -99999;
            for (int j = 0; j < res2.length; j++) {
                if (res2[j] > max) {
                    indx = j;
                    max = res2[j];
                }
            }
            int indx1 = 0;
            for (HashMap.Entry<Double, Double> entry : map_category.entrySet()) {
                if (indx1 == indx) {
                    res[i] = entry.getKey();
                }
                indx1++;
            }
        }
//        System.out.println(Arrays.toString(res));
        return res;
    }

    /**
     * 2.计算 P(B|A) 如：P(Iris特征1| Iris的类别1) * P(Iris特征2| Iris的类别1) 、P(Iris特征1| Iris的类别2) * P(Iris特征2| Iris的类别2)
     *
     * @param in
     */
    private double getP(ArrayList<Double> in, double type) {
        double res = 0.0;
        for (int j = 0; j < in.size(); j++) {
            // 对于它的每个特征
            // !!!! 注意：如果在计算条件概率时，一个概率值为0，那么最后的乘积也是0。这会影响后验概率的计算结果，使分类产生偏差。为了降低这种影响，可以采用贝叶斯估计。 拉普拉斯平滑
            int equNum = 1, allNum = 1;
            for (int i = 0; i < this._fit_X.rows(); i++) {
                if (this._y.getQuick(0, i) == type) {
                    // 对于每个类别,如果特征近似相等
                    // TODO 突然发现，对于连续的值，这种方法有点问题，需要给定合适的e
                    if (Math.abs(in.get(j) - this._fit_X.getQuick(i, j)) < e) {
                        equNum++;
                    }
                    allNum++;
                }
            }
            // ！！！！另外遇到一个问题就是下溢出，这是由于太多很小的数相乘造成的。
            //这种解决方法是对乘机取自然对数。在代数中，ln(a*b*c)=ln(a)+ln(b)+ln(c)。
            res += Math.log((double) equNum / (double) allNum);
        }
        return res;
    }

    @Override
    public double score(DoubleMatrix2D testX, DoubleMatrix2D testY) throws Exception {
        double[] res = this.predict(testX);
        if (res.length != testY.columns()) {
            throw new Exception("Warning! your (res.length)" + res.length + "!= (testY.columns())" + testY.columns());
        }
        int isOk = 0;
        for (int i = 0; i < res.length; i++) {
            if (res[i] == testY.getQuick(0, i)) {
                isOk++;
            }
        }
        System.out.println(isOk);
        System.out.println(res.length);
        return (double) isOk / (double) res.length;
    }
}
