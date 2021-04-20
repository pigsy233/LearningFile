import java.io.*;
import java.util.*;


public class AntColonyAlgorithm {

    /**
     * 蚂蚁数量
     */
    private  int ac;
    /**
     * 蚂蚁列表
     */
    private Ant ants[];

    /**
     * 城市邻接矩阵
     */
    private double citys[][];

    /**
     * 城市信息素列表
     */
    private double citysInfo[][];

    /**
     * 城市数量
     */
    private int cityNum;

    /**
     * 信息素蒸发速率
     */
    private double evaporateCoefficient;

    /**
     * 距离影响系数
     */
    private double disAlpha;

    /**
     * 平衡迭代次数
     */
    private int k;

    /**
     * 信息素影响系数
     */
    private double informationBeta;

    /**
     * 城市列表
     */
    private List<Integer> memory = new ArrayList<Integer>();

    /**
     * 选择城市及其概率
     */
    private TreeMap<Integer ,Double> selectPer = new TreeMap<Integer ,Double>();

    /**
     * 当前最优解
     */
    private double bestFitness;

    /**
     * 当前最优路径
     */
    private int[] x;

    Writer out;


    /**
     * 蚂蚁
     */
    class Ant {

        /**
         * 随机数产生器
         */
        private Random random = new Random();

        /**
         * 当前蚂蚁的解
         */
        private int[] x;

        /**
         * 当前解的适应度
         */
        public double fitness;

        /**
         * 对蚂蚁分配n个路径节点
         */
        Ant() {
            x = new int[cityNum];
        }

        /**
         * 返回当前蚂蚁的解
         *
         * @return
         */
        public int[] getX() {
            return x;
        }

        /**
         * 返回当前解的适应度
         *
         * @return
         */
        public double getFitness() {
            return fitness;
        }

        /**
         * 计算当前城市去往下一个城市的概率
         *
         * @param now 当前城市
         * @return 计算概率的分母
         */
        public double calculateP(int now) {
            selectPer.clear();
            double sum = 0;
            for (int i = 0; i < cityNum; i++) {
                if (memory.indexOf(i) != -1){
                    sum += Math.pow(citysInfo[now][i], informationBeta) * Math.pow(citys[now][i], -disAlpha);//Math.pow(citys[now - 1][i], -1)
                    selectPer.put(i,sum);
                }
            }
            return sum;
        }

        public double calculateFitness() {
            fitness = 0;
            for (int i = 1; i < cityNum; i++) {
//                System.out.println(citys[x[i - 1] - 1][x[i] - 1]);
                fitness += citys[x[i - 1]][x[i]];
//                System.out.println("fitness:" + fitness);
            }
            fitness += citys[x[x.length - 1]][x[0]];
//            System.out.println(fitness);
            return fitness;
        }

        /**
         * 更新蚂蚁当前解
         */
        public void update() {
            clear();
            int index;
            index = random.nextInt(memory.size());
            int cityNo = memory.get(index);
            memory.remove(index);
            x[0] = cityNo;
            for (int i = 1; i < cityNum; i++) {

                double d =random.nextDouble() * calculateP(cityNo);
                Iterator iterator = selectPer.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry entry = (Map.Entry)iterator.next();
                    if (d <= (double)entry.getValue()){
                        x[i] = (int)entry.getKey();
                        memory.remove((Integer) x[i]);
                        break;
                    }
                }

            }

            calculateFitness();
//            System.out.println(calculateFitness());
        }

        /**
         * 清除上次计算痕迹
         */
        public void clear() {
            memory.clear();
            selectPer.clear();
            for (int i = 0; i < cityNum; i++) {
                x[i] = 0;
                memory.add(i);
            }
            fitness = 0;
        }

    }

    /**
     * 通过参数列表设置蚁群算法
     *
     * @param cityNum              城市数量
     * @param cites                城市邻接矩阵
     * @param evaporateCoefficient 信息素蒸发速率
     * @param disAlpha             距离影响因数
     * @param informationBeta      信息素影响因素
     * @param initInfo             初始信息素
     * @param k                    平衡迭代次数
     */
    AntColonyAlgorithm(int cityNum, double[][] cites, double evaporateCoefficient, double disAlpha, double informationBeta, double initInfo, int k, int ac) throws IOException {

        this.cityNum = cityNum;
        this.evaporateCoefficient = evaporateCoefficient;
        this.disAlpha = disAlpha;
        this.informationBeta = informationBeta;
        this.citys = cites;
        this.k = k;
        this.ac = ac;
        citysInfo = new double[cityNum][cityNum];
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                citysInfo[i][j] = initInfo;
            }
        }
        File file = new File("test.txt");
        if (!file.exists())
            file.createNewFile();
        out = new FileWriter(file);
    }

    private void init() {
        Ant a = new Ant();
        a.update();
        x = a.getX();
        ants = new Ant[ac];
        for (int i = 0; i < ac; i++) {
            ants[i] = new Ant();
        }
        bestFitness = a.getFitness();
    }

    /**
     * 进行路径迭代
     */
    private void update() {

        for (Ant a : ants) {
            a.update();
        }
    }

    /**
     * 进行最优解迭代
     */
    private void updateBest() {
        for (Ant a : ants) {
            if (a.fitness < bestFitness) {
                bestFitness = a.fitness;
                x = a.getX();
                System.out.println("updated  " + bestFitness);
            }
        }
    }


    private void drop() {
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                citysInfo[i][j] *= 1-evaporateCoefficient;
            }
        }
        int x[];
        double release;
        for (Ant a : ants) {
            x = a.getX();
            release = 1.0/a.getFitness();
            for (int i = 1; i < x.length; i++) {
                 citysInfo[x[i-1]][x[i]] += release;
            }
            citysInfo[x[9]][x[0]] += release;
        }
    }

    private void printInfo() throws IOException {

        for (int i = 0; i < cityNum; i++) {
            out.write(x[i] + "\n");
        }
        out.flush();
        out.close();
        System.out.println(bestFitness);
    }

    /**
     * 算法控制框架
     */
    public void ACA() throws IOException {
        init();

        for (int i = 0; i < k; i++) {

            update();
            updateBest();
            drop();
        }

        printInfo();
    }

    public static double[][] vec2Dis(double[][] vec, int n) {

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

    public static void main(String[] args) throws Exception {

        double vec[][] = {
                {42.1140318271001,	61.4047547306191},
                {18.9399476786959,	66.3555240941141},
                {99.8951163318083,	72.2153342838984},
                {53.2101718582436,	67.4395759313246},
                {74.9386467804577,	2.76684506993165},
                {61.7626052857277,	56.4340594412483},
                {82.2294788788426,	85.8418349945237},
                {28.2489169781314,	72.3540311466207},
                {38.8530720779781,	59.6801183871400},
                {60.3474512267332,	11.7205921743973}
                };
        double [][] citys = vec2Dis(vec,10);

        AntColonyAlgorithm aca = new AntColonyAlgorithm(10,citys,0.5,1,5,0.3,200, 50);
        //aca.ACA();
        Ant a = aca.new Ant();
        a.update();
        a.calculateFitness();
        //a.calculateP();

    }

}

