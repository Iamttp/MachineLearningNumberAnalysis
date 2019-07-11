package ML;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadDate {
    private double[][] featureGet;
    private double[][] resGet;
    private boolean flag;

    public double[][] getFeatureGet() {
        return featureGet;
    }

    public double[][] getResGet() {
        return resGet;
    }

    public LoadDate(String path) throws Exception {
        this.flag = load_date(path);
        if (!this.flag) {
            throw new Exception("读取文件错误");
        }
    }

    // 默认最后一行存储结果
    private boolean load_date(String path) {
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            // ！！！中文可能需要设置编码
            FileReader fr = new FileReader(path);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

        int col;
        // 对ArrayList中存储的字符串进行处理
        int length = arrayList.size();
        featureGet = new double[length - 1][];
        for (int i = 0; i < length - 1; i++) {
            String[] s = arrayList.get(i).split(",");
            col = s.length;
            double[] temp = new double[col];
            for (int j = 0; j < s.length; j++) {
                temp[j] = Double.parseDouble(s[j]);
            }
            featureGet[i] = temp;
        }

        // 处理最后一行数据
        String[] s = arrayList.get(arrayList.size() - 1).split(",");
        double[] temp = new double[length - 1];
        for (int j = 0; j < s.length; j++) {
            temp[j] = Double.parseDouble(s[j]);
        }
        resGet = new double[1][];
        resGet[0] = temp;
        return true;
    }
}
