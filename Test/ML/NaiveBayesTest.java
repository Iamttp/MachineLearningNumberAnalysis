package ML;

import ML.algorithm.NaiveBayes;
import ML.beforeDate.LoadDate;
import matrix.impl.DenseDoubleMatrix2D;
import org.junit.Test;

public class NaiveBayesTest {
    @Test
    public void fit() throws Exception {
        // 哇，当e为0.5时，测试集全部通过
        NaiveBayes naiveBayes = new NaiveBayes(0.5);
        LoadDate loadDate = new LoadDate(".\\Test\\ML\\testDateTrain.txt");
        double[][] res1 = loadDate.getResGet();
        double[][] res2 = loadDate.getFeatureGet();
        naiveBayes = (NaiveBayes) naiveBayes.fit(new DenseDoubleMatrix2D(res2), new DenseDoubleMatrix2D(res1));

        LoadDate loadDate2 = new LoadDate(".\\Test\\ML\\testDateTest.txt");
        double[][] res3 = loadDate2.getResGet();
        double[][] res4 = loadDate2.getFeatureGet();
        double res = naiveBayes.score(new DenseDoubleMatrix2D(res4), new DenseDoubleMatrix2D(res3));
        System.out.println("testDateTest\t" + res);
    }

    @Test
    public void predict() throws Exception {
        // 哇，当e为0.5时，测试集全部通过
        NaiveBayes naiveBayes = new NaiveBayes(1.5);
        LoadDate loadDate = new LoadDate(".\\Test\\ML\\testDate2Train.txt");
        double[][] res1 = loadDate.getResGet();
        double[][] res2 = loadDate.getFeatureGet();
        naiveBayes = (NaiveBayes) naiveBayes.fit(new DenseDoubleMatrix2D(res2), new DenseDoubleMatrix2D(res1));

        LoadDate loadDate2 = new LoadDate(".\\Test\\ML\\testDate2Test.txt");
        double[][] res3 = loadDate2.getResGet();
        double[][] res4 = loadDate2.getFeatureGet();
        double res = naiveBayes.score(new DenseDoubleMatrix2D(res4), new DenseDoubleMatrix2D(res3));
        System.out.println("testDate2Test\t" + res);
    }

    @Test
    public void score() {
    }
}