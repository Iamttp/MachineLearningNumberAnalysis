package matrix.linalg;

/**
 * 对DoubleMatrix2D进行一些运算
 */
public class Algebra {
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
}
