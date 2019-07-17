package ML;

import ML.algorithm.DecisionTree;
import ML.beforeDate.Discretization;
import ML.beforeDate.LoadDate;
import matrix.impl.DenseDoubleMatrix2D;
import org.junit.Test;

import java.util.*;

public class DecisionTreeTest {

    @Test
    public void fit() {
    }

    @Test
    public void predict() throws Exception {
        DecisionTree decisionTree = new DecisionTree();
        LoadDate loadDate = new LoadDate(".\\Test\\ML\\testDate2.txt");
        DenseDoubleMatrix2D resAll1 = new DenseDoubleMatrix2D(loadDate.getResGet());
        DenseDoubleMatrix2D resAll2 = new DenseDoubleMatrix2D(loadDate.getFeatureGet());
        for (int i = 0; i < resAll2.columns(); i++) {
            // TODO 不是整数貌似还不行，可能是浮点数比较问题？
            resAll2.setOneCol(Discretization.equalWidth(resAll2.getOneCol(i), 4, true), i);
        }

        //////////////////////////////////////////////////////////////////// 强力去重 haha
        ArrayList<double[]> arrayList = new ArrayList<>();
        ArrayList<Double> arrayList2 = new ArrayList<>();
        for (int i = 0; i < resAll2.rows(); i++) {
            double[] one = resAll2.getOneRow(i);
            for (int j = i; j < resAll2.rows(); j++) {
                if (Arrays.hashCode(one) == Arrays.hashCode(resAll2.getOneRow(j)) && i != j) {
                    double[] temp = new double[resAll2.columns()];
                    resAll2.setOneRow(temp, j);
                    resAll1.setQuick(0, j, -99999);
                }
            }
        }

        for (int i = 0; i < resAll2.rows(); i++) {
            if (Arrays.hashCode(resAll2.getOneRow(i)) != Arrays.hashCode(new double[resAll2.columns()])) {
                arrayList.add(resAll2.getOneRow(i));
                arrayList2.add(resAll1.getQuick(0, i));
            }
        }
        double[][] x = new double[arrayList.size()][];
        double[] x2 = new double[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            x[i] = arrayList.get(i);
            x2[i] = arrayList2.get(i);
        }
        System.out.println(Arrays.deepToString(x));
        System.out.println(Arrays.toString(x2));
        resAll2 = new DenseDoubleMatrix2D(x);
        double[][] x3 = new double[1][];
        x3[0] = x2;
        resAll1 = new DenseDoubleMatrix2D(x3);
        ////////////////////////////////////////////////////////////////////

        int rows = resAll2.rows() / 5 * 4;
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
        decisionTree = (DecisionTree) decisionTree.fit(new DenseDoubleMatrix2D(res1), new DenseDoubleMatrix2D(res2));

        double res = decisionTree.score(new DenseDoubleMatrix2D(res1), new DenseDoubleMatrix2D(res2));
        System.out.println(new DenseDoubleMatrix2D(res2));
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