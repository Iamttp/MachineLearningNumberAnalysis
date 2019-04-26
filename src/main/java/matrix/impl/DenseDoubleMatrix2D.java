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
}
