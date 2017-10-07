package Graph;

import java.util.*;

public class Dijkstra {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        List<ArrayList<Node>> list = new ArrayList<ArrayList<Node>>();

        int n = input.nextInt(), m = input.nextInt();
        for (int i = 0; i <= n; i++)
            list.add(new ArrayList<Node>());

        int a, b, w;
        for (int i = 0; i < m; i++){
            a = input.nextInt();
            b = input.nextInt();
            w = input.nextInt();
            list.get(a).add(new Node(b, w));
            list.get(b).add(new Node(a, w));
        }

        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        boolean[] isUsed = new boolean[n + 1];
        int[] distances = new int[n + 1];
        int[] parent = new int[n + 1];

        for (int i = 1; i <= n; i++)
            distances[i] = Integer.MAX_VALUE;


        queue.add(new Node(1, 0));
        distances[1] = 0;
        parent[1] = -1;
        while (!queue.isEmpty()){
            Node myNode = queue.remove();
            for (int i = 0; i < list.get(myNode.index).size(); i++){
                Node toNode = list.get(myNode.index).get(i);
                if (!isUsed[toNode.index] &&
                        (distances[myNode.index] + toNode.length < distances[toNode.index])){
                    distances[toNode.index] = distances[myNode.index] + toNode.length;
                    queue.add(new Node(toNode.index, distances[toNode.index]));
                    parent[toNode.index] = myNode.index;
                }
            }
            isUsed[myNode.index] = true;
        }
        if (distances[n] == Integer.MAX_VALUE) System.out.println("-1");
        else {
            int lastNode = n;
            LinkedList<Integer> answer = new LinkedList<Integer>();
            while (parent[lastNode] != -1) {
                answer.addLast(lastNode);
                lastNode = parent[lastNode];
            }
            System.out.print("1");
            while (!answer.isEmpty())
                System.out.print(" " + answer.removeLast());
            System.out.println();
        }
    }
}

class Node implements Comparable<Node>{
    public int index = 0, length = 0;

    public Node(int index, int length){
        this.index = index;
        this.length = length;
    }

    public int compareTo(Node n2){
        if (length > n2.length) return 1;
        else if (length == n2.length) return 0;
        return -1;
    }
}

