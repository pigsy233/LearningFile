import java.util.Random;

public class SimulatedAnnealing {


    private static Random random = new Random();

    /**
     * 城市临接矩阵
     */
    private double[][] citys;

    /**
     * 温度
     */
    private double temp;

    /**
     * 内层平衡循环代数
     */
    private int k;

    /**
     * 降温系数
     */
    private double des;

    /**
     * 最小温度
     */
    private double minTemp;
    /**
     * 城市个数
     */
    private int n;

    /**
     * 当前接受的解
     */
    private int[] x;

    /**
     * 当前进行的解
     */
    private int[] y;

    /**
     * 当前解的适应值
     */
    private int xFitness = 0;

    /**
     * 当前进行的解的适应值
     */
    private int yFitness = 0;

    /**
     * 产生初始化解
     *
     * @param n 城市个数
     * @return 初始化的解
     */
    private void initAns(int n) {

        x = new int[n];
        y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = i + 1;
            y[i] = i + 1;
        }
        xFitness = calculateFitness();
        yFitness = xFitness;

    }

    /**
     * 产生新解
     */
    private void newAns() {

        int i = random.nextInt(n - 1) + 1;
        int j = random.nextInt(n - 1) + 1;
        while (i == j) {
            j = random.nextInt(n - 1) + 1;
        }

        int m = y[i];
        y[i] = y[j];
        y[j] = m;
        yFitness = calculateFitness();
    }

    /**
     * 模拟降温
     */
    private void drop() {
        temp *= des;
    }

    /**
     * 更新当前解
     */
    private void update() {
        xFitness = yFitness;
        for (int i = 0; i < n; i++) {
            x[i] = y[i];
        }
    }

    /**
     * 计算接受新解的概率
     *
     * @return 概率
     */
    private double acceptP() {
        double f = (xFitness - yFitness) / (temp *1.380649E-23);
        return Math.pow(Math.E, f);
    }

    /**
     * 打印当前解
     */
    private void printAns() {

        for (int a : x) {
            System.out.print(a + "->");
        }
        System.out.print(x[0]);
        System.out.println();
    }

    /**
     * 计算适应度
     *
     * @return 适应度
     */
    private int calculateFitness() {
        int fit = 0;
        for (int i = 1; i < n; i++) {
            fit += citys[y[i - 1] - 1][y[i] - 1];
        }
        fit += citys[y[n - 1] - 1][y[0] - 1];
        return fit;
    }

    /**
     * SA算法核心
     */
    public void SA() {


        initAns(n);

        int times = 0;
        while (temp > minTemp) {

            for (int i = 0; i < k; i++) {
                newAns();
                yFitness = calculateFitness();
                if (yFitness <= xFitness)
                    update();
                else {
                    if (random.nextDouble() <= acceptP())
                        update();
                }
            }

            drop();
            times++;
        }

        printAns();
        System.out.println("路径长度" + yFitness);
        System.out.println("进行" + times + "降温");
    }

    /**
     * 将坐标转化为邻接矩阵
     *
     * @param vec 坐标
     * @param n   城市个数
     * @return
     */
    public double[][] vec2Dis(int[][] vec, int n) {

        double citys[][] = new double[n][n];
        double dis;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    citys[i][j] = 0;
                } else {
                    dis = Math.sqrt((vec[j][0] - vec[i][0]) * (vec[j][0] - vec[i][0]) + (vec[j][1] - vec[i][1]) * (vec[j][1] - vec[i][1]));
                    citys[i][j] = dis;
                    citys[j][i] = dis;
                }
            }

        }
        return citys;
    }

    /**
     * @param citys   城市临接矩阵
     * @param temp    初始温度
     * @param n       城市个数
     * @param k       内层平衡循环代数
     * @param des     降温系数
     * @param minTemp 最小温度
     */
    SimulatedAnnealing(double[][] citys, double temp, int n, int k, double des, double minTemp) {
        this.citys = citys;
        this.temp = temp;
        this.n = n;
        this.k = k;
        this.des = des;
        this.minTemp = minTemp;
    }


    public static void main(String[] args) {

//        double citys[][] = new double[10][10];
        double citys[][] = {{-1, 9, 19, 13, 15, 98, 56, 12, 54, 65},
                {21, -1, 7, 14, 8, 54, 5, 34, 6, 7},
                {1, 40, -1, 17, 25, 5, 79, 54, 2, 35},
                {41, 80, 10, -1, 54, 23, 98, 54, 21, 4},
                {25, 42, 12, 7, -1, 23, 91, 54, 24, 88},
                {7, 21, 65, 78, 45, -1, 98, 6, 78, 12},
                {45, 78, 57, 12, 52, 13, -1, 54, 1, 64},
                {51, 85, 35, 11, 57, 83, 91, -1, 21, 44},
                {47, 30, 33, 15, 54, 27, 18, 51, -1, 5},
                {3, 21, 14, 35, 4, 35, 8, 53, 21, -1},};
        SimulatedAnnealing sa = new SimulatedAnnealing(citys, 50000.0, 10, 50, 0.99, 1E-8);
        sa.SA();
    }
}
