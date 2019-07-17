package ML;

import ML.algorithm.DecisionTree;
import ML.beforeDate.Discretization;
import ML.beforeDate.LoadDate;
import matrix.impl.DenseDoubleMatrix2D;
import org.junit.Test;

public class DecisionTreeTest {

    @Test
    public void fit() {
    }

    @Test
    public void predict() throws Exception {
        DecisionTree decisionTree = new DecisionTree();
        LoadDate loadDate = new LoadDate(".\\Test\\ML\\testDate.txt");
        DenseDoubleMatrix2D resAll1 = new DenseDoubleMatrix2D(loadDate.getResGet());
        DenseDoubleMatrix2D resAll2 = new DenseDoubleMatrix2D(loadDate.getFeatureGet());
        for (int i = 0; i < resAll2.columns(); i++) {
            resAll2.setOneCol(Discretization.equalWidth(resAll2.getOneCol(i), 5, false), i);
        }

        int rows = resAll2.rows() / 3 * 2;
        double[][] res1 = new double[rows][];
        double[][] res2 = new double[1][rows];
        double[][] res3 = new double[resAll2.rows() - rows][];
        double[][] res4 = new double[1][resAll2.rows() - rows];

        for (int i = 0; i < rows; i++) {
            res1[i] = resAll2.getOneRow(i);
            res2[0][i] = resAll1.getQuick(0, i);
        }
        for (int i = rows; i < resAll2.rows(); i++) {
            res3[i - rows] = resAll2.getOneRow(i);
            res4[0][i - rows] = resAll2.getQuick(0, i);
        }
        System.out.println(new DenseDoubleMatrix2D(res1));
        decisionTree = (DecisionTree) decisionTree.fit(new DenseDoubleMatrix2D(res1), new DenseDoubleMatrix2D(res2));

        double res = decisionTree.score(new DenseDoubleMatrix2D(res1), new DenseDoubleMatrix2D(res2));
        System.out.println("testDateTest\t" + res);
    }

    @Test
    public void score() throws Exception {
//        DecisionTree decisionTree = new DecisionTree();
//        DenseDoubleMatrix2D trainx = new DenseDoubleMatrix2D(new double[][]{
//                {1, 4, 7, 10, 13, 16},
//                {2, 4, 8, 10, 13, 16},
//                {2, 4, 7, 10, 13, 16},
//                {1, 4, 8, 10, 13, 16},
//                {3, 4, 7, 10, 13, 16},
//                {1, 5, 7, 10, 14, 17},
//                {2, 5, 7, 11, 14, 17},
//                {2, 5, 7, 10, 14, 16},
//                {2, 5, 8, 11, 14, 16},
//                {1, 6, 9, 10, 15, 17},
//                {3, 6, 9, 12, 15, 16},
//                {3, 4, 7, 12, 15, 17},
//                {1, 5, 7, 11, 13, 16},
//                {3, 5, 8, 11, 13, 16},
//                {2, 5, 7, 10, 14, 17},
//                {3, 4, 7, 12, 15, 16},
//                {1, 4, 8, 11, 14, 16},
//        });
//        DenseDoubleMatrix2D trainy = new DenseDoubleMatrix2D(new double[][]{
//                {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}
//        });
//
//        decisionTree.fit(trainx, trainy);
//        double res = decisionTree.score(trainx, trainy);
//        System.out.println(res);
    }
}