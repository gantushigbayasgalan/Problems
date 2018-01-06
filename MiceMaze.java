import java.util.*;
import java.io.*;

public class MiceMaze {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(new File("input.txt"));
        
//        Scanner input = new Scanner(System.in);
        int N = input.nextInt(), E = input.nextInt(), T = input.nextInt();
        E--;

        boolean[] isUsed = new boolean[N];
        int[] dest = new int[N];
        List<ArrayList<Node>> list = new ArrayList<ArrayList<Node>>();

        for (int i = 0; i < N; i++)
            list.add(new ArrayList<Node>());

        int M = input.nextInt();
        for (int i = 0; i < M; i++){
            int a = input.nextInt(), b = input.nextInt(), t = input.nextInt();
            a--; b--;
            list.get(b).add(new Node(a, t));
        }

        PriorityQueue<Node> q = new PriorityQueue<Node>(N, new Node());
        for (int j = 0; j < N; j++){
            isUsed[j] = false;
            dest[j] = Integer.MAX_VALUE;
        }
        q.add(new Node(E, 0));
        dest[E] = 0;
        while (!q.isEmpty()){
            Node node = q.remove();
            for (int j = 0; j < list.get(node.index).size(); j++){
                Node toNode = list.get(node.index).get(j);
                if (!isUsed[toNode.index] && dest[node.index] + toNode.time < dest[toNode.index] &&
                        toNode.index != node.index){
                            dest[toNode.index] = dest[node.index] + toNode.time;
                            q.add(new Node(toNode.index, dest[toNode.index]));
                        }
            }
            isUsed[node.index] = true;
        }

        int answer = 0;
        for (int i = 0; i < N; i++)
            if (dest[i] <= T) answer++;

        System.out.println(answer);
    }
}

class Node implements Comparator<Node>{
    public int index;
    public int time;

    public Node(){
    }

    public Node(int index, int time){
        this.index = index;
        this.time = time;
    }

    public int compare(Node node1, Node node2){
        if (node1.time > node2.time) return 1;
        if (node1.time < node2.time) return -1;
        return 0;
    }
}
