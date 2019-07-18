package ML;

import matrix.DoubleMatrix2D;

/**
 * 通用的机器学习算法接口，参考于sk-learn
 *
 * @author ttp
 */
public abstract class AbstractMLBase {
    protected DoubleMatrix2D _fit_X;
    protected DoubleMatrix2D _y;

    public AbstractMLBase fit(DoubleMatrix2D trainX, DoubleMatrix2D trainY) throws Exception {
        if (trainX.rows() != trainY.columns()) {
            throw new Exception("Warning! your (trainX.rows())" + trainX.rows() + "!= (testY.columns())" + trainY.columns());
        }
        this._fit_X = trainX;
        this._y = trainY;
        return this;
    }

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

    //    get_params();
    //    set_params();
    public abstract double[] predict(DoubleMatrix2D testX);
}
