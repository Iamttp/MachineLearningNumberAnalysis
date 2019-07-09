package numberAnalysis;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/*
 * 插值法测试
 */
public class InterpolationTest {
    @Test
    public void getnewtddValue() {
        double[] res1 = Interpolation.newtddMethod(new double[]{1, 2, 3}, new double[]{2, 4, 6}, 3);
        System.out.println(Arrays.toString(res1));
        System.out.println(Interpolation.getnewtddValue(new double[]{1, 2, 3}, res1, 3, 10));
    }
}
