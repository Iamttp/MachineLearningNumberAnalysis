package ML;

import matrix.impl.DenseDoubleMatrix2D;
import org.junit.Before;
import org.junit.Test;

public class NaiveBayesTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void fit() throws Exception {
        // 哇，当e为0.5时，测试集全部通过
        NaiveBayes naiveBayes = new NaiveBayes(1.5);
        double[][] res1 = new double[1][427];
        double[][] res2 = LoadDate.load_breast_cancer("C:\\Users\\ttp\\Desktop\\JavaNumberAnalysis\\Test\\ML\\testDate2Train.txt", 30, res1);
        naiveBayes = (NaiveBayes) naiveBayes.fit(new DenseDoubleMatrix2D(res2), new DenseDoubleMatrix2D(res1));

        double[][] res3 = new double[1][470];
        double[][] res4 = LoadDate.load_breast_cancer("C:\\Users\\ttp\\Desktop\\JavaNumberAnalysis\\Test\\ML\\testDate2test.txt", 30, res3);
        double res = naiveBayes.score(new DenseDoubleMatrix2D(res4), new DenseDoubleMatrix2D(res3));
        System.out.println(res);
        System.out.println(res);
    }

    @Test
    public void predict() {
    }

    @Test
    public void score() {
    }
}