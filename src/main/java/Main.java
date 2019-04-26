import core.F;
import core.F2;
import matrix.DoubleMatrix2D;
import matrix.impl.DenseDoubleMatrix2D;
import matrix.linalg.Algebra;
import matrix.linalg.LUDecomposition;
import numberAnalysis.DerivedFunction;
import numberAnalysis.SolveEquations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static numberAnalysis.Integral.simpson38;
import static numberAnalysis.Integral.TrapezoidF;
import static numberAnalysis.OrdinaryDifferential.euler;
import static numberAnalysis.OrdinaryDifferential.rungeKutta;

public class Main {

    static class myF implements F {
        @Override
        public double f(double x) {
            return x * x;
        }
    }

    // 复合梯形积分测试
    public static void test2() {
        double res = TrapezoidF(new myF(), 1, 2, 4000);
        System.out.println(res);
    }

    // 辛普森积分测试
    public static void test3() {
        double res = simpson38(new myF(), 1, 2);
        System.out.println(res);
    }

    static class myF2 implements F2 {
        // x2 表示y 即为y'(x,y)函数
        @Override
        public double f(double x, double x2) {
            return x2 - 2 * x / x2;
        }
    }

    // 龙格-库塔常微分测试
    public static void test4() {
        double[] res = rungeKutta(new myF2(), 0, 1, 10, 1);
        System.out.println(res[10 - 1]);
    }

    static class myF3 implements F {
        @Override
        public double f(double x) {
            return x * x - 2 * x;
        }
    }

    // 二分法解方程测试
    public static void test5() throws Exception {
        double res = SolveEquations.bisect(new myF3(), 0.5, 5, 0.01);
        System.out.println(res);
    }

    // 连续求导测试
    public static void test6() {
        F f1 = new DerivedFunction(new myF3());
        F f2 = new DerivedFunction(f1);
        System.out.println(f1.f(1));
        System.out.println(f2.f(1));
    }

    // 牛顿法迭代求方程测试
    public static void test7() {
        double res = SolveEquations.newton(new myF3(), 0.5, 10);
        System.out.println(res);
    }

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
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
        test8();
        // TODO 迭代
    }
}
