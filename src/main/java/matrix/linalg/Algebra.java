package matrix.linalg;

import matrix.DoubleMatrix2D;
import matrix.impl.DenseDoubleMatrix2D;
import matrix.impl.PersistentObject;

/**
 * 对DoubleMatrix2D进行一些运算
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
        if (a.columns() != b.rows())
            return null;
        //c矩阵的行数y，与列数x
        int y = a.rows();
        int x = b.columns();
        DoubleMatrix2D c = new DenseDoubleMatrix2D(y, x);
        for (int i = 0; i < y; i++)
            for (int j = 0; j < x; j++) {   //c矩阵的第i行第j列所对应的数值，等于a矩阵的第i行分别乘以b矩阵的第j列之和
                double res = 0;
                for (int k = 0; k < b.rows(); k++)
                    res += a.getQuick(i, k) * b.getQuick(k, j);
                c.setQuick(i, j, res);
            }
        return c;
    }
}
