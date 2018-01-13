import java.util.*;
import java.io.*;

public class Buglife {
    private static ArrayList<Integer>[] list = new ArrayList[1001];
    private static int[] color = new int[1001];

    public static void main(String[] args) throws IOException {
        Reader input = new Reader("input.txt");
        int t = input.nextInt();

        boolean isOk;
        for (int i = 0; i <= 1000; i++)
            list[i] = new ArrayList<Integer>();

        for (int q = 1; q <= t; q++){
            int bugs = input.nextInt(), interactions = input.nextInt();
            for (int i = 0; i <= bugs; i++){
                list[i].clear();
                color[i] = 0;
            }

            System.out.println("Scenario #" + q + ":");
            for (int i = 0; i < interactions; i++){
                int x = input.nextInt(), y = input.nextInt();
                list[x].add(y);
                list[y].add(x);
            }
            isOk = true;
            for (int i = 1; i <= bugs; i++){
                if (color[i] == 0 && !DFS(i, 1)) {
                    isOk = false;
                    break;
                } 
            }
            if (isOk) System.out.println("No suspicious bugs found!");
            else System.out.println("Suspicious bugs found!");
        }
    }

    public static boolean DFS(int vertex, int currentColor){
        if (color[vertex] != 0) return (color[vertex] == currentColor);
        color[vertex] = currentColor;
        int nextColor = (currentColor == 1 ? 2 : 1);
        for (int i = 0; i < list[vertex].size(); i++)
            if (!DFS(list[vertex].get(i), nextColor)) return false;
        return true;
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
