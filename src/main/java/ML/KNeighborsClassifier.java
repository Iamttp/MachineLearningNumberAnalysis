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
public class KNeighborsClassifier extends AbstractMLBase {
    private int n_neighbors;

    public KNeighborsClassifier(int n_neighbors) {
        this.n_neighbors = n_neighbors;
    }

    @Override
    public double[] predict(DoubleMatrix2D testX) {
        double[] res = new double[testX.rows()];
        for (int i = 0; i < testX.rows(); i++) {
            // 对每一行进行预测
            // TODO 优化， 使DoubleMatrix2D直接返回数组
            ArrayList<Double> in = new ArrayList<>();
            for (int j = 0; j < testX.columns(); j++) {
                in.add(testX.getQuick(i, j));
            }
            List<Integer> neigh_ind = kneighbors(in);

            DoubleMatrix2D y = this._y;
            int n_neig = this.n_neighbors;
            HashMap<Double, Integer> map = new HashMap<>();
            for (int j = 0; j < n_neig; j++) {
                // 最近邻居的下标
                int num = neigh_ind.get(j);
                if (map.containsKey(y.getQuick(0, num))) {
                    map.put(y.getQuick(0, num), map.get(y.getQuick(0, num)) + 1);
                } else {
                    map.put(y.getQuick(0, num), 1);
                }
            }
//        System.out.println(neigh_ind);
//            System.out.println(map);
            int max = 0;
            double key = 0;
            for (Map.Entry entry : map.entrySet()) {
                if ((Integer) entry.getValue() > max) {
                    max = (Integer) entry.getValue();
                    key = (Double) entry.getKey();
                }
            }
            res[i] = key;
        }
        return res;
    }

    /**
     * dist: Array representing the lengths to points 表示点的长度的数组
     * ind : Indices of the nearest points in the population matrix. 人口矩阵中最近点的指数。
     *
     * @param testX
     * @return indices of and distances to the neighbors of each point. 返回每个点的邻居的索引和距离。
     */
    private List<Integer> kneighbors(List<Double> testX) {
        List<Double> dist = new ArrayList<>();
        DoubleMatrix2D train_X = this._fit_X;
        for (int i = 0; i < train_X.rows(); i++) {
            double distance = 0;
            for (int j = 0; j < train_X.columns(); j++) {
                double x = testX.get(j);
                double y = train_X.getQuick(i, j);
                // TODO 欧式距离, 最后好像要开根号
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
        // TODO 返回dist，距离未返回
//        System.out.println(dist);
        return ind;
    }
}
