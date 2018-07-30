/**
 * Created by Lysergids on 2016/5/23 0023.
 */

import java.util.Random;
import java.util.Vector;

public class Ant implements Cloneable{
    private Vector<Integer> tabu;
    private Vector<Integer> allowedProject;
    private float[] delta;
    private float[] timeLimit;
    private float[] phtime;
    private String[] strpre;
    private float[] reducetime;
    private float alpha; //信息素系数
    private float beta; //信息素系数
    private float gama; //资金系数
    private float omega; //资金系数
    private float timeLength;
    private int projectNum;
    private int firstProject;
    private int currentProject;
    private Graph gra;
    private int[] event;
    private int[] ve;
    private int[] vl;

    public Ant() {
        projectNum = 30;
        timeLength = 0;

    }

    // 初始化蚂蚁数量
    public Ant(int num) {
        projectNum = num;
        timeLength = 0;

    }

    public void init(float[] timeLimit,String[] str, float[] rt, float a, float b, float c, float d){
        alpha = a;
        beta = b;
        gama = c;
        omega = d;
        strpre = str;
        reducetime = rt;
        allowedProject = new Vector<Integer>();
        tabu = new Vector<Integer>();
        this.timeLimit = timeLimit;
        phtime = new float[projectNum];
        delta = new float[projectNum];
        for (int i = 0; i < projectNum; i++) {
            Integer integer = new Integer(i);
            allowedProject.add(integer);
            delta[i] = 0.f;
        }
    }

    public void init(float[] timeLimit, float[] rt, float a, float b, float c, float d){
        alpha = a;
        beta = b;
        gama = c;
        omega = d;
        reducetime = rt;
        allowedProject = new Vector<Integer>();
        tabu = new Vector<Integer>();
        this.timeLimit = timeLimit;
        delta = new float[projectNum];
        for (int i = 0; i < projectNum; i++) {
            Integer integer = new Integer(i);
            allowedProject.add(integer);
            delta[i] = 0.f;
        }
    }

    public void selectNext(float[] pheromone) {
        float[] p = new float[projectNum];
        float sum = 0.0f;
        // 计算分母部分
        for (Integer i : allowedProject) {
            sum += Math.pow(pheromone[i.intValue()], alpha) * Math.pow(1.0 / timeLimit[i.intValue()], beta);
        }
        // 计算概率矩阵
        for (int i = 0; i < projectNum; i++) {
            boolean flag = false;
            for (Integer j : allowedProject) {

                if (i == j.intValue()) {
                    p[i] = (float) (Math.pow(pheromone[i], alpha) * Math.pow(1.0 / timeLimit[i], beta)) / sum;
                    flag = true;
                    //System.out.println(p[i]);
                    break;
                }
            }

            if (flag == false) {
                p[i] = 0.f;
            }
        }

        // 蚂蚁进行概率选择下一个项目
        Random random = new Random(System.currentTimeMillis());
        float sleectP = random.nextFloat();
        int selectProject = 0;
        float sum1 = 0.f;
        for (int i = 0; i < projectNum; i++) {
            sum1 += p[i];
            if (sum1 >= sleectP) {
                selectProject = i;
                break;
            }
        }

        // 从允许选择的项目中去除完成的项目
        for (Integer i : allowedProject) {
            if (i.intValue() == selectProject) {
                allowedProject.remove(i);
                break;
            }
        }
        // 在禁忌表中添加完成项目
        tabu.add(Integer.valueOf(selectProject));
        // 将当前项目改为选择的项目
        currentProject = selectProject;

    }

    private float calculatetimeLength() {
        float len = 0;
        float[] tl = new float[projectNum];
        for (int i = 0; i < projectNum; i++){
            tl[i] = timeLimit[i];
        }
        for (int i = 0; i < projectNum; i++) {
            tl[this.tabu.get(i).intValue()] =  (float) (tl[this.tabu.get(i).intValue()]*(1 - Math.pow(reducetime[i] , gama) * omega));
            phtime[i] = tl[this.tabu.get(i).intValue()];
        }

        gra = new Graph(projectNum);
        for(int i = 1; i < (projectNum+1); i++){
            gra.add(String.valueOf(i));
        }

        for(int i = projectNum - 1; i > 0; i--){
            String[] strcol = strpre[i].split("'");
            for(int j = 0; j < strcol.length; j++){
                int weight;
                //System.out.println(timeLimit[Integer.valueOf(strcol[j])]);
                weight = (int)(tl[Integer.valueOf(strcol[j])] + 0.5);
                gra.addEdge(Integer.valueOf(strcol[j]), i, weight);
            }
        }
        if (gra.topo()) {
            gra.calculate();

            int[] ve = gra.getVE();
            int[] vl = gra.getVl();
            int[] key = new int[gra.getPNum()];


            len = ve[ve.length-1] +timeLimit[projectNum-1];
            for (int w = 0; w < gra.getPNum(); w++) {
                key[w] = gra.getPoint()[w];
                //System.out.print(key[w] + "      ");
            }
            this.setKeyevent(key);
            this.setVe(ve);
            this.setVl(vl);
            //System.out.println();
        }
            //System.out.println(len);
            return len;
    }



    public Vector<Integer> getAllowedProject() {
        return allowedProject;
    }

    public void setAllowedProject(Vector<Integer> allowedProject) {
        this.allowedProject = allowedProject;
    }

    public float getTimeLength() {
        timeLength = calculatetimeLength();
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public int[] getKeyevent(){ return event; }

    public void setKeyevent(int[] key){ event = key; }

    public int[] getVe(){ return ve; }

    public void setVe(int[] key){ ve = key; }

    public int[] getVl(){ return vl; }

    public void setVl(int[] key){ vl = key; }

    public int getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(int projectNum) {
        this.projectNum = projectNum;
    }

    public Vector<Integer> getTabu() {
        return tabu;
    }

    public void setTabu(Vector<Integer> tabu) {
        this.tabu = tabu;
    }

    public float[] getDelta() {
        return delta;
    }

    public float[] getPhtime() {
        return phtime;
    }

    public void setDelta(float[] delta) {
        this.delta = delta;
    }

    public int getFirstProject() {
        return firstProject;
    }

    public void setFirstProject(int firstProject) {
        this.firstProject = firstProject;
    }
}
