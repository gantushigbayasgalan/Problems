package Graph;
//Spoj

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bitmap {
    private Queue<Node> queue = new LinkedList<Node>();
    private int[] xPlus = {-1, 0, 1, 0}, yPlus = {0, 1, 0, -1};
    private String[] map;

    public Bitmap(){
    }

    public void setMap(String[] map){
        this.map = map;
    }

    public int[][] solve(){
        int[][] answer = new int[map.length][map[0].length()];

        queue.clear();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length(); j++) {
                if (map[i].charAt(j) == '1') {
                    queue.add(new Node(i, j));
                    answer[i][j] = 1;
                }
            }
        }

        while (!queue.isEmpty()){
            Node node = queue.remove();
            for (int i = 0; i < 4; i++){
                int xNew = node.x + xPlus[i];
                int yNew = node.y + yPlus[i];
                if (isOk(xNew, yNew) && answer[xNew][yNew] == 0){
                    answer[xNew][yNew] = answer[node.x][node.y] + 1;
                    queue.add(new Node(xNew, yNew));
                }
            }
        }
        return answer;
    }

    private boolean isOk(int xNew, int yNew){
        return (xNew >= 0 && yNew >= 0 && xNew < map.length && yNew < map[0].length());
    }

    class Node{
        private int x, y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Bitmap bitmap = new Bitmap();
        int t = input.nextInt();
        for (int q = 0; q < t; q++){
            int n = input.nextInt(), m = input.nextInt();
            String[] map = new String[n];
            for (int j = 0; j < n; j++)
                map[j] = input.next();

            bitmap.setMap(map);
            int[][] answer = bitmap.solve();
            for (int i = 0; i < answer.length; i++){
                for (int j = 0;j < answer[i].length; j++)
                    System.out.print(answer[i][j] - 1 + " ");
                System.out.println();
            }
        }

    }
}
