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
                {1, 4},
                {2, 4},
                {2, 4},
                {1, 4},
                {3, 4},
                {1, 5},
                {2, 5},
                {2, 5},
                {2, 5},
                {1, 6},
                {3, 6},
                {3, 4},
                {1, 5},
                {3, 5},
                {2, 5},
                {3, 4},
                {1, 4},
        }), new DenseDoubleMatrix2D(new double[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        }));
        decisionTree.predict(new DenseDoubleMatrix2D(new double[][]{
                {2, 6},
        }));
    }

    @Test
    public void score() {
    }
}