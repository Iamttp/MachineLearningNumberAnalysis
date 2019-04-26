package matrix.linalg;

import matrix.DoubleMatrix2D;
import matrix.impl.DenseDoubleMatrix2D;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LUDecomposition {
    /**
     * LU 分解 TODO 没有根据连续数组进行优化
     *
     * @param a 传入矩阵
     * @return 返回L、U矩阵
     */
    public static List<DoubleMatrix2D> decomposition(DoubleMatrix2D a) throws IOException, ClassNotFoundException {
        DoubleMatrix2D U = (DoubleMatrix2D) a.deepClone();  //a是要分解的矩阵
        DoubleMatrix2D L = new DenseDoubleMatrix2D(U.rows(), U.columns());

        for (int j = 0; j < U.columns(); j++) {
            if (U.getQuick(0, 0) == 0) {
                throw new IllegalArgumentException("zero pivot encountered.");
            }

            for (int i = j + 1; i < U.rows(); i++) {
                double mult = U.getQuick(i, j) / U.getQuick(j, j);
                for (int k = j; k < U.columns(); k++) {
                    U.setQuick(i, k, U.getQuick(i, k) - U.getQuick(j, k) * mult);
                    //得出上三角矩阵U,通过减去矩阵的第一行,第二行,第一行(第二行)得到上三角矩阵
                }
                L.setQuick(i, j, mult);  //得到下三角矩阵是得出上三角矩阵的乘积因子
            }
        }
        return Arrays.asList(L, U);
    }
}
