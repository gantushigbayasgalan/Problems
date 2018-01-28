import java.util.*;
import java.io.*;

public class Pour1 {
    private static int a, b, c;

    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(new File("input.txt"));
        int t = input.nextInt();

        while (t-- > 0){
            a = input.nextInt(); 
            b = input.nextInt(); 
            c = input.nextInt();
            if ((c > a && c > b) || (c % gcd(a, b) != 0)) {
                System.out.println("-1");
                continue;
            }
            int a1 = DFS(a, 0, 1);
            swap();
            int a2 = Math.min(DFS(a, 0, 1), a1);
            System.out.println(a2);
        }
    }
    public static int DFS(int x, int y, int z){
        if (x == c || y == c) return z;
        if (y == b) return DFS(x, 0, z + 1);
        else if (x == 0) return DFS(a, y, z + 1);
        else if (y + x < b) return DFS(0, x + y, z + 1);
        return DFS(x + y - b, b, z + 1);
    }

    public static void swap(){
        int z = a;
        a = b;
        b = z;
    }
    public static int gcd(int x, int y){
        return (y == 0? x : gcd(y, x % y));
    }
}
