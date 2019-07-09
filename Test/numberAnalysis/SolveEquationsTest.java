package numberAnalysis;

import core.F;
import org.junit.Test;

/*
 * 求方程解测试
 */
public class SolveEquationsTest {
    static class myF implements F {
        @Override
        public double f(double x) {
            return x * x - 2 * x;
        }
    }

    @Test
    public void bisect() {
        double res = 0;
        try {
            res = SolveEquations.bisect(new myF(), 0.5, 5, 0.01);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);
    }

    @Test
    public void newton() {
        double res = SolveEquations.newton(new myF(), 0.5, 10);
        System.out.println(res);
    }
}