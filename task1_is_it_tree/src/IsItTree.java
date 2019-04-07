import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IsItTree {

    public static boolean dfs(int i, int matr[][], boolean color[]) {
        if (color[i]) {
            return false;
        }
        color[i] = true;
        for (int j = 0; j < color.length; ++j) {
            if (matr[i][j] != 0) {
                dfs(j, matr, color);
            }
        }
        return true;
    }


    public static boolean isItTree(int matr[][], int n) {
        int edges = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matr[i][j] != 0) {
                    edges++;
                }
            }
        }
        if (edges != n - 1) {
            return false;
        }
        boolean color[] = new boolean[n];
        dfs(0, matr, color);
        for (int i = 0; i < n; ++i) {
            if (!color[i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        int[][] matr = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matr[i][j] = in.nextInt();

        FileWriter fr = new FileWriter(new File("output.txt"));
        if (isItTree(matr, n))
            fr.write("YES");
        else
            fr.write("NO");
        fr.close();
    }
}
