import java.util.*;

public class Problem1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    
        int n = input.nextInt();
        int[] a1 = new int[n];
        for (int i = 0; i < n; i++)
            a1[i] = input.nextInt();

        int m = input.nextInt();
        int[] a2 = new int[m];
        for (int i = 0; i < m; i++)
            a2[i] = input.nextInt();

        System.out.println(prototype(a1, a2));

    }
    static int prototype(int[] a1, int[] a2) {
        int j;
        for (int i = 0; i < a1.length; i++) {
            for (j = 0; j < a2.length; j++)
                if (a1[i] == a2[j]) break;
            if (j == a2.length) return 0;
        }

        for (int i = 0; i < a2.length; i++) {
            for (j = 0; j < a1.length; j++)
                if (a2[i] == a1[j]) break;
            if (j == a1.length) return 0;
        }
        return 1;
    }
}

