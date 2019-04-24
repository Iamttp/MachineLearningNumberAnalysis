import core.F;

import static numberAnalysis.Integral.simpson38;

public class Main {

    // 需要继承F接口实现静态内部类，完成函数的创建
    static class myF implements F {
        @Override
        public double f(double x) {
            return x * x;
        }
    }

    public static void main(String[] args) {
        double res = simpson38(new myF(), 0, 10);
        System.out.println(res);
    }
}
