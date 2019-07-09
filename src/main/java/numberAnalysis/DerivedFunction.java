package numberAnalysis;

import core.F;

/**
 * 函数求导，可重复求导
 * 参考：`https://www.cnblogs.com/thinkam/p/7933279.html#head-6
 *
 * @author ttp
 */
public class DerivedFunction implements F {
    private static final double DELTA_X = 0.000001;
    private F function;

    public DerivedFunction(F function) {
        this.function = function;
    }

    @Override
    public double f(double x) {
        return (function.f(x + DELTA_X) - function.f(x)) / DELTA_X;
    }
}