import java.io.*;
import java.util.*;

public class MatrixChain {

    public static int multiplyOrder(int[] p) {
        int n = p.length - 1;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][i] = 0;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        try {
            String line = "";
            Scanner in = new Scanner(new File("input.txt"));
            while (in.hasNextLine())
                line += in.nextLine() + " ";
            String[] s = line.split(" ");
            int N = Integer.parseInt(s[0]);

            int a[] = new int[N + 1];
            for(int i = 1, j = 0; i < s.length; j++, i += 2){
                a[j] = Integer.parseInt(s[i]);
            }
            a[N] = Integer.parseInt(s[s.length - 1]);
            File file = new File("output.txt");
            FileWriter fr = null;
            try {
                fr = new FileWriter(file);
                fr.write(String.valueOf(multiplyOrder(a)));
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (FileNotFoundException e){
        System.out.println("File not find");}

    }
}
