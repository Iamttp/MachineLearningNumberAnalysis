import core.F;

import static numberAnalysis.Integral.simpson38;
import static numberAnalysis.Integral.TrapezoidF;
import static numberAnalysis.OrdinaryDifferential.euler;

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

    public static void test4() {
        double[] res = euler(new myF(), 0, 1, 10000, 1);
        System.out.println(res[10000 - 1]);
    }

    public static void main(String[] args) {
        test2();
        test3();
        test4();
    }
}
