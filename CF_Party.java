package Graph;

import java.util.Scanner;

public class CF_Party{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        CF_Party party = new CF_Party();

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++)
            parent[i] = input.nextInt();

        int answer = 0;
        for (int i = 1; i <= n; i++)
            answer = Math.max(answer, party.DFS(i, parent));

        System.out.println(answer);
    }

    public int DFS(int node, int[] parent){
        if (parent[node] == -1) return 1;
        return DFS(parent[node], parent) + 1;
    }
}
