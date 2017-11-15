package Graph;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Shop {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] x = {-1, 0, 1, 0}, y = {0, 1, 0, -1};
        boolean[] isUsed = new boolean[650];
        int[] distance = new int[650];
        int w = input.nextInt(), h = input.nextInt(), answer;
        String shopMap, shopRow;
        while (w != 0 && h != 0){
            shopMap = "";
            int start = 0, finish = 0;
            for (int i = 0; i < h; i++){
                shopRow = input.next();
                shopMap += shopRow;
            }
            for (int i = 0; i < h * w; i++) {
                if (shopMap.charAt(i) == 'S') start = i;
                if (shopMap.charAt(i) == 'D') finish = i;
                isUsed[i] = false;
                distance[i] = Integer.MAX_VALUE;
            }

            PriorityQueue<Pair> queue = new PriorityQueue<Pair>();
            queue.add(new Pair(0, start));
            distance[start] = 0;

            while (!queue.isEmpty()){
                Pair pair = queue.remove();
                for (int i = 0; i < 4; i++){
                    int nowX = pair.node / w + x[i];
                    int nowY = pair.node % w + y[i];
                    int neighbor = nowX * w + nowY;
                    if (isOk(nowX, nowY, w, h) && !isUsed[neighbor]){
                        int digit = isDigit(shopMap.charAt(neighbor));
                        if (digit > -1 && distance[pair.node] + digit < distance[neighbor]){
                            distance[neighbor] = distance[pair.node] + digit;
                            queue.add(new Pair(distance[neighbor], neighbor));
                        }
                    }
                }
                isUsed[pair.node] = true;
            }
            System.out.println(distance[finish]);
            w = input.nextInt();
            h = input.nextInt();
        }
    }

    static boolean isOk(int x, int y, int w, int h){
        return (x >= 0 && x < h && y >= 0 && y < w);
    }

    static int isDigit(char ch){
        if (ch == 'D') return 0;
        if (48 <= ch && ch <= 57) return ch - 48;
        return -1;
    }

    static class Pair implements Comparable<Pair>{
        public int distance, node;

        public Pair(int distance, int node){
            this.distance = distance;
            this.node = node;
        }

        public int compareTo(Pair pair){
            if (distance > pair.distance) return 1;
            else if (distance == pair.distance) return 0;
            return -1;
        }
    }
}
