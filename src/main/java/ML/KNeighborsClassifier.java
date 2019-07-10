package ML;

import matrix.DoubleMatrix2D;

import java.util.*;

/**
 * k邻近算法
 * 1>分别计算改点与已经类型数据集中的每个点之间的距离；
 * 2>按距离递增排序；
 * 3>选取距离最小的前k个点；
 * 4>统计这k个点，对应类别，每个类别出现的频率；
 * 5>这k个点，对应类别，出现频率最高的作为当前点的预测分类；
 *
 * @author ttp
 */
public class KNeighborsClassifier implements MLBase {
    private int n_neighbors;
    private DoubleMatrix2D _fit_X;
    private DoubleMatrix2D _y;

    public KNeighborsClassifier(int n_neighbors) {
        this.n_neighbors = n_neighbors;
    }

    @Override
    public MLBase fit(DoubleMatrix2D trainX, DoubleMatrix2D trainY) {
        // TODO check_X_y 核对数据是否规范
        this._fit_X = trainX;
        this._y = trainY;
        return this;
    }

    @Override
    public DoubleMatrix2D predict(DoubleMatrix2D testX) {
        List<Integer> neigh_ind = kneighbors(testX);
        DoubleMatrix2D y = this._y;
        int n_neig = this.n_neighbors;
        HashMap<Double, Integer> map = new HashMap<>();
        for (int i = 0; i < n_neig; i++) {
            int num = neigh_ind.get(i);
            if (map.containsKey(y.getQuick(0, num))) {
                map.put(y.getQuick(0, num), map.get(y.getQuick(0, num)) + 1);
            } else {
                map.put(y.getQuick(0, num), 0);
            }
        }
        System.out.println(neigh_ind);
        System.out.println(map);
        return null;
    }

    /**
     * dist: Array representing the lengths to points 表示点的长度的数组
     * ind : Indices of the nearest points in the population matrix. 人口矩阵中最近点的指数。
     *
     * @param testX
     * @return indices of and distances to the neighbors of each point. 返回每个点的邻居的索引和距离。
     */
    private List<Integer> kneighbors(DoubleMatrix2D testX) {
        List<Double> dist = new ArrayList<>();
        DoubleMatrix2D train_X = this._fit_X;
        for (int i = 0; i < train_X.rows(); i++) {
            double distance = 0;
            for (int j = 0; j < train_X.columns(); j++) {
                double x = testX.getQuick(0, j);
                double y = train_X.getQuick(i, j);
                // TODO 是否是这样计算
                distance += (x - y) * (x - y);
            }
            dist.add(distance);
        }
        HashMap<Double, Integer> map = new HashMap<>();
        //将值和下标存入Map
        for (int i = 0; i < dist.size(); i++) {
            map.put(dist.get(i), i);
        }
        Collections.sort(dist);
        List<Integer> ind = new ArrayList<>();
        for (Double aDouble : dist) {
            ind.add(map.get(aDouble));
        }
        // TODO 返回dist
        return ind;
    }

    @Override
    public double score(DoubleMatrix2D testX, DoubleMatrix2D testY) {
        return 0;
    }
}
