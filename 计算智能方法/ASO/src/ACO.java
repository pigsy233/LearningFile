import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

/**
 * 蚁群优化算法，用来求解TSP问题
 *
 * @author FashionXu
 */
public class ACO {
    //定义蚂蚁群
    Ant[] ants;
    int antCount;//蚂蚁的数量
    int[][] distance;//表示城市间距离
    double[][] citiesInfo;//信息素矩阵
    int cityCount;//城市数量
    int[] bestTour;//求解的最佳路径
    int bestLength;//求的最优解的长度
    double alpha;
    double beta;

    /**
     * 初始化函数
     *
     * @param antNum   系统用到蚂蚁的数量
     */
    public void init(int[] items, int antNum, double alpha, double beta){
        this.alpha = alpha;
        this.beta = beta;
        antCount = antNum;
        ants = new Ant[antCount];

        //处理数据
        Arrays.sort(items);
        int index = items.length-1;
        while (items[index] > )

        //读取数据
//        int[] x;
//        int[] y;
//        String strbuff;
//        BufferedReader tspdata = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
//        strbuff = tspdata.readLine();
//        citycount = Integer.valueOf(strbuff);
//        distance = new int[citycount][citycount];
//        x = new int[citycount];
//        y = new int[citycount];
//        for (int citys = 0; citys < citycount; citys++) {
//            strbuff = tspdata.readLine();
//            String[] strcol = strbuff.split(" ");
//            x[citys] = Integer.valueOf(strcol[1]);
//            y[citys] = Integer.valueOf(strcol[2]);
//        }
        //计算距离矩阵
//        for (int city1 = 0; city1 < cityCount - 1; city1++) {
//            distance[city1][city1] = 0;
//            for (int city2 = city1 + 1; city2 < cityCount; city2++) {
//                distance[city1][city2] = (int) (Math.sqrt((x[city1] - x[city2]) * (x[city1] - x[city2])
//                        + (y[city1] - y[city2]) * (y[city1] - y[city2])) + 0.5);
//                distance[city2][city1] = distance[city1][city2];
//            }
//        }
//        distance[cityCount - 1][cityCount - 1] = 0;
        //初始化信息素矩阵
        citiesInfo = new double[cityCount][cityCount];
        for (int i = 0; i < cityCount; i++) {
            for (int j = 0; j < cityCount; j++) {
                citiesInfo[i][j] = 0.1;
            }
        }
        bestLength = Integer.MAX_VALUE;
        bestTour = new int[cityCount + 1];
        //随机放置蚂蚁
        for (int i = 0; i < antCount; i++) {
            ants[i] = new Ant();
            ants[i].RandomSelectCity(cityCount);
        }
    }

    /**
     * ACO的运行过程
     *
     * @param maxgen ACO的最多循环次数
     */
    public void run(int maxgen) {
        for (int runtimes = 0; runtimes < maxgen; runtimes++) {
            //每一只蚂蚁移动的过程
            for (int i = 0; i < antCount; i++) {
                for (int j = 1; j < cityCount; j++) {
                    ants[i].SelectNextCity(j, citiesInfo, distance, alpha, beta);
                }
                //计算蚂蚁获得的路径长度
                ants[i].CalTourLength(distance);
                if (ants[i].fitness < bestLength) {
                    //保留最优路径
                    bestLength = ants[i].fitness;
                    System.out.println("第" + runtimes + "代，发现新的解" + bestLength);
                    for (int j = 0; j < cityCount + 1; j++)
                        bestTour[j] = ants[i].tour[j];
                }
            }
            //更新信息素矩阵
            UpdateCitiesInfo();
            //重新随机设置蚂蚁
            for (int i = 0; i < antCount; i++) {
                ants[i].RandomSelectCity(cityCount);
            }
        }
    }

    /**
     * 更新信息素矩阵
     */
    private void UpdateCitiesInfo() {
        double rou = 0.5;
        //信息素挥发
        for (int i = 0; i < cityCount; i++)
            for (int j = 0; j < cityCount; j++)
                citiesInfo[i][j] = citiesInfo[i][j] * (1 - rou);
        //信息素更新
        for (int i = 0; i < antCount; i++) {
            for (int j = 0; j < cityCount; j++) {
                citiesInfo[ants[i].tour[j]][ants[i].tour[j + 1]] += 1.0 / ants[i].fitness;
            }
        }
    }

    /**
     * 输出程序运行结果
     */
    public void ReportResult() {
        System.out.println("最优路径长度是" + bestLength);
    }

    public static void main(String[] args) {
        ACO aco;
        aco = new ACO();
        aco.init( 50, 1.0, 2.0);
        aco.run(2000);
        aco.ReportResult();

    }
}

