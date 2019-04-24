import java.util.Arrays;

import static core.numberAnalysis.Interpolation.newtddMethod;
import static core.numberAnalysis.Interpolation.getValue;

public class Main {

    public static void main(String[] args) {
        // --------------------------------------------------------------测试用例
        // 给出坐标(0,1)(2,2)(3,4)，求出通过该3个点的曲线，并给出x=10时y的值
        // 答案：函数为一元二次方程，1 + 0.5 * (x - 0) + 0.5 * (x - 0) * (x- 2)
        //       x = 10     y = 46
        double[] x = new double[]{0, 2, 3};
        double[] y = new double[]{1, 2, 4};
        double[] c = newtddMethod(x, y, 3);
        System.out.println(Arrays.toString(c));
        System.out.println(getValue(x, c, 3, 10));
    }
}
