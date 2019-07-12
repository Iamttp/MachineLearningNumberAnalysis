package ML;

import matrix.impl.DenseDoubleMatrix2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecisionTreeTest {

    @Test
    public void fit() {
    }

    @Test
    public void predict() throws Exception {
        DecisionTree decisionTree = new DecisionTree();
        decisionTree.fit(new DenseDoubleMatrix2D(new double[][]{
                {1, 4, 7, 10, 13, 16},
                {2, 4, 8, 10, 13, 16},
                {2, 4, 7, 10, 13, 16},
                {1, 4, 8, 10, 13, 16},
                {3, 4, 7, 10, 13, 16},
                {1, 5, 7, 10, 14, 17},
                {2, 5, 7, 11, 14, 17},
                {2, 5, 7, 10, 14, 16},
                {2, 5, 8, 11, 14, 16},
                {1, 6, 9, 10, 15, 17},
                {3, 6, 9, 12, 15, 16},
                {3, 4, 7, 12, 15, 17},
                {1, 5, 7, 11, 13, 16},
                {3, 5, 8, 11, 13, 16},
                {2, 5, 7, 10, 14, 17},
                {3, 4, 7, 12, 15, 16},
                {1, 4, 8, 11, 14, 16},
        }), new DenseDoubleMatrix2D(new double[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        }));
        decisionTree.predict(new DenseDoubleMatrix2D(new double[][]{
                {2, 6, 7, 10, 14, 16},
        }));
    }

    @Test
    public void score() {
    }
}