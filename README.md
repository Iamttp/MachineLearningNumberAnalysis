# Java 数值分析基本工具类


## 参考网上规范

首先说明一下，这个工具类的规范不是我定义的，而是参考众多知名开源框架发现的。

先说第一种情况，如果工具类中的方法全部时静态方法，那么可以将工具类作为一个 abstract 抽象类。Spring 框架中存在大量的这样的使用。

然后，如果工具类中有异常，请抛出，不要自己去 try-catch。更不要 try 了之后 e.printStackTrace() 。

还有就是工具类中要不要打印日志问题，尽量不要打印，像 log4j 这样的一些第三方日志框架也不要用。降低于第三方类库的依赖。

还有一情况是，工具类中的方法非 static 的。那你可以将工具类定义为 final class，考虑到工具类应该不能被继承。在私有化它的构造函数，提供一个单例。

工具类的命名应该用 Util 结尾，例如 LogUtil。

至于工具类中的方法应该设计为静态的还是非静态的，这个没有统一的标准。各有各的好，参加大多数开源框架，static 的多一些。

像大名鼎鼎的 Hutool，它提到了工具类的 6 大设计思想。方法优先于对象、自动识别优于用户定义、便捷性与灵活性并存、适配与兼容、可选依赖原则、无侵入原则。

## 0.1 基本结构

代码规范尽可能参考Hutool，尽可能减少对外部库的依赖。

## 0.7 完成一些基本的数值分析方法

```java
    static class myF implements F {
        @Override
        public double f(double x) {
            return x * x;
        }
    }

    // 复合梯形积分测试
    public static void test2() {
        double res = TrapezoidF(new myF(), 1, 2, 4000);
        System.out.println(res);
    }

    // 辛普森积分测试
    public static void test3() {
        double res = simpson38(new myF(), 1, 2);
        System.out.println(res);
    }

    static class myF2 implements F2 {
        // x2 表示y 即为y'(x,y)函数
        @Override
        public double f(double x, double x2) {
            return x2 - 2 * x / x2;
        }
    }

    // 龙格-库塔常微分测试
    public static void test4() {
        double[] res = rungeKutta(new myF2(), 0, 1, 10, 1);
        System.out.println(res[10 - 1]);
    }

    static class myF3 implements F {
        @Override
        public double f(double x) {
            return x * x - 2 * x;
        }
    }

    // 二分法解方程测试
    public static void test5() throws Exception {
        double res = SolveEquations.bisect(new myF3(), 0.5, 5, 0.01);
        System.out.println(res);
    }

    // 连续求导测试
    public static void test6() {
        F f1 = new DerivedFunction(new myF3());
        F f2 = new DerivedFunction(f1);
        System.out.println(f1.f(1));
        System.out.println(f2.f(1));
    }

    // 牛顿法迭代求方程测试
    public static void test7() {
        double res = SolveEquations.newton(new myF3(), 0.5, 10);
        System.out.println(res);
    }
```

## 1.0 数值分析 -- 线性代数

代码参考colt
