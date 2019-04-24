import core.F;

import static numberAnalysis.Integral.TrapezoidF;

public class Main {

    // 需要继承F接口实现静态内部类，完成函数的创建
    static class myF implements F {
        @Override
        public double f(double x) {
            return Math.log(x);
        }
    }

    public static void test2() {
        double res = TrapezoidF(new myF(), 1, 2, 4000);
        System.out.println(res);
    }

    public static void main(String[] args) {
        test2();
    }
}
