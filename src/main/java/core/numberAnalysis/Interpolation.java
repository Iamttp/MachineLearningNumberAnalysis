package core.numberAnalysis;

public class Interpolation {

    /**
     * @param x 给出的点的横坐标
     * @param y 给出的点的纵坐标
     * @param n 给出的点的数目
     * @return 标识系数的数组
     */
    public static double[] newtddMethod(double[] x, double[] y, int n) {
        double[][] v = new double[n][n];
        for (int i = 0; i < n; i++) {
            v[i][0] = y[i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                v[j][i] = (v[j + 1][i - 1] - v[j][i - 1]) / (x[j + i] - x[j]);
            }
        }

        double[] res = new double[n];
        System.arraycopy(v[0], 0, res, 0, n);
        return res;
    }

    /**
     * @param x  给出的点的横坐标
     * @param c  标识系数的数组
     * @param n  给出的点的数目
     * @param px 给出的变量
     * @return 返回变量运算后的结果
     */
    public static double getValue(double[] x, double[] c, int n, int px) {
        //(int x, int y) -> x + y;
        double sum = c[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sum = sum * (px - x[i]) + c[i];
        }
        return sum;
    }
}
