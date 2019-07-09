package ML;

import matrix.impl.DenseDoubleMatrix2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class KNeighborsClassifierTest {
    KNeighborsClassifier kNeighborsClassifier = new KNeighborsClassifier(2);

    @Test
    public void fit() {
        kNeighborsClassifier.fit(new DenseDoubleMatrix2D(new double[][]{
                {4.8, 3.0, 1.4, 0.3},
                {5.1, 3.8, 1.6, 0.2},
                {4.6, 3.2, 1.4, 0.2},
                {5.3, 3.7, 1.5, 0.2},
                {5.0, 3.3, 1.4, 0.2},
                {7.0, 3.2, 4.7, 1.4},
                {6.4, 3.2, 4.5, 1.5},
                {6.9, 3.1, 4.9, 1.5},
                {5.5, 2.3, 4.0, 1.3},
                {6.5, 2.8, 4.6, 1.5}
        }), new DenseDoubleMatrix2D(new double[][]{
                {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}
        }));
    }

    @Test
    public void predict() {
        kNeighborsClassifier.predict(new DenseDoubleMatrix2D(new double[][]{{5, 2.9, 1, 0.2}}));
    }

    @Test
    public void score() {
    }
}