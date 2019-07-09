package numberAnalysis;

import core.F;

/**
 * @author ttp
 */
public class SolveEquations {

    /**
     * 二分法求函数值，稳定，速度偏慢
     *
     * @param f      给出的方程
     * @param xleft  左端点
     * @param xright 右端点
     * @param tol    允许的误差
     * @return 返回的零点
     * @throws Exception 表示传入的左右端点不符合条件
     */
    public static double bisect(F f, double xleft, double xright, double tol) throws Exception {
        double fa = f.f(xleft);
        double fb = f.f(xright);
        if (fa * fb >= 0) {
            throw new Exception("Error! your f(xleft)*f(xright) >= 0");
        }
        while ((fb - fa) / 2 > tol) {
            double c = (xright + xleft) / 2;
            double fc = f.f(c);
            if (fc == 0)
                break;
            if (fc * fa < 0) {
                fb = fc;
                xright = c;
            } else {
                fa = fc;
                xleft = c;
            }
        }
        return (xleft + xright) / 2;
    }


    /**
     * 牛顿方法迭代求方程
     *
     * @param f     方程
     * @param x0    估计的方程解初值
     * @param leven 迭代次数
     * @return 方程解
     */
    public static double newton(F f, double x0, int leven) {
        F f1 = new DerivedFunction(f);
        for (int i = 0; i < leven; i++) {
            x0 = x0 - f.f(x0) / f1.f(x0);
        }
        return x0;
    }
}
