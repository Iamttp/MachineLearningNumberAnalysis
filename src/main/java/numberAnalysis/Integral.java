package numberAnalysis;

import core.F;

public class Integral {
    
    // 精度为3
    public static double simpson38(F f, double xleft, double xright) {
        double h = (xright - xleft) / 3;
        return 3.0 / 8.0 * h * (
                f.f(xleft) +
                        3 * f.f(xleft + h) +
                        3 * f.f(xleft + 2 * h) +
                        f.f(xleft + 3 * h)
        );
    }
}
