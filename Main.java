package Graph;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
    static private int count;

    static void DFS(int x, List<ArrayList<Integer>> list, boolean[] isUsed){
        for (int i = 0; i < list.get(x).size(); i++){
            int newNode = list.get(x).get(i);
            if (!isUsed[newNode]){
                count++;
                isUsed[newNode] = true;
                DFS(newNode, list, isUsed);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader lineInput = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/Graph/input.txt")));

        String line;

        try {
            line = lineInput.readLine();
            Scanner input = new Scanner(line);
            int n = input.nextInt();
            List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
            while (n != 0){
                list.clear();
                for (int i = 0; i < n; i++)
                    list.add(new ArrayList<Integer>());

                line = lineInput.readLine();
                input = new Scanner(line);
                int parent = input.nextInt() - 1;
                while (parent != -1){
                    while (input.hasNextInt()){
                        int node = input.nextInt() - 1;
                        list.get(parent).add(node);
                        list.get(node).add(parent);
                    }
                    line = lineInput.readLine();
                    input = new Scanner(line);
                    parent = input.nextInt() - 1;
                }
                int answer = 0;
                for (int i = 0; i < n; i++){
                    count = 1;
                    int startNode = i + 1;
                    if (startNode == n) startNode = 0;
                    boolean[] isUsed = new boolean[n];

                    isUsed[startNode] = true;
                    isUsed[i] = true;
                    DFS(startNode, list, isUsed);
                    if (count < n - 1) answer++;
                }
                System.out.println(answer);
                line = lineInput.readLine();
                input = new Scanner(line);
                n = input.nextInt();
            }

        }catch (Exception e){
        }
    }
}
