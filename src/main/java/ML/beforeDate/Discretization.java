package ML.beforeDate;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class Discretization {
    /**
     * 对数据进行等宽离散化
     */
    public static double[] equalWidth(double[] inData, int n, boolean isInt) {
        Arrays.sort(inData);
        double minData = inData[0];
        double maxData = inData[inData.length - 1];
        double space = (maxData - minData) / n;
        for (int j = 0; j < inData.length; j++) {
            int x = (int) ((inData[j] - minData) / space);
            if (isInt) {
                inData[j] = (int) minData + x * (int) space;
            } else {
                inData[j] = minData + x * space;
            }
        }
        return inData;
    }
}
