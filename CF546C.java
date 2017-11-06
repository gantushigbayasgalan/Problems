package Graph;

import java.util.*;

public class CF546C {
    static Map<LinkedList<Integer>, LinkedList<Integer>> mp
            = new LinkedHashMap<LinkedList<Integer>, LinkedList<Integer>>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        LinkedList<Integer> soldierOne = new LinkedList<Integer>();
        LinkedList<Integer> soldierTwo = new LinkedList<Integer>();

        int n = input.nextInt();
        int k1 = input.nextInt();
        for (int i = 0; i < k1; i++)
            soldierOne.addLast(input.nextInt());

        int k2 = input.nextInt();
        for (int i = 0; i < k2; i++)
            soldierTwo.addLast(input.nextInt());

        Pair pair = DFS(soldierOne, soldierTwo, 0);
        if (pair.a == -1) System.out.println("-1");
        else System.out.println(pair.a + " " + pair.b);
    }


    static Pair DFS(LinkedList<Integer> soldierOne, LinkedList<Integer> soldierTwo, int round){
        if (soldierOne.size() == 0) return new Pair(round, 2);
        if (soldierTwo.size() == 0) return new Pair(round, 1);

        LinkedList<Integer> list = mp.get(soldierOne);
        if (list != null && list.equals(soldierTwo)) return new Pair(-1, -1);
        else{
            mp.put(new LinkedList<Integer>(soldierOne), new LinkedList<Integer>(soldierTwo));
            int f1 = soldierOne.removeFirst(), f2 = soldierTwo.removeFirst();
            if (f1 > f2) {
                soldierOne.addLast(f2);
                soldierOne.addLast(f1);
            } else{
                soldierTwo.addLast(f1);
                soldierTwo.addLast(f2);
            }
            round++;
            return DFS(soldierOne, soldierTwo, round);
        }
    }

    static class Pair{
        public int a, b;

        public Pair(int a, int b){
            this.a = a;
            this.b = b;
        }
    }
}
