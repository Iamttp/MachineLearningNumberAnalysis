package matrix.linalg;

import matrix.DoubleMatrix2D;
import matrix.impl.DenseDoubleMatrix2D;
import matrix.impl.PersistentObject;

import java.io.IOException;

/**
 * 对DoubleMatrix2D进行一些运算
 * TODO Non-transient non-serializable instance field in serializable class
 * @author ttp
 */
public class Algebra extends PersistentObject {
    public static final Algebra DEFAULT = new Algebra();
    protected Property property;

    public Algebra() {
        this(Property.DEFAULT.tolerance());
    }

    public Algebra(double var1) {
        this.setProperty(new Property(var1));
    }

    public void setProperty(Property var1) {
        if (this == DEFAULT && var1 != this.property) {
            throw new IllegalArgumentException("Attempted to modify immutable object.");
        } else {
            this.property = var1;
        }
    }

    public static DoubleMatrix2D mult(DoubleMatrix2D a, DoubleMatrix2D b) {
        //当a的列数与矩阵b的行数不相等时，不能进行点乘，返回null
        if (a.columns() != b.rows()) {
            throw new IllegalArgumentException("Incompatible dimensions: " + a.toStringShort() + " and " + b.toStringShort());
        }
        //c矩阵的行数y，与列数x
        int y = a.rows();
        int x = b.columns();
        DoubleMatrix2D c = new DenseDoubleMatrix2D(y, x);
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {   //c矩阵的第i行第j列所对应的数值，等于a矩阵的第i行分别乘以b矩阵的第j列之和
                double res = 0;
                for (int k = 0; k < b.rows(); k++) {
                    res += a.getQuick(i, k) * b.getQuick(k, j);
                }
                c.setQuick(i, j, res);
            }
        }
        return c;
    }

    public static DoubleMatrix2D transpose(DoubleMatrix2D a) {
        DoubleMatrix2D MatrixC = new DenseDoubleMatrix2D(a.columns(), a.rows());
        for (int i = 0; i < a.rows(); i++) {
            for (int j = 0; j < a.columns(); j++) {
                MatrixC.setQuick(j, i, a.getQuick(i, j));
            }
        }
        return MatrixC;
    }

    public static DoubleMatrix2D subMatrix(DoubleMatrix2D a, DoubleMatrix2D b) {
        if (a.columns() != b.columns() || a.rows() != b.rows()) {
            throw new IllegalArgumentException("Incompatible dimensions: " + a.toStringShort() + " and " + b.toStringShort());
        }
        DoubleMatrix2D MatrixC = new DenseDoubleMatrix2D(a.rows(), a.columns());
        for (int i = 0; i < a.rows(); i++) {
            for (int j = 0; j < a.columns(); j++) {
                MatrixC.setQuick(j, i, a.getQuick(i, j) - b.getQuick(i, j));
            }
        }
        return MatrixC;
    }

    public static DoubleMatrix2D addMatrix(DoubleMatrix2D a, DoubleMatrix2D b) {
        if (a.columns() != b.columns() || a.rows() != b.rows()) {
            throw new IllegalArgumentException("Incompatible dimensions: " + a.toStringShort() + " and " + b.toStringShort());
        }
        DoubleMatrix2D MatrixC = new DenseDoubleMatrix2D(a.rows(), a.columns());
        for (int i = 0; i < a.rows(); i++) {
            for (int j = 0; j < a.columns(); j++) {
                MatrixC.setQuick(j, i, a.getQuick(i, j) + b.getQuick(i, j));
            }
        }
        return MatrixC;
    }

    /**
     * @param a     系数矩阵
     * @param b     b
     * @param leven 迭代次数
     * @return 返回结果 n x 1
     */
    public static DoubleMatrix2D jacobi(DoubleMatrix2D a, double[] b, int leven) throws IOException, ClassNotFoundException {
        return jacobi(a, b, leven, new double[b.length]);
    }

    public static DoubleMatrix2D jacobi(DoubleMatrix2D a, double[] b, int leven, double[] x0) throws IOException, ClassNotFoundException {
        int size = a.rows();
        if (size != a.columns()) {
            throw new IllegalArgumentException("Incompatible dimensions: " + a.toStringShort());
        }
        DoubleMatrix2D DF1 = new DenseDoubleMatrix2D(a.rows(), a.columns());
        DoubleMatrix2D LAU = (DoubleMatrix2D) a.deepClone();
        for (int i = 0; i < size; i++) {
            DF1.setQuick(i, i, 1 / a.getQuick(i, i));
            LAU.setQuick(i, i, 0);
        }
        DoubleMatrix2D x = new DenseDoubleMatrix2D(x0);
        DoubleMatrix2D xa = Algebra.mult(DF1, LAU);
        DoubleMatrix2D xb = Algebra.mult(DF1, new DenseDoubleMatrix2D(b));
        for (int i = 0; i < leven; i++) {
            x = Algebra.subMatrix(xb, Algebra.mult(xa, x));
        }
        return x;
    }
}
