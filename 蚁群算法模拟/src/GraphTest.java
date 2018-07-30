import java.io.IOException;

public class GraphTest {

    public static void main(String[] args) throws IOException {

        /**
         * 测试没有回路的情况 
         *
         */
        System.out.println("<!--------测试没有回路的情况-------------->");
        Graph graph = new Graph(9);
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.add("d");
        graph.add("e");
        graph.add("f");
        graph.add("g");
        graph.add("h");
        graph.add("i");
        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 4);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 5, 2);
        graph.addEdge(4, 6, 9);
        graph.addEdge(4, 7, 7);
        graph.addEdge(5, 7, 4);
        graph.addEdge(6, 8, 2);
        graph.addEdge(7, 8, 4);
        if (graph.topo()) {
            graph.calculate();

            int[] e = graph.getE();
            int[] l = graph.getL();
            int[] point = graph.getPoint();
            int[] ve = graph.getVE();
            int[] vl = graph.getVl();
            int[] key = graph.getKey();

            System.out.println("事件的最早发生时间：");
            for (int w = 0; w < ve.length; w++) {
                System.out.print(ve[w] + "      ");
            }
            System.out.println();

            System.out.println("事件的最晚发生时间：");
            for (int w = 0; w < vl.length; w++) {
                System.out.print(vl[w] + "      ");
            }
            System.out.println();

            System.out.println("活动的最早发生时间：");
            for (int w = 0; w < e.length; w++) {
                System.out.print(e[w] + "      ");
            }

            System.out.println();
            System.out.println("活动的最迟发生时间：");
            for (int w = 0; w < l.length; w++) {
                System.out.print(l[w] + "      ");
            }

            System.out.println();

            System.out.println("关键活动有：");
            for (int w = 0; w < graph.getPNum(); w++) {
                System.out.print(point[w] + "      ");
            }
            System.out.println("关键活动有：");
            for (int w = 0; w < graph.getKNum(); w++) {
                System.out.print(key[w] + "      ");
            }
            System.out.println();



            /**
             * 测试有回路的情况 
             *
             */
            System.out.println("<!--------测试没有回路的情况-------------->");
            graph = new Graph(9);
            graph.add("a");
            graph.add("b");
            graph.add("c");
            graph.add("d");
            graph.add("e");
            graph.add("f");
            graph.add("g");
            graph.add("h");
            graph.add("i");
            graph.addEdge(0, 1, 6);
            graph.addEdge(2, 0, 4);
            graph.addEdge(0, 3, 5);
            graph.addEdge(1, 4, 1);
            graph.addEdge(4, 2, 1);
            graph.addEdge(3, 5, 2);
            graph.addEdge(4, 6, 9);
            graph.addEdge(4, 7, 7);
            graph.addEdge(5, 7, 4);
            graph.addEdge(6, 8, 2);
            graph.addEdge(7, 8, 4);

            if (graph.topo()) {
                graph.calculate();

                int[] e1 = graph.getE();
                int[] l1 = graph.getL();
                int[] key1 = graph.getKey();
                int[] ve1 = graph.getVE();
                int[] vl1 = graph.getVl();

                System.out.println("事件的最早发生时间：");
                for (int w = 0; w < ve.length; w++) {
                    System.out.print(ve[w] + "      ");
                }
                System.out.println();

                System.out.println("事件的最晚发生时间：");
                for (int w = 0; w < vl.length; w++) {
                    System.out.print(vl[w] + "      ");
                }
                System.out.println();

                System.out.println("活动的最早发生时间：");
                for (int w = 0; w < e.length; w++) {
                    System.out.print(e[w] + "      ");
                }

                System.out.println();
                System.out.println("活动的最迟发生时间：");
                for (int w = 0; w < l.length; w++) {
                    System.out.print(l[w] + "      ");
                }

                System.out.println();

                System.out.println("关键活动有：");
                for (int w = 0; w < graph.getKNum(); w++) {
                    System.out.print(key[w] + "      ");
                }
            }

        }

    }

}  