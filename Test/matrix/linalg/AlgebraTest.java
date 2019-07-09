package matrix.linalg;

import matrix.impl.DenseDoubleMatrix2D;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/*
 * 矩阵雅可比方法测试及Algebra测试
 */
public class AlgebraTest {

    @Test
    public void setProperty() {
    }

    @Test
    public void mult() {
    }

    @Test
    public void transpose() {
    }

    @Test
    public void subMatrix() {
    }

    @Test
    public void addMatrix() {
    }

    @Test
    public void jacobi() throws IOException, ClassNotFoundException {
        // TODO 没有判断为严格对角占优矩阵（充分条件）
        // ---------------------------------------------------------------雅可比方法求解方程组测试
        DenseDoubleMatrix2D d2 = new DenseDoubleMatrix2D(new double[][]{
                {3, 1},
                {1, 2}
        });
        System.out.println(Algebra.jacobi(d2, new double[]{5, 5}, 100));

        DenseDoubleMatrix2D d3 = new DenseDoubleMatrix2D(new double[][]{
                {3, 1, -1},
                {2, 4, 1},
                {-1, 2, 5}
        });
        System.out.println(Algebra.jacobi(d3, new double[]{4, 1, 1}, 100));
    }
}