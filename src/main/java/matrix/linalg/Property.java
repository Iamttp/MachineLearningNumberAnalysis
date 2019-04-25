package matrix.linalg;


/**
 * 对DoubleMatrix2D进行一些判断，定义tol，容忍的误差
 */
public class Property {
    public static final Property DEFAULT = new Property(1.0E-9D);
    protected double tolerance;

    public Property(double var1) {
        this.tolerance = Math.abs(var1);
    }

    public double tolerance() {
        return this.tolerance;
    }
}
