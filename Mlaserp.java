import java.util.*;
import java.io.*;

public class Mlaserp {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("input.txt"));
        ArrayList<Pair> pairs = new ArrayList<Pair>();

        int m = input.nextInt(), n = input.nextInt();
        String[] map = new String[n];
        int[][] mirrors = new int[n][m];
        int[] xPlus = {0, 1, 0, -1}, yPlus = {1, 0, -1, 0};

        for (int i = 0; i < n; i++){
            map[i] = input.next();
            for (int j = 0; j < m; j++){
                mirrors[i][j] = 0;
                if (map[i].charAt(j) == 'C') pairs.add(new Pair(i, j));
                if (map[i].charAt(j) == '*') mirrors[i][j] = -1;
            }
        }
        int firstCowX = pairs.get(0).x, firstCowY = pairs.get(0).y;
        int secondCowX = pairs.get(1).x, secondCowY = pairs.get(1).y;
        
        Queue<Pair> q = new LinkedList<Pair>();
        q.offer(new Pair(firstCowX, firstCowY));
        mirrors[firstCowX][firstCowY] = 1;

        Pair current, moving;
        while (!q.isEmpty()){
            current = q.remove();
            for (int i = 0; i < 4; i++){
                moving = new Pair(current.x, current.y);
                moving.x += xPlus[i];
                moving.y += yPlus[i];
                while (isOnMap(n, m, moving) && mirrors[moving.x][moving.y] != -1) {
                    if (mirrors[moving.x][moving.y] == 0) {
                        q.offer(new Pair(moving.x, moving.y));
                        mirrors[moving.x][moving.y] = mirrors[current.x][current.y] + 1;
                    }
                    moving.x += xPlus[i];
                    moving.y += yPlus[i];
                }
            }
            if (mirrors[secondCowX][secondCowY] != 0) break;
        }
/*        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++)
                System.out.print("\t" + mirrors[i][j]);
            System.out.println();
        }*/
        System.out.println(mirrors[secondCowX][secondCowY] - 2);
    }
    public static boolean isOnMap(int n, int m, Pair moving){
        return (moving.x >= 0 && moving.x < n && moving.y >= 0 && moving.y < m);
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}
