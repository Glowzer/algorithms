import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MinHeap {

    public static String isHeap(int[] a, int n){
        int flag = 1;

        for (int i = n; i > 1; i--)
            if (a[i] >= a[i / 2])
                ++flag;

        return (flag == n) ? "Yes" : "No";
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        int a[] = new int[n + 1];
        for(int i = 1; i < n + 1; i++)
            a[i] = in.nextInt();

        File file = new File("output.txt");
        FileWriter fr = null;
        fr = new FileWriter(file);

        fr.write(String.valueOf(isHeap(a, n)));

        fr.close();
    }
}
