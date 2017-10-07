package Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by gantushig on 27.09.17. Spoj-OneZero
 */

public class OneZero {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        Queue<NumberAndString> queue;
        boolean[] check = new boolean[20000];

        int t = input.nextInt();
        for (int i = 0; i < t; i++){
            int n = input.nextInt();

            for (int q = 0; q < n; q++)
                check[q] = false;

            queue = new LinkedList<NumberAndString>();
            queue.add(new NumberAndString(1 % n, "1"));
            check[1] = true;
            while (!queue.isEmpty()){
                NumberAndString start = queue.remove();
                if (start.num == 0){
                    System.out.println(start.str);
                    queue.clear();
                } else{
                    if (!check[start.num * 10 % n]) {
                        queue.add(new NumberAndString(start.num * 10 % n, start.str + "0"));
                        check[start.num * 10 % n] = true;
                    }
                    if (!check[(start.num * 10 + 1) % n ]) {
                        queue.add(new NumberAndString((start.num * 10 + 1) % n, start.str + "1"));
                        check[(start.num * 10 + 1) % n ] = true;
                    }
                }
            }
        }
    }
    static class NumberAndString {
        private int num;
        private String str;

        public NumberAndString(int num, String str){
            this.num = num;
            this.str = str;
        }
    }
}
