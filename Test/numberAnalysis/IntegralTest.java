package numberAnalysis;

import numberAnalysis.Core.F;
import org.junit.Test;

/*
 * 函数求积测试
 */
public class IntegralTest {

    static class myF implements F {
        @Override
        public double f(double x) {
            return x * x;
        }
    }

    @Test
    public void simpson38() {
        System.out.println(Integral.simpson38(new myF(), 1, 2));
    }

    @Test
    public void trapezoidF() {
        System.out.println(Integral.trapezoidF(new myF(), 1, 2, 4000));
    }
}