import java.util.*;
import java.io.*;

public class Labyr1 {
    private static int[] xPlus = {0, 1, 0, -1};
    private static int[] yPlus = {1, 0, -1, 0};

    public static void main(String[] args) throws FileNotFoundException{
     //   Scanner input = new Scanner(System.in);
        Scanner input = new Scanner(new File("input.txt"));

        int t = input.nextInt(), r, c, maxLength, xStart = 0, yStart = 0;
        String line;
        boolean[][] map = new boolean[1000][1000];
        boolean[][] copyMap = new boolean[1000][1000];
        while (t-- > 0){
            c = input.nextInt();
            r = input.nextInt();
            for (int i = 0; i < r; i++){
                line = input.next();
                for (int j = 0; j < c; j++){
                    map[i][j] = false;
                    if (line.charAt(j) == '#') map[i][j] = true;
                    else {
                        xStart = i;
                        yStart = j;
                    }
                }
            }

            copyMap = deepCopy(map);
            Block block = DFS(copyMap, xStart, yStart, 0);
            block = DFS(deepCopy(map), block.row, block.column, 0);

            System.out.println("Maximum rope length is " + block.len + ".");
        }

    }

    static boolean[][] deepCopy(boolean[][] map){
        boolean[][] result = new boolean[map.length][];
        for (int i = 0; i < map.length; i++)
            result[i] = Arrays.copyOf(map[i], map[i].length);

        return result;
    }

    static Block DFS(boolean[][] map, int x, int y, int l){
        map[x][y] = true;
        int xNew, yNew;
        Block max = new Block(x, y, l);
        Block returnedBlock;
        for (int i = 0; i < 4; i++){
            xNew = x + xPlus[i];
            yNew = y + yPlus[i];
            if (isOnMap(map.length, map[0].length, xNew, yNew) && !map[xNew][yNew]){
                returnedBlock =  DFS(map, xNew, yNew, l + 1);
                if (returnedBlock.len > max.len) max = returnedBlock;
            }
        }
        return max;
    }

    static boolean isOnMap(int n, int m, int x, int y){
        return (0 <= x && x < n && 0 <= y && y < m);
    }

    static class Block {
        public int row, column, len;

        public Block(int row, int column, int len){
            this.row = row;
            this.column = column;
            this.len = len;
        }
    }
}

