import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HashTable {

    public static int h(long x, int i, long m, int c) {
        return ((x % m) + c * i) % m;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        int m = in.nextInt();
        int c = in.nextInt();
        int n = in.nextInt();
        long[] hashTable = new long[m];
        for (int i = 0; i < m; i++)
            hashTable[i] = -1;
        for (long i = 0; i < n; i++){
            long val = in.nextLong();
            for (int j = 0; j < m; j++){
                if(hashTable[h(val, j, m, c)] == -1) {
                    hashTable[h(val, j, m, c)] = val;
                    break;
                }
            }
        }
        FileWriter fr = new FileWriter(new File("output.txt"));
        for (int i = 0; i < m; i++)
           fr.write(hashTable[i] + " ");
        fr.close();
    }
}
