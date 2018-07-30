import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Lysergids on 2016/5/24 0024.
 */
public class AP implements Cloneable{
    private Ant[] ants; // 蚂蚁
    private int antNum; // 蚂蚁数量
    private int projectNum; // 项目数量
    private int MAX_GEN; // 运行代数
    int cm = 0;
    private float[] pheromone; // 信息素矩阵
    private float[] timeLimit; // 工期矩阵
    private String[] strpre;
    private float bestLength; // 最佳长度
    private float leastCost; // 最佳长度
    private float expectedLength;
    private int[] bestTour; // 最佳路径
    private int[] ve;
    private int[] vl;

    private int ctime = 0;
    private float alpha;
    private float beta;
    private float gama;
    private float omega;
    private float rho;
    private float ar;
    private float er;
    private float tc;
    private float oc;
    private float cost;
    private float rc;

    private boolean advanced;



    public AP() {

    }

    public AP(int n, int m, int g, float a, float b, float c, float d, float r) {
        projectNum = n;
        antNum = m;
        ants = new Ant[antNum];
        MAX_GEN = g;
        alpha = a;
        beta = b;
        gama = c;
        omega = d;
        rho = r;
        advanced = true;
    }

    public AP(int n, int m, int g, float a, float b, float c, float d, float r, float ar, float er, float el, float tc, float oc, float rc) {
        projectNum = n;
        antNum = m;
        ants = new Ant[antNum];
        MAX_GEN = g;
        alpha = a;
        beta = b;
        gama = c;
        omega = d;
        rho = r;
        this.ar = ar;
        this.er = er;
        this.tc = tc;
        this.oc = oc;
        this.rc = rc;
        expectedLength = el;
        advanced = false;
    }

    public void init(String filename) throws IOException {
        // 读取数据
        int[] x;
        String strbuff;
        BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

        timeLimit = new float[projectNum];
        x = new int[projectNum];
        strpre = new String[projectNum];
        for (int i = 0; i < projectNum; i++) {
            strbuff = data.readLine();
            String[] strcol = strbuff.split(" ");
            x[i] = Integer.valueOf(strcol[1]);
            strpre[i] = strcol[2];
        }

        // 初始化信息素队列和数据队列
        pheromone = new float[projectNum];
        for (int i = 0; i < projectNum; i++) {
            timeLimit[i] = x[i];
            pheromone[i] = 0.1f; // 初始化为0.1
        }
        bestLength = Integer.MAX_VALUE;
        leastCost = Integer.MAX_VALUE;
        bestTour = new int[projectNum + 1];
        // 随机放置蚂蚁

        float[] rt = reduceB();

        for (int i = 0; i < antNum; i++) {
            ants[i] = new Ant(projectNum);
            ants[i].init(timeLimit, strpre, rt, alpha, beta, gama, omega);
        }
    }

    public void init(float[] Limit){

        pheromone = new float[projectNum];
        for (int i = 0; i < projectNum; i++) {
            pheromone[i] = 0.01f; // 初始化为0.01
        }
        bestLength = Integer.MAX_VALUE;
        leastCost = Integer.MAX_VALUE;
        bestTour = new int[projectNum + 1];
        // 随机放置蚂蚁

        float[] rt = reduceA();

        for (int i = 0; i < antNum; i++) {
            ants[i] = new Ant(projectNum);
            ants[i].init(Limit, strpre, rt, alpha, beta, gama, omega);
        }
    }

    public float[] reduceA(){
        reduceTimeRatio rtr = new reduceTimeRatio(projectNum);
        rtr.result();
        return rtr.above();
    }

    private float[] reduceB(){
        reduceTimeRatio rtr = new reduceTimeRatio(projectNum);
        rtr.result();
        return rtr.below(ar);
    }

    public void solve() {

        for (int g = 0; g < MAX_GEN; g++) {

            cm++;
            for (int i = 0; i < antNum; i++) {
                for (int j = 1; j < projectNum; j++) {
                    ants[i].selectNext(pheromone);
                }

                float sum = Integer.MAX_VALUE;
                ants[i].getTabu().add(ants[i].getFirstProject());
                float dec = ants[i].getTimeLength()-expectedLength;
                if(ants[i].getTimeLength()<= expectedLength)
                    dec = 0.0f;
                sum = tc*ants[i].getTimeLength() + oc*dec + ar*rc;
                if ((ants[i].getTimeLength() < expectedLength*er)&&(sum < leastCost)) {
                    bestLength = ants[i].getTimeLength();
                    bestTour = ants[i].getKeyevent();
                    ve = ants[i].getVe();
                    vl = ants[i].getVl();
                    leastCost = sum;
                    ctime = cm;
                    //for (int k = 0; k < projectNum + 1; k++) {
                        //bestTour[k] = ants[i].getTabu().get(k).intValue();
                    //}
                }
                for (int j = 0; j < projectNum; j++) {
                    ants[i].getDelta()[ants[i].getTabu().get(j).intValue()] = (float) (1. / ants[i].getPhtime()[j]);
                }
            }

            // 更新信息素
            updatePheromone();

            float[] rt;

            if(advanced) {
                rt = reduceA();
            }
            else {
                rt = reduceB();
            }

            // 重新初始化蚂蚁
            for (int i = 0; i < antNum; i++) {
                ants[i].init(timeLimit, strpre, rt, alpha, beta, gama, omega);
            }
        }

        // 打印最佳结果
        //printOptimal();
    }
    // 更新信息素
    private void updatePheromone() {
        // 信息素挥发
        for (int i = 0; i < projectNum; i++)
            pheromone[i] = pheromone[i] * (1 - rho);
        // 信息素更新
        for (int i = 0; i < projectNum; i++) {
            for (int k = 0; k < antNum; k++) {
                pheromone[i] += ants[k].getDelta()[i];
            }
        }
    }

    private void printOptimal() {
        System.out.println("The optimal subObject length is: " + bestLength);
        for (int i = 0; i < bestTour.length; i++) {
            System.out.print(bestTour[i]+"     ");
        }
        System.out.println(ar*1000000);
        /**for (int i = 0; i < projectNum + 1; i++) {
            //System.out.println(bestTour[i]);
        }
        for (int i = 0; i < projectNum; i++) {
            for (int j = 0; j < projectNum; j++) {
                for (int k = 0; k < antNum; k++) {
                    System.out.println("The optimal delta is: " + ants[k].getDelta()[i] );
                }
            }
        }
         */
    }

    public Ant[] getAnts() {
        return ants;
    }

    public void setAnts(Ant[] ants) {
        this.ants = ants;
    }

    public int getAntNum() {
        return antNum;
    }

    public void setAntNum(int m) {
        this.antNum = m;
    }

    public int getprojectNum() {
        return projectNum;
    }

    public void setprojectNum(int projectNum) {
        this.projectNum = projectNum;
    }

    public int getMAX_GEN() {
        return MAX_GEN;
    }

    public void setMAX_GEN(int mAX_GEN) {
        MAX_GEN = mAX_GEN;
    }

    public float[] getPheromone() {
        return pheromone;
    }

    public void setPheromone(float[] pheromone) {
        this.pheromone = pheromone;
    }

    public float[] gettimeLimit() {
        return timeLimit;
    }

    public void settimeLimit(float[] timeLimit) {
        this.timeLimit = timeLimit;
    }

    public float getBestLength() {
        return bestLength;
    }

    public float getLeastCost() {
        return leastCost;
    }

    public void setBestLength(int bestLength) {
        this.bestLength = bestLength;
    }

    public int[] getBestTour() {
        return bestTour;
    }

    public int[] getVe() {
        return ve;
    }

    public int[] getVl() {
        return vl;
    }

    public void setBestTour(int[] bestTour) {
        this.bestTour = bestTour;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getBeta() {
        return beta;
    }

    public void setBeta(float beta) {
        this.beta = beta;
    }

    public float getRho() {
        return rho;
    }

    public void setRho(float rho) {
        this.rho = rho;
    }
}
