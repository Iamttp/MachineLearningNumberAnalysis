package matrix.impl;

import matrix.DoubleMatrix2D;

/**
 * 稠密矩阵
 */
public class DenseDoubleMatrix2D extends DoubleMatrix2D {
    protected double[] elements;

    public DenseDoubleMatrix2D(double[][] var1) {
        this(var1.length, var1.length == 0 ? 0 : var1[0].length);
        this.assign(var1);
    }

    // 构建向量 n x 1
    public DenseDoubleMatrix2D(double[] var1) {
        this(var1.length, 1);
        this.assign(var1);
    }

    public DenseDoubleMatrix2D(int var1, int var2) {
        this.setUp(var1, var2);
        this.elements = new double[var1 * var2];
    }

    @Override
    public void setQuick(int var1, int var2, double var3) {
        this.elements[var1 * this.columns + var2] = var3;
    }

    @Override
    public double getQuick(int var1, int var2) {
        return this.elements[var1 * this.columns + var2];
    }

    protected int index(int var1, int var2) {
        return var1 * this.columns + var2;
    }

    @Override
    public double[] getOneCol(int col) {
        double[] res = new double[this.rows];
        for (int i = 0; i < this.rows; i++) {
            res[i] = this.getQuick(i, col);
        }
        return res;
    }

    @Override
    public double[] getOneRow(int row) {
        double[] res = new double[this.columns];
        for (int i = 0; i < this.columns; i++) {
            res[i] = this.getQuick(row, i);
        }
        return res;
    }

    @Override
    public void setOneCol(double[] oneCol, int col) {
        if (oneCol.length != this.rows) {
            throw new IllegalArgumentException("Must have same number of : oneCol.length=" + oneCol.length + "this.rows=" + this.rows);
        }
        for (int i = 0; i < this.rows; i++) {
            this.setQuick(i, col, oneCol[i]);
        }
    }

    @Override
    public void setOneRow(double[] oneRow, int row) {
        if (oneRow.length != this.columns) {
            throw new IllegalArgumentException("Must have same number of : oneRow.length=" + oneRow.length + "this.columns=" + this.columns);
        }
        for (int i = 0; i < this.rows; i++) {
            this.setQuick(row, i, oneRow[i]);
        }
    }
}
