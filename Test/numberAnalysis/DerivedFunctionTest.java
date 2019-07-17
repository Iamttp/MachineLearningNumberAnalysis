package numberAnalysis;

import numberAnalysis.Core.F;
import org.junit.Test;

/**
 * 函数求导测试
 * 参考：`https://www.cnblogs.com/thinkam/p/7933279.html#head-6
 */
public class DerivedFunctionTest {
    static class myF implements F {
        @Override
        public double f(double x) {
            return x * x - 2 * x;
        }
    }

    @Test
    public void f() {
        F f1 = new DerivedFunction(new myF());
        F f2 = new DerivedFunction(f1);
        System.out.println(f1.f(1));
        System.out.println(f2.f(1));
    }
}