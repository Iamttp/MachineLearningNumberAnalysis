package matrix.linalg;

import matrix.DoubleMatrix2D;
import matrix.impl.DenseDoubleMatrix2D;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/*
 * LU分解测试
 */

public class LUDecompositionTest {

    @Test
    public void decomposition() {
        DenseDoubleMatrix2D d = new DenseDoubleMatrix2D(new double[][]{
                {2, 4, -2},
                {4, 9, -3},
                {-2, -1, 7},
                {-2, 1, 7}
        });
        List<DoubleMatrix2D> ld = null;
        try {
            ld = LUDecomposition.decomposition(d);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert ld != null;
        System.out.println(ld.get(0));
        System.out.println(ld.get(1));

        // AlgebraTest
        System.out.println(Algebra.mult(d, Algebra.transpose(d)));
    }
}