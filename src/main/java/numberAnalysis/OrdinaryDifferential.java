package numberAnalysis;

import core.F2;

/**
 * 常微分方程数值解法计算数学的一个分支。是解常微分方程各类定解问题的数值方法。现有的解析方法只能用于求解一些特殊类型的定解问题，实用上许多很有价值的常微分方程的解不能用初等函数来表示，常常需要求其数值解。所谓数值解，是指在求解区间内一系列离散点处给出真解的近似值。
 * <p>
 * !!!区间左闭右开
 * !!!rungeKutta方法绝大多数情况下更优
 */
public class OrdinaryDifferential {

    /**
     * @param differentialf 微分方程 x2 表示y 即为y'(x,y)函数
     * @param xleft         左端点
     * @param xright        右端点
     * @param leven         步数
     * @param initY         初始y的值
     * @return 对应的leven大小的y值数组
     */
    public static double[] euler(F2 differentialf, double xleft, double xright, int leven, double initY) {
        double h = (xright - xleft) / leven;
        double[] y = new double[leven];
        double nowX = xleft;
        y[0] = initY;
        for (int i = 1; i < leven; i++) {
            y[i] = y[i - 1] + h * (differentialf.f(nowX, y[i - 1]));
            nowX += h;
        }
        return y;
    }

    /**
     * @param differentialf 微分方程 x2 表示y 即为y'(x,y)函数
     * @param xleft         左端点
     * @param xright        右端点
     * @param leven         步数
     * @param initY         初始y的值
     * @return 对应的leven大小的y值数组
     */
    public static double[] rungeKutta(F2 differentialf, double xleft, double xright, int leven, double initY) {
        double h = (xright - xleft) / leven;
        double[] y = new double[leven];
        double nowX = xleft;
        y[0] = initY;
        for (int i = 1; i < leven; i++) {
            double K1 = differentialf.f(nowX, y[i - 1]);
            double K2 = differentialf.f(nowX + h / 2, y[i - 1] + h / 2 * K1);
            double K3 = differentialf.f(nowX + h / 2, y[i - 1] + h / 2 * K2);
            double K4 = differentialf.f(nowX + h, y[i - 1] + h * K3);
            y[i] = y[i - 1] + h / 6 * (K1 + 2 * K2 + 2 * K3 + K4);
            nowX += h;
        }
        return y;
    }
}
