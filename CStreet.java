package Graph;

import java.util.PriorityQueue;
import java.util.Scanner;

public class CStreet {
    static MyTree[] tree = new MyTree[1001];

    static boolean isConnected(Street street){
        return getParent(street.start) == getParent(street.finish);
    }

    static int getParent(int x){
        if (tree[x].parent != x) return getParent(tree[x].parent);
        return x;
    }

    static void unionFind(int x, int y){
        int parentX = getParent(x);
        int parentY = getParent(y);
        if (tree[parentX].rank < tree[parentY].rank) tree[parentX].parent = parentY;
        else if (tree[parentX].rank > tree[parentY].rank) tree[parentY].parent = parentX;
        else {
            tree[parentY].parent = parentX;
            tree[parentX].rank++;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numberOfTest = input.nextInt();
        PriorityQueue<Street> queue = new PriorityQueue<Street>();
        boolean[] isUsed = new boolean[1001];
        for (int i = 0; i <= 1000; i++)
            tree[i] = new MyTree();
        int count, total;
        Street street;

        for (int t = 0; t < numberOfTest; t++){
            int cost = input.nextInt();
            int numberOfBuildings = input.nextInt(), numberOfStreets = input.nextInt();

            queue.clear();
            for (int i = 1; i <= numberOfBuildings; i++) {
                isUsed[i] = false;
                tree[i].parent = i;
                tree[i].rank = 0;
            }

            for (int i = 0; i < numberOfStreets; i++){
                int a = input.nextInt(), b = input.nextInt(), c = input.nextInt();
                queue.add(new Street(a, b, c));
            }
            count = 0;
            total = 0;
            while (count < numberOfBuildings - 1){
                street = queue.remove();
                if (!isConnected(street)){
                    total += street.length;
                    unionFind(street.start, street.finish);
                    count++;
                }
            }
            System.out.println(total * cost);
        }
    }
}

class MyTree {
    public int parent, rank;
}

class Street implements Comparable<Street>{
    public int start, finish, length;

    public Street(int start, int finish, int length){
        this.start = start;
        this.finish = finish;
        this.length = length;
    }

    public int compareTo(Street s1){
        if (length > s1.length) return 1;
        else if (length == s1.length) return 0;
        return -1;
    }
}
