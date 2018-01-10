import java.util.*;
import java.io.*;

public class Ucv2018h {
    private int[] xPlus = {0, 1, 0, -1}, yPlus = {1, 0, -1, 0};
    private int numberOfSlicks;
    private int size;

    public Ucv2018h(Scanner input){
        Map<Integer, Integer> mp = new TreeMap<Integer, Integer>();

        int n = input.nextInt(), m = input.nextInt();
        int[][] map = new int[250][250];
        while (n != 0 || m != 0){
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    map[i][j] = input.nextInt();

            numberOfSlicks = 0;
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (map[i][j] == 1){
                        numberOfSlicks++;
                        size = 0;
                        DFS(map, i, j);
                        if (mp.containsKey(size)) mp.put(size, mp.get(size) + 1);
                            else mp.put(size, 1);
                    }
                }
            }
            System.out.println(numberOfSlicks);
            for (Map.Entry<Integer, Integer> entry: mp.entrySet())
                System.out.println(entry.getKey() + " " + entry.getValue());

            mp.clear();
            n = input.nextInt();
            m = input.nextInt();
        }
    }

    private void DFS(int[][] map, int x, int y){
        map[x][y]++;
        size++;
        int xNew, yNew;
        for (int i = 0; i < 4; i++){
            xNew = x + xPlus[i];
            yNew = y + yPlus[i];
            if (isClear(xNew, yNew, map.length, map[0].length) && map[xNew][yNew] == 1) DFS(map, xNew, yNew);
        }
    }

    private boolean isClear(int x, int y, int n, int m){
        return (x >= 0 && x < n && y >= 0 && y < m);
    }

    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(new File("input.txt"));
//        Scanner input = new Scanner(System.in);

        Ucv2018h problem = new Ucv2018h(input);
    }
}
