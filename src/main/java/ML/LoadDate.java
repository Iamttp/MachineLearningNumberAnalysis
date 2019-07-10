package ML;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadDate {
    // 默认最后一行存储结果
    public static double[][] load_breast_cancer(String path, int col, double[][] resGet) {
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList<>();
        try {
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
            e.printStackTrace();
        }

        // 对ArrayList中存储的字符串进行处理
        int length = arrayList.size();
        double[][] res = new double[length - 1][col];
        for (int i = 0; i < length - 1; i++) {
            String[] s = arrayList.get(i).split(",");
            double[] temp = new double[col];
            for (int j = 0; j < s.length; j++) {
                temp[j] = Double.parseDouble(s[j]);
            }
            res[i] = temp;
        }

        String[] s = arrayList.get(arrayList.size() - 1).split(",");
        double[] temp = new double[length - 1];
        for (int j = 0; j < s.length; j++) {
            temp[j] = Double.parseDouble(s[j]);
        }
        resGet[0] = temp;

        return res;
    }
}
