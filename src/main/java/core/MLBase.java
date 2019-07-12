package core;

import matrix.DoubleMatrix2D;

/**
 * 通用的机器学习算法接口，参考于sk-learn
 */
public interface MLBase {
    MLBase fit(DoubleMatrix2D trainX, DoubleMatrix2D trainY) throws Exception;

    //    get_params();
    //    set_params();

    double[] predict(DoubleMatrix2D testX);

    double score(DoubleMatrix2D testX, DoubleMatrix2D testY) throws Exception;
}
