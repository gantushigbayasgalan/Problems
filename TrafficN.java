import java.util.*;
import java.io.*;

public class TrafficN {
    private static ArrayList<Node>[] A = new ArrayList[10001];
    private static ArrayList<Node>[] B = new ArrayList[10001];
    private static PriorityQueue<Node> q = new PriorityQueue<Node>(10001, new Node());
    private static int[] dest = new int[10001];
    private static boolean[] isUsed = new boolean[10001];

    public static void main(String[] args) throws IOException{

        Reader input = new Reader("input.txt");

        for (int i = 0; i <= 10000; i++){
            A[i] = new ArrayList<Node>();
            B[i] = new ArrayList<Node>();
        }

        int T = input.nextInt(), n, m, k, s, t, di, ci, li, uj, vj, qj;
        Node startNode, toNode;
        while (T-- > 0){
            n = input.nextInt();
            m = input.nextInt();
            k = input.nextInt();
            s = input.nextInt();
            t = input.nextInt();
            for (int i = 0; i <= n; i++){
                A[i].clear();
                B[i].clear();
                dest[i] = Integer.MAX_VALUE;
                isUsed[i] = false;
            }
            for (int i = 0; i < m; i++){
                di = input.nextInt();
                ci = input.nextInt();
                li = input.nextInt();
                A[di].add(new Node(ci, li));
            }
            for (int i = 0; i < k; i++){
                uj = input.nextInt();
                vj = input.nextInt();
                qj = input.nextInt();
                B[uj].add(new Node(vj, qj));
                B[vj].add(new Node(uj, qj));
            }   
            q.add(new Node(s, 0));
            dest[s] = 0;
            while (!q.isEmpty()){
                startNode = q.remove();
                for (int i = 0; i < A[startNode.index].size(); i++){
                    toNode = A[startNode.index].get(i);
                    if (!isUsed[startNode.index] && startNode.cost + toNode.cost < dest[toNode.index]){
                        dest[toNode.index] = startNode.cost + toNode.cost;
                        q.add(new Node(toNode.index, dest[toNode.index]));
                    }
                }
                if (!startNode.twoWay){
                    for (int i = 0; i < B[startNode.index].size(); i++){
                        toNode = B[startNode.index].get(i);
                        if (!isUsed[startNode.index] && startNode.cost + toNode.cost < dest[toNode.index]){
                            dest[toNode.index] = startNode.cost + toNode.cost;
                            q.add(new Node(toNode.index, dest[toNode.index], true));
                        }
                    }
                }
                isUsed[startNode.index] = true;
            }
            if (dest[t] == Integer.MAX_VALUE) System.out.println("-1");
            else System.out.println(dest[t]);
        }
    }
}

class Node implements Comparator<Node>{
    public int index = 0;
    public int cost = 0;
    public boolean twoWay = false;

    public Node(){
    }

    public Node(int index, int cost){
        this.index = index;
        this.cost = cost;
    }

    public Node(int index, int cost, boolean twoWay){
        this.index = index;
        this.cost = cost;
        this.twoWay = twoWay;
    }

    public int compare(Node n1, Node n2){
        if (n1.cost > n2.cost) return 1;
        if (n1.cost < n2.cost) return -1;
        return 0;
    }
}

class Reader {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader(){
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException {
        din = new DataInputStream(new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
        byte[] buf = new byte[64];
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n') break;
            buf[cnt++] = (byte)c;
        }
        return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException{
        byte c = read();
        int ret = 0;

        while (c <= ' ')
            c = read();

        boolean neg = (c == '-');
        if (neg) c = read();
        do{
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg) return -ret;
        return ret;
    }

    public int nextLong() throws IOException{
        byte c = read();
        int ret = 0;

        while (c <= ' ')
            c = read();

        boolean neg = (c == '-');
        if (neg) c = read();
        do{
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg) return -ret;
        return ret;
    }

    public double nextDouble() throws IOException {
        double ret = 0.0, div = 1;
        byte c = read();
        while (c <= ' ')
            c = read();

        boolean neg = (c == '-');
        if (neg) c = read();

        do{
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9')
                ret += (c - '0') / (div *= 10);
        }

        if (neg) return -ret;
        return ret;
    }

    private void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) buffer[0] = -1;
    }

    private byte read() throws IOException {
        if (bufferPointer == bytesRead) fillBuffer();
        return buffer[bufferPointer++];
    }

    private void close() throws IOException {
        if (din == null) return;
        din.close();
    }
}
