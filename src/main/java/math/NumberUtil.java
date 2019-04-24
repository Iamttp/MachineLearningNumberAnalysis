package math;

public class NumberUtil {
    /**
     * 计算阶乘
     * <p>
     * n! = n * (n-1) * ... * end
     * </p>
     *
     * @param start 阶乘起始
     * @param end   阶乘结束
     * @return 结果
     * @since 4.1.0
     */
    public static long factorial(long start, long end) {
        if (start < end) {
            return 0L;
        }
        if (start == end) {
            return 1L;
        }
        return start * factorial(start - 1, end);
    }

    /**
     * 计算阶乘
     * <p>
     * n! = n * (n-1) * ... * 2 * 1
     * </p>
     *
     * @param n 阶乘起始
     * @return 结果
     */
    public static long factorial(long n) {
        return factorial(n, 1);
    }
}
