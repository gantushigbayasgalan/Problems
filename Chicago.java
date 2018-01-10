import java.util.*;
import java.io.*;

public class Chicago {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("input.txt"));
//        Scanner input = new Scanner(System.in);

        ArrayList<ArrayList<Node>> list = new ArrayList<ArrayList<Node>>();
        int n = input.nextInt(), m, a, b, p;
        double[] per = new double[101];
        boolean[] isUsed = new boolean[101];
        while (n != 0){
            m = input.nextInt();
            list.clear();
            for (int i = 0; i <= n; i++){
                list.add(new ArrayList<Node>());
                isUsed[i] = false;
                per[i] = 0.0;
            }

            for (int i = 0; i < m; i++){
                a = input.nextInt(); 
                b = input.nextInt(); 
                p = input.nextInt();
                list.get(a).add(new Node(b, p));
                list.get(b).add(new Node(a, p));
            }
            PriorityQueue<Node> q = new PriorityQueue<Node>(m, new Node());

            q.add(new Node(1, 100.0));
            per[1] = 100.0;
            while (!q.isEmpty()){
                Node node = q.remove();
                for (int i = 0; i < list.get(node.vertex).size(); i++){
                    Node to = list.get(node.vertex).get(i);
                    if (!isUsed[to.vertex] && (node.percent * to.percent) / 100.0 > per[to.vertex]){
                        per[to.vertex] = (node.percent * to.percent) / 100.0;
                        q.add(new Node(to.vertex, per[to.vertex]));
                    }
                }
                isUsed[node.vertex] = true;
            }
            System.out.printf("%.6f percent\n", per[n]);
            n = input.nextInt();
        }
    }

}

class Node implements Comparator<Node> {
    public int vertex = 0;
    public double percent = 0.0;

    public Node(int vertex, double percent){
        this.vertex = vertex;
        this.percent = percent;
    }

    public Node(){
    }

    public int compare(Node n1, Node n2){
        if (n1.percent < n2.percent) return 1;
        if (n1.percent > n2.percent) return -1;
        return 0;
    }
}
