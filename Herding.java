import java.util.*;
import java.io.*;

public class Herding {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(new File("input.txt"));

//        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();
        String[] map = new String[n];
        int[][] isVisit = new int[n][m];

        for (int i = 0; i < n; i++)
            map[i] = input.next();

        int answer = 0, counter = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (isVisit[i][j] == 0){
                    counter++;
                    if (!DFS(i, j, isVisit, map, counter)) answer++;
                }
            }
        }
        System.out.println(answer);
    }
    static boolean DFS(int x, int y, int[][] isVisit, String[] map, int counter){
        isVisit[x][y] = counter;
        char ch = map[x].charAt(y);
        if (ch == 'S') x++;
        else if (ch == 'W') y--;
        else if (ch == 'E') y++;
        else x--;
        if (isVisit[x][y] == 0) return DFS(x, y, isVisit, map, counter);
        else if (isVisit[x][y] == counter) return false;
        return true;
    }
}
