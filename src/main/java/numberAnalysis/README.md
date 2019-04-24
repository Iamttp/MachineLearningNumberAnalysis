插值法又称“内插法”，是利用函数f (x)在某区间中已知的若干点的函数值，作出适当的特定函数，在区间的其他点上用这特定函数的值作为函数f (x)的近似值，这种方法称为插值法。如果这特定函数是多项式，就称它为插值多项式。

牛顿差商插值方法

```java 
public static void test1() {
    // --------------------------------------------------------------测试用例
    // 给出坐标(0,1)(2,2)(3,4)，求出通过该3个点的曲线，并给出x=10时y的值
    // 答案：函数为一元二次方程，1 + 0.5 * (x - 0) + 0.5 * (x - 0) * (x- 2)
    //       x = 10     y = 46
    double[] x = new double[]{0, 2, 3};
    double[] y = new double[]{1, 2, 4};
    double[] c = newtddMethod(x, y, 3);
    System.out.println(Arrays.toString(c));
    System.out.println(getnewtddValue(x, c, 3, 10));
}
```

辛普森（Simpson）公式是牛顿-科特斯公式当n=2时的情形，也称为三点公式。利用区间二等分的三个点来进行积分插值。其科特斯系数分别为1/6，4/6，1/6。

3/8公式使用4个点更加精确。
复合梯形公式

```java
// 需要继承F接口实现静态内部类，完成函数的创建
static class myF implements F {
    @Override
    public double f(double x) {
        return Math.log(x);
    }
}

public static void test2() {
    double res = TrapezoidF(new myF(), 1, 2, 4000);
    System.out.println(res);
}
```

常微分方程数值解法计算数学的一个分支。是解常微分方程各类定解问题的数值方法。现有的解析方法只能用于求解一些特殊类型的定解问题，实用上许多很有价值的常微分方程的解不能用初等函数来表示，常常需要求其数值解。所谓数值解，是指在求解区间内一系列离散点处给出真解的近似值。

欧拉方法求微分方程

```java
static class myF implements F {
    @Override
    public double f(double x) {
        return x * x;
    }
}

public static void test4() {
    double[] res = euler(new myF(), 0, 1, 10000, 1);
    System.out.println(res[10000 - 1]);
}
```