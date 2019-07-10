package ML;

import matrix.DoubleMatrix2D;

/**
 * 朴素贝叶斯算法，朴素：特征条件独立；贝叶斯：基于贝叶斯定理
 *
 * @author ttp
 */
public class NaïveBayes implements MLBase {
    @Override
    public MLBase fit(DoubleMatrix2D trainX, DoubleMatrix2D trainY) {
        return null;
    }

    @Override
    public double[] predict(DoubleMatrix2D testX) {
        return new double[0];
    }

    @Override
    public double score(DoubleMatrix2D testX, DoubleMatrix2D testY) throws Exception {
        return 0;
    }
}
