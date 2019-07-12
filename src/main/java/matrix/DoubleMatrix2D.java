package matrix;

import matrix.impl.AbstractMatrix2D;

import java.util.Arrays;

/**
 * 定义抽象类，完成赋值，设置值等功能，但值存储方式未定
 */
public abstract class DoubleMatrix2D extends AbstractMatrix2D {

    public DoubleMatrix2D assign(double[][] var1) {
        if (var1.length != this.rows) {
            throw new IllegalArgumentException("Must have same number of rows: rows=" + var1.length + "rows()=" + this.rows);
        } else {
            int var2 = this.rows;

            while (true) {
                --var2;
                if (var2 < 0) {
                    return this;
                }

                double[] var3 = var1[var2];
                if (var3.length != this.columns) {
                    throw new IllegalArgumentException("Must have same number of columns in every row: columns=" + var3.length + "columns()=" + this.columns);
                }

                int var4 = this.columns;

                while (true) {
                    --var4;
                    if (var4 < 0) {
                        break;
                    }

                    this.setQuick(var2, var4, var3[var4]);
                }
            }
        }
    }

    // n x 1 矩阵
    public DoubleMatrix2D assign(double[] var1) {
        if (var1.length != this.rows) {
            throw new IllegalArgumentException("Must have same number of rows: rows=" + var1.length + "rows()=" + this.rows);
        }
        int var3 = this.rows;
        while (true) {
            --var3;
            if (var3 < 0) {
                break;
            }

            this.setQuick(var3, 0, var1[var3]);
        }
        return this;
    }

    /**
     * @param var1 设置值多少行
     * @param var2 设置值多少列
     * @param var3 设置的值
     */
    public abstract void setQuick(int var1, int var2, double var3);

    public abstract double getQuick(int var1, int var2);

    public static String shape(AbstractMatrix2D var0) {
        return var0.rows() + " x " + var0.columns() + " matrix";
    }

    @Override
    public String toString() {
        String[][] var2 = new String[rows][columns];
        for (int i = 0; i < var2.length; i++) {
            for (int j = 0; j < var2[0].length; j++) {
                var2[i][j] = getQuick(i, j) + " ";
            }
        }

        StringBuilder var3 = new StringBuilder(Arrays.deepToString(var2));
        var3.insert(0, "\t" + shape(this) + "\n");
        for (int i = 0; i < var3.length(); i++) {
            if (',' == var3.charAt(i)) {
                if (var3.charAt(i - 1) == ']') {
                    var3.setCharAt(i, '\n');
                }
            }
        }
        return var3.toString();
    }

    public abstract double[] getOneCol(int row);

    public abstract double[] getOneRow(int col);
}
