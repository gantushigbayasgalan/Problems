package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Benefact {
    public List<ArrayList<Pair>> list = new ArrayList<ArrayList<Pair>>();

    public Benefact(){
    }

    public void clearArray(int n){
        list.clear();
        for (int i = 0; i < n; i++)
            list.add(new ArrayList<Pair>());
    }

    public void connectAB(int a, int b, int l){
        list.get(a).add(new Pair(b, l));
        list.get(b).add(new Pair(a, l));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numberOfTest = input.nextInt();
        Benefact myClass = new Benefact();

        for (int t = 0; t < numberOfTest; t++){

            int n = input.nextInt();
            myClass.clearArray(n);

            for (int i = 1; i < n; i++){
                int a = input.nextInt() - 1, b = input.nextInt() - 1, l = input.nextInt();
                myClass.connectAB(a, b, l);
            }
            Pair longestNode = myClass.DFS(0, -1);
            longestNode = myClass.DFS(longestNode.node, -1);
            System.out.println(longestNode.length);
        }
    }

    public Pair DFS(int x, int parent){
        if (list.get(x).size() == 1 && parent != -1) return new Pair(x, 0);
        else{
            Pair ln = new Pair(0, 0);
            for (int i = 0; i < list.get(x).size(); i++){
                if (list.get(x).get(i).node != parent){
                    Pair longestChild = DFS(list.get(x).get(i).node, x);
                    if (ln.length < longestChild.length + list.get(x).get(i).length){
                        ln.length = longestChild.length + list.get(x).get(i).length;
                        ln.node = longestChild.node;
                    }
                }
            }
            return ln;
        }
    }
}

class Pair {
    public int node = 0, length = 0;

    public Pair(int node, int length){
        this.node = node;
        this.length = length;
    }
}
