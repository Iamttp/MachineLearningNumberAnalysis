package ML;

import matrix.DoubleMatrix2D;

public interface MLBase {
    MLBase fit(DoubleMatrix2D trainX, DoubleMatrix2D trainY);

    //    get_params();
    //    set_params();

    DoubleMatrix2D predict(DoubleMatrix2D testX);

    double score(DoubleMatrix2D testX, DoubleMatrix2D testY);
}
