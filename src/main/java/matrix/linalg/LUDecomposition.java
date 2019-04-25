package matrix.linalg;

import matrix.DoubleMatrix2D;
import matrix.impl.DenseDoubleMatrix2D;

import java.util.Arrays;
import java.util.List;

public class LUDecomposition {
    /**
     * LU 分解 TODO 没有根据连续数组进行优化
     *
     * @param a 传入矩阵
     * @return 返回L、U矩阵
     */
    public static List<DoubleMatrix2D> decomposition(DoubleMatrix2D a) {
        DoubleMatrix2D U = a;  //a是要分解的矩阵
        DoubleMatrix2D L = new DenseDoubleMatrix2D(a.rows(), a.columns());

        for (int j = 0; j < a.columns(); j++) {
            if (a.getQuick(0, 0) == 0) {
                throw new IllegalArgumentException("zero pivot encountered.");
            }

            for (int i = j + 1; i < a.rows(); i++) {
                double mult = a.getQuick(i, j) / a.getQuick(j, j);
                for (int k = j; k < a.columns(); k++) {
                    U.setQuick(i, k, a.getQuick(i, k) - a.getQuick(j, k) * mult);
                    //得出上三角矩阵U,通过减去矩阵的第一行,第二行,第一行(第二行)得到上三角矩阵
                }
                L.setQuick(i, j, mult);  //得到下三角矩阵是得出上三角矩阵的乘积因子
            }
        }
        return Arrays.asList(L, U);
    }
}
