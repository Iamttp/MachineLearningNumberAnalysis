package numberAnalysis;

import numberAnalysis.Core.F2;
import org.junit.Test;

/*
 * 常微分方程数值解法测试
 */
public class OrdinaryDifferentialTest {

    static class myF2 implements F2 {
        // x2 表示y 即为y'(x,y)函数
        @Override
        public double f(double x, double x2) {
            return x2 - 2 * x / x2;
        }
    }

    @Test
    public void euler() {
        double[] res = OrdinaryDifferential.euler(new myF2(), 0, 1, 10, 1);
        System.out.println(res[10 - 1]);
    }

    @Test
    public void rungeKutta() {
        double[] res = OrdinaryDifferential.rungeKutta(new myF2(), 0, 1, 10, 1);
        System.out.println(res[10 - 1]);
    }
}