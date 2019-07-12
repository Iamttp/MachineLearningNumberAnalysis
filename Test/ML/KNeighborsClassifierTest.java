package ML;

import core.LoadDate;
import matrix.impl.DenseDoubleMatrix2D;
import org.junit.Test;

public class KNeighborsClassifierTest {
    @Test
    public void fit() {
    }

    @Test
    public void predict() throws Exception {
        KNeighborsClassifier kNeighborsClassifier = new KNeighborsClassifier(5);
        LoadDate loadDate = new LoadDate(".\\Test\\ML\\testDateTrain.txt");
        double[][] res1 = loadDate.getResGet();
        double[][] res2 = loadDate.getFeatureGet();
        kNeighborsClassifier = (KNeighborsClassifier) kNeighborsClassifier.fit(new DenseDoubleMatrix2D(res2), new DenseDoubleMatrix2D(res1));

        LoadDate loadDate2 = new LoadDate(".\\Test\\ML\\testDateTest.txt");
        double[][] res3 = loadDate2.getResGet();
        double[][] res4 = loadDate2.getFeatureGet();
        double res = kNeighborsClassifier.score(new DenseDoubleMatrix2D(res4), new DenseDoubleMatrix2D(res3));
        System.out.println("testDateTest\t" + res);
    }

    @Test
    public void score() throws Exception {
        KNeighborsClassifier kNeighborsClassifier = new KNeighborsClassifier(9);
        LoadDate loadDate = new LoadDate(".\\Test\\ML\\testDate2Train.txt");
        double[][] res1 = loadDate.getResGet();
        double[][] res2 = loadDate.getFeatureGet();
        kNeighborsClassifier = (KNeighborsClassifier) kNeighborsClassifier.fit(new DenseDoubleMatrix2D(res2), new DenseDoubleMatrix2D(res1));

        LoadDate loadDate2 = new LoadDate(".\\Test\\ML\\testDate2Test.txt");
        double[][] res3 = loadDate2.getResGet();
        double[][] res4 = loadDate2.getFeatureGet();
        double res = kNeighborsClassifier.score(new DenseDoubleMatrix2D(res4), new DenseDoubleMatrix2D(res3));
        System.out.println("testDate2Test\t" + res);
    }
}