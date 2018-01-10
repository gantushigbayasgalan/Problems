import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class AbcPath {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(new File("input.txt"));
//        Scanner input = new Scanner(System.in);
//
        int h = input.nextInt(), w = input.nextInt();
        int[] xPlus = {0, 1, 1, 1, 0, -1, -1, -1}, yPlus = {1, 1, 0, -1, -1, -1, 0, 1};
        String[] map = new String[50];
        Queue<XyAndLength> q = new LinkedList<XyAndLength>();
        int testNumber = 0;
        boolean[][] isVisited = new boolean[50][50];

        while (h != 0 || w != 0){
            testNumber++;
            XyAndLength node = new XyAndLength(0, 0, 0);
            q.clear();
            for (int i = 0; i < h; i++){
                map[i] = input.next();
                for (int j = 0; j < w; j++){
                    isVisited[i][j] = false;
                    if (map[i].charAt(j) == 'A') q.offer(new XyAndLength(i, j, 1));
                }
            }
            while (!q.isEmpty() && node.length < 26){
                node = q.remove();
                for (int i = 0; i < 8; i++){
                    int xNew = node.x + xPlus[i];
                    int yNew = node.y + yPlus[i];
                    if (isClear(xNew, yNew, h, w) && (int)map[xNew].charAt(yNew) == node.length + 65 
                            && !isVisited[xNew][yNew]){
                        q.offer(new XyAndLength(xNew, yNew, node.length + 1));
                        isVisited[xNew][yNew] = true;
                    }
                }
            }
            System.out.println("Case " + testNumber + ": " + node.length);

            h = input.nextInt();
            w = input.nextInt();
        }
    }

    private static boolean isClear(int xNew, int yNew, int h, int w){
        return (xNew > -1 && xNew < h && yNew > -1 && yNew < w);
    }

    private static class XyAndLength{
        public int x, y, length;

        public XyAndLength(int x, int y, int length){
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }
}
