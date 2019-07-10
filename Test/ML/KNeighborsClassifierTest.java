package ML;

import matrix.impl.DenseDoubleMatrix2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class KNeighborsClassifierTest {
    @Test
    public void fit() {
    }

    @Test
    public void predict() {
    }

    @Test
    public void score() throws Exception {
        KNeighborsClassifier kNeighborsClassifier = new KNeighborsClassifier(9);
        double[][] res1 = new double[1][427];
        double[][] res2 = LoadDate.load_breast_cancer("C:\\Users\\ttp\\Desktop\\JavaNumberAnalysis\\Test\\ML\\testDate2Train.txt", 30, res1);
        kNeighborsClassifier = (KNeighborsClassifier) kNeighborsClassifier.fit(new DenseDoubleMatrix2D(res2), new DenseDoubleMatrix2D(res1));

        double[][] res3 = new double[1][470];
        double[][] res4 = LoadDate.load_breast_cancer("C:\\Users\\ttp\\Desktop\\JavaNumberAnalysis\\Test\\ML\\testDate2test.txt", 30, res3);
        double res = kNeighborsClassifier.score(new DenseDoubleMatrix2D(res4), new DenseDoubleMatrix2D(res3));
        System.out.println(res);
    }
}