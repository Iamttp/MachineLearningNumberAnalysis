import core.F;
import core.F2;

import java.util.Arrays;

import static numberAnalysis.Integral.simpson38;
import static numberAnalysis.Integral.TrapezoidF;
import static numberAnalysis.OrdinaryDifferential.euler;
import static numberAnalysis.OrdinaryDifferential.rungeKutta;

public class Main {

    // 需要继承F接口实现静态内部类，完成函数的创建
    static class myF implements F {
        @Override
        public double f(double x) {
            return x * x;
        }
    }

    public static void test2() {
        double res = TrapezoidF(new myF(), 1, 2, 40);
        System.out.println(res);
    }

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

    public static void test4() {
        double[] res = rungeKutta(new myF2(), 0, 1, 10, 1);
        System.out.println(Arrays.toString(res));
    }

    public static void main(String[] args) {
        test2();
        test3();
        test4();
    }
}
