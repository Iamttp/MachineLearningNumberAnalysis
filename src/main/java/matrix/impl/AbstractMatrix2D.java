package matrix.impl;

/**
 * 定义了基本的行数和列数，以及简单的转string和设置、核对大小
 */
public abstract class AbstractMatrix2D extends PersistentObject {
    protected int columns;
    protected int rows;

    public int columns() {
        return columns;
    }

    public int rows() {
        return rows;
    }

    public void checkShape(AbstractMatrix2D var1) {
        if (this.columns != var1.columns || this.rows != var1.rows) {
            throw new IllegalArgumentException("Incompatible dimensions: " + this.toStringShort() + " and " + var1.toStringShort());
        }
    }

    public String toStringShort() {
        return this.rows + " x " + this.columns + " matrix";
    }

    /**
     * @param var1 设置行数
     * @param var2 设置列数
     */
    protected void setUp(int var1, int var2) {
        if (var1 >= 0 && var2 >= 0) {
            this.rows = var1;
            this.columns = var2;
            if ((double) var2 * (double) var1 > 2.147483647E9D) {
                throw new IllegalArgumentException("matrix too large");
            }
        } else {
            throw new IllegalArgumentException("negative size");
        }
    }
}
