package ML;

import matrix.DoubleMatrix2D;

import java.util.ArrayList;
import java.util.List;

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
        // TODO Returns indices of and distances to the neighbors of each point. 返回每个点的邻居的索引和距离。
        List<List<Double>> res1 = kneighbors(testX);
        assert res1 != null;
        List<Double> neigh_dist = res1.get(0);
        List<Double> neigh_ind = res1.get(1);

        return null;
    }

    /**
     * dist: Array representing the lengths to points 表示点的长度的数组
     * ind : Indices of the nearest points in the population matrix. 人口矩阵中最近点的指数。
     *
     * @param testX
     */
    private List<List<Double>> kneighbors(DoubleMatrix2D testX) {
        // TODO

        return null;
    }

    @Override
    public double score(DoubleMatrix2D testX, DoubleMatrix2D testY) {
        return 0;
    }
}
