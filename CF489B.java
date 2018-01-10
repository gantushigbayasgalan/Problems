import java.util.*;
import java.io.*;

public class CF489B {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("input.txt"));
//        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = input.nextInt();
        
        Arrays.sort(a);
        int[] b = new int[102];
        int girl, m = input.nextInt();
        for (int i = 0; i < m; i++){
            girl = input.nextInt();
            b[girl]++;
        }
        int numberOfPairs = 0;
        for (int i = 0; i < n; i++){
            if (b[a[i] - 1] != 0) {numberOfPairs++; b[a[i] - 1]--;}
            else if (b[a[i]] != 0) {numberOfPairs++; b[a[i]]--;}
            else if (b[a[i] + 1] != 0) {numberOfPairs++; b[a[i] + 1]--;}
        }
        System.out.println(numberOfPairs);
    }
}
