import java.io.IOException;

/**
 * Created by Lysergids on 2016/5/26 0026.
 */
public class MainR {
    public static void main(String[] args) throws IOException {
        long begin = System.currentTimeMillis();
        float[] ratio;
        float length = 0.0f;
        float best = Integer.MAX_VALUE;
        int cm = 0;
        int cmt = 0;
        int cmp = 0;
        int[] empty = new int[1];
        int[] bestTour1 = empty;
        int[] bestTour2 = empty;
        int[] bestTour3 = empty;
        int[] bestTour4 = empty;
        int[] ve1 = empty;
        int[] ve2 = empty;
        int[] ve3 = empty;
        int[] ve4 = empty;
        int[] vl1 = empty;
        int[] vl2 = empty;
        int[] vl3 = empty;
        int[] vl4 = empty;
        float[] bestSub = new float[4];
        float ra1 = 0.00000f;
        float ra2 = 0.00000f;
        float ra3 = 0.00000f;
        float ra4 = 0.00000f;

        for(int i = 0; i < 100;i++) {

            cmp++;
            reduceTimeRatio rtr = new reduceTimeRatio(7);
            rtr.result();
            ratio = rtr.above();

            AP ap1 = new AP(10, 100, 100, 1.f, 5.f, 0.3f, 0.4f, 0.3f, ratio[0], 1.05f, 120.0f, 100, 100, 10000);
            ap1.init("E:\\Project\\AntS\\data\\data1.txt");
            ap1.solve();
            AP ap2 = new AP(10, 100, 100, 1.f, 5.f, 0.3f, 0.4f, 0.3f, ratio[1], 1.08f, 120.0f, 100, 100, 10000);
            ap2.init("E:\\Project\\AntS\\data\\data2.txt");
            ap2.solve();
            AP ap3 = new AP(10, 100, 100, 1.f, 5.f, 0.3f, 0.4f, 0.3f, ratio[2], 1.1f, 135.0f, 100, 100, 10000);
            ap3.init("E:\\Project\\AntS\\data\\data3.txt");
            ap3.solve();
            AP ap4 = new AP(10, 100, 100, 1.f, 5.f, 0.3f, 0.4f, 0.3f, ratio[3], 1.08f, 125.0f, 100, 100, 10000);
            ap4.init("E:\\Project\\AntS\\data\\data4.txt");
            ap4.solve();

            float leastcost = ap1.getLeastCost() + ap2.getLeastCost() + ap3.getLeastCost() + ap4.getLeastCost();
            if(leastcost < best) {
                best = leastcost;
                bestTour1 = ap1.getBestTour();
                bestTour2 = ap2.getBestTour();
                bestTour3 = ap3.getBestTour();
                bestTour4 = ap4.getBestTour();
                ve1 = ap1.getVe();
                ve2 = ap2.getVe();
                ve3 = ap3.getVe();
                ve4 = ap4.getVe();
                vl1 = ap1.getVl();
                vl2 = ap2.getVl();
                vl3 = ap3.getVl();
                vl4 = ap4.getVl();
                bestSub[0] = ap1.getBestLength();
                bestSub[1] = ap2.getBestLength();
                bestSub[2] = ap3.getBestLength();
                bestSub[3] = ap4.getBestLength();
                ra1 = ap1.getLeastCost();
                ra2 = ap2.getLeastCost();
                ra3 = ap3.getLeastCost();
                ra4 = ap4.getLeastCost();
                cmt = (cmt + (ap1.cm + ap2.cm + ap3.cm + ap4.cm)/4)/2;
                cm = cmp;
                length = ap1.getBestLength() + ap2.getBestLength() + ap3.getBestLength() + ap4.getBestLength();
            }
        }
        System.out.println("The optimal subObject1 length is: " + bestSub[0]);
        System.out.print("The key activities in subObject1 is:");
        for (int i = 0; i < bestTour1.length; i++) {
            System.out.print(bestTour1[i]+"     ");
        }
        System.out.println();
        System.out.print("The earliest start time in subObject1 is:");
        for (int i = 0; i < ve1.length; i++) {
            System.out.print(ve1[i]+"     ");
        }
        System.out.println();
        System.out.print("The least start time in subObject1 is:");
        for (int i = 0; i < vl1.length; i++) {
            System.out.print(vl1[i]+"     ");
        }
        System.out.println();
        System.out.println(ra1);

        System.out.println("The optimal subObject2 length is: " + bestSub[1]);
        System.out.print("The key activities in subObject2 is:");
        for (int i = 0; i < bestTour2.length; i++) {
            System.out.print(bestTour2[i]+"     ");
        }
        System.out.println();
        System.out.print("The earliest start time in subObject1 is:");
        for (int i = 0; i < ve2.length; i++) {
            System.out.print(ve2[i]+"     ");
        }
        System.out.println();
        System.out.print("The least start time in subObject1 is:");
        for (int i = 0; i < vl2.length; i++) {
            System.out.print(vl2[i]+"     ");
        }
        System.out.println();
        System.out.println(ra2);

        System.out.println("The optimal subObject3 length is: " + bestSub[2]);
        System.out.print("The key activities in subObject3 is:");
        for (int i = 0; i < bestTour3.length; i++) {
            System.out.print(bestTour3[i]+"     ");
        }
        System.out.println();
        System.out.print("The earliest start time in subObject1 is:");
        for (int i = 0; i < ve3.length; i++) {
            System.out.print(ve3[i]+"     ");
        }
        System.out.println();
        System.out.print("The least start time in subObject1 is:");
        for (int i = 0; i < vl3.length; i++) {
            System.out.print(vl3[i]+"     ");
        }
        System.out.println();
        System.out.println(ra3);

        System.out.println("The optimal subObject4 length is: " + bestSub[3]);
        System.out.print("The key activities in subObject4 is:");
        for (int i = 0; i < bestTour4.length; i++) {
            System.out.print(bestTour4[i]+"     ");
        }
        System.out.println();
        System.out.print("The earliest start time in subObject1 is:");
        for (int i = 0; i < ve4.length; i++) {
            System.out.print(ve4[i]+"     ");
        }
        System.out.println();
        System.out.print("The least start time in subObject1 is:");
        for (int i = 0; i < vl4.length; i++) {
            System.out.print(vl4[i]+"     ");
        }
        System.out.println();
        System.out.println(ra4);

        System.out.println("The optimal mainObject length is: " + length);
        System.out.println("The expended money is: " + best);
        long end = System.currentTimeMillis() - begin;
        System.out.println("耗时：" + end + "毫秒");
        System.out.println("上层收敛用代数：" + cm + "下层收敛用代数：" + cmt);
    }


}
