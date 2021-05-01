
import java.util.Random;

/**
 * 蚂蚁类
 *
 * @author FashionXu
 */
public class Ant {
    /**
     * 蚂蚁获得的路径
     */
    public int[] tour;
    /**
     * 蚂蚁选择的物品，0为未选过，1为选过
     */
    int[] unselectItem;
    /**
     * 蚂蚁获得的路径长度
     */
    public int fitness;
    /**
     * 城市数量
     */
    int cities;


    public void RandomSelectCity(int itemCount) {
        cities = itemCount;
        unselectItem = new int[itemCount];
        tour = new int[itemCount + 1];
        fitness = 0;
        for (int i = 0; i < itemCount; i++) {
            tour[i] = -1;
            unselectItem[i] = 1;
        }
        Random rnd = new Random();
        int firstCity = rnd.nextInt(itemCount);
        unselectItem[firstCity] = 0;
        tour[0] = firstCity;
    }

    /**
     * 选择下一个城市
     *
     * @param index    需要选择第index个城市了
     * @param citiesInfo      全局的信息素信息
     * @param distance 全局的距离矩阵信息
     */
    public void SelectNextCity(int index, double[][] citiesInfo, int[][] distance, double alpha, double beta) {
        double[] p;
        p = new double[cities];
        double sum = 0;
        int currentCity = tour[index - 1];
        //计算公式中的分母部分
        for (int i = 0; i < cities; i++) {
            if (unselectItem[i] == 0)
                sum += (Math.pow(citiesInfo[currentCity][i], alpha) *
                        Math.pow(1.0 / distance[currentCity][i], beta));
        }
        //计算每个城市被选中的概率
        for (int i = 0; i < cities; i++) {
            if (unselectItem[i] == 1)
                p[i] = 0.0;
            else {
                p[i] = (Math.pow(citiesInfo[currentCity][i], alpha) *
                        Math.pow(1.0 / distance[currentCity][i], beta)) / sum;
            }
        }
        Random rnd = new Random();
        double selectP = rnd.nextDouble();
        //轮盘赌选择一个城市；
        double sumSelect = 0;
        int selectCity = -1;
        for (int i = 0; i < cities; i++) {
            sumSelect += p[i];
            if (sumSelect >= selectP) {
                selectCity = i;
                break;
            }
        }
        if (selectCity == -1)
            System.out.println();
        tour[index] = selectCity;
        unselectItem[selectCity] = 0;
    }

    /**
     * 计算蚂蚁获得的路径的长度
     *
     * @param distance 全局的距离矩阵信息
     */
    public void CalTourLength(int[][] distance) {
        fitness = 0;
        tour[cities] = tour[0];
        for (int i = 0; i < cities; i++) {
            fitness += distance[tour[i]][tour[i + 1]];
        }
    }
}
