package Graph;

//Spoj

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Ppath {
    private boolean[] isNotPrime = new boolean[10001];
    private ArrayList<Integer> answer = new ArrayList<Integer>();

    public Ppath(int[] a, int[] b){
        for (int i = 2; i <= 100; i++){
            int q = 10000 / i;
            if (!isNotPrime[i]) {
                for (int j = 2; j <= q; j++)
                    isNotPrime[i * j] = true;
            }
        }
        for (int i = 0; i < 1000; i++)
            isNotPrime[i] = true;

        Queue<PrimeAndLength> queue = new LinkedList<PrimeAndLength>();
        for (int i = 0; i < a.length; i++){
            if (a[i] == b[i]) {answer.add(0);continue;}
            queue.clear();

            boolean[] isUsed = new boolean[10000];
            queue.offer(new PrimeAndLength(a[i], 0));
            isUsed[a[i]] = true;
            int[] d = new int[4];

            while (!queue.isEmpty()){
                PrimeAndLength number = queue.remove();
                d[0] = number.prime / 1000; d[1] = number.prime / 100 % 10;
                d[2] = number.prime / 10 % 10;
                d[3] = number.prime % 10;
                int digit, p = 10000;
                for (int j = 0; j < 4; j++){
                    digit = d[j];
                    p /= 10;
                    d[j] = 0;
                    int newNumber = getNumber(d);
                    for (int q = 0; q < 10; q++){
                        if (!isUsed[newNumber] && !isNotPrime[newNumber]){
                            isUsed[newNumber] = true;
                            queue.add(new PrimeAndLength(newNumber, number.length + 1));
                        }
                        newNumber += p;
                    }
                    d[j] = digit;
                }
                if (isUsed[b[i]]) {answer.add(number.length + 1); break;}
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int t = input.nextInt();
        int[] a = new int[100], b = new int[100];
        for (int i = 0; i < t; i++){
            a[i] = input.nextInt();
            b[i] = input.nextInt();
        }

        Ppath ppath = new Ppath(a, b);
        for (int i = 0; i < t; i++)
            System.out.println(ppath.answer.get(i));
    }

    private int getNumber(int d[]){
        return d[0] * 1000 + d[1] * 100 + d[2] * 10 +d[3];
    }

    class PrimeAndLength {
        private int prime, length;

        public PrimeAndLength(int prime, int length){
            this.prime = prime;
            this.length = length;
        }
    }

}
