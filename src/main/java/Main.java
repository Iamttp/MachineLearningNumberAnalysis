import matrix.DoubleMatrix2D;
import matrix.impl.DenseDoubleMatrix2D;
import matrix.linalg.Algebra;
import matrix.linalg.LUDecomposition;

import java.io.IOException;
import java.util.List;

public class Main {
    // 矩阵基本测试、LU分解
    public static void test8() throws IOException, ClassNotFoundException {
        DenseDoubleMatrix2D d = new DenseDoubleMatrix2D(new double[][]{
                {2, 4, -2},
                {4, 9, -3},
                {-2, -1, 7},
                {-2, 1, 7}
        });
        List<DoubleMatrix2D> ld = LUDecomposition.decomposition(d);
        System.out.println(ld.get(0));
        System.out.println(ld.get(1));
        System.out.println(Algebra.mult(d, Algebra.transpose(d)));

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

    public static void main(String[] args) throws Exception {
        // TODO 迭代
    }
}
