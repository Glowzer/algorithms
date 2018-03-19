import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class task21 {

    public static int[][][] Cost = new int [10][10][10];
    public static int mf, mb, mc, n;
    public static final int MAX_COUNT = 10;

    public static int[][][] initData(int[][][] Cost) throws IOException{
        for (int i = 0; i < MAX_COUNT ; i++)
            for (int j = 0; j < MAX_COUNT ; j++)
                for (int k = 0; k < MAX_COUNT ; k++)
                    Cost[i][j][k] = 0;
        Scanner in = new Scanner(new File("input.txt"));
        int m, f, b, c, s;
        n = in.nextInt();
        m = in.nextInt();
        for (int i = 0; i < m; i++) {
            f = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            s = in.nextInt();

            f = Math.min(f, 9);
            b = Math.min(b, 9);
            c = Math.min(c, 9);
            if ((Cost[f][b][c] == 0) || (s < Cost[f][b][c]))
                Cost[f][b][c] = s;
        }
        mf = in.nextInt();
        mb = in.nextInt();
        mc = in.nextInt();
        return Cost;
    }

    public static void main(String[] args) throws IOException{
        initData(Cost);
        int[][][] NotLessCost = Cost;
        int F, B, C;
        for(int i = 0; i < MAX_COUNT ; i++) {
            for (int j = 0; j < MAX_COUNT ; j++) {
                for (int k = 0; k < MAX_COUNT ; k++) {
                    if (NotLessCost[i][j][k] != 0) {
                        for (int l = 0; l < MAX_COUNT ; l++) {
                            for (int m = 0; m < MAX_COUNT ; m++) {
                                for (int n = 0; n < MAX_COUNT ; n++) {
                                    if (Cost[l][m][n] != 0) {
                                        F = Math.min(i + l, 9);
                                        B = Math.min(j + m, 9);
                                        C = Math.min(k + n, 9);
                                        if (NotLessCost[F][B][C] == 0 || (NotLessCost[F][B][C] > Cost[l][m][n] + NotLessCost[i][j][k]))
                                            NotLessCost[F][B][C] = Cost[l][m][n] + NotLessCost[i][j][k];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        int minPay = Integer.MAX_VALUE;

        for(int i = mf; i < MAX_COUNT ; i++) {
            for (int j = mb; j < MAX_COUNT ; j++) {
                for (int k = mc; k < MAX_COUNT ; k++) {
                    if(NotLessCost[i][j][k] != 0)
                        if(NotLessCost[i][j][k] < minPay)
                        minPay = NotLessCost[i][j][k];
                }
            }
        }
        double result = minPay;
        result = Math.ceil(result / n);
        String res = Integer.toString((int)result);
        File file = new File("output.txt");
        FileWriter fr = null;
        fr = new FileWriter(file);
        fr.write(res);
        fr.close();
    }
}
