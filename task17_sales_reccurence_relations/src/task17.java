import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class task17 {
//    public static int[][][] Cost = new int [10][10][10];
//    public static int mf, mb, mc, n;
        public static final int MAX_COUNT = 5;
//
//    public static int[][][] initData(int[][][] Cost) throws IOException {
//        for (int i = 0; i < MAX_COUNT ; i++)
//            for (int j = 0; j < MAX_COUNT ; j++)
//                for (int k = 0; k < MAX_COUNT ; k++)
//                    Cost[i][j][k] = 0;
//        Scanner in = new Scanner(new File("input.txt"));
//        int m, f, b, c, s;
//        n = in.nextInt();
//        m = in.nextInt();
//        for (int i = 0; i < m; i++) {
//            f = in.nextInt();
//            b = in.nextInt();
//            c = in.nextInt();
//            s = in.nextInt();
//
//            f = Math.min(f, 9);
//            b = Math.min(b, 9);
//            c = Math.min(c, 9);
//            if ((Cost[f][b][c] == 0) || (s < Cost[f][b][c]))
//                Cost[f][b][c] = s;
//        }
//        mf = in.nextInt();
//        mb = in.nextInt();
//        mc = in.nextInt();
//        return Cost;
//    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new File("input.txt"));
        int b = in.nextInt();
        int[] code = new int[MAX_COUNT];
        int[] amount = new int[MAX_COUNT];
        int[] cost = new int[MAX_COUNT - 1];
        for(int i = 0; i < b; i++){
            code[i] = in.nextInt();
            amount[i] = in.nextInt();
            cost[i] = in.nextInt();
        }
        int s = in.nextInt();
        int[][] O = new int [b + s][5];
        for(int i = 0; i < b; i++)
            O[i][i] = 1;
        for(int i = 0; i < s; i++){
            int count = in.nextInt();
            for(int j = 0; j < count; j++){
                for(int k = j; k < code.length; k++){
                    if(in.nextInt() == code[k]) {
                        O[i + b][j] = in.nextInt();
                        break;
                    }
                }
            }
            cost[b + i] = in.nextInt();
        }

        int[][][][][] R = new int[MAX_COUNT][MAX_COUNT][MAX_COUNT][MAX_COUNT][MAX_COUNT];
        for (int i = 0; i < MAX_COUNT; i++)
            for (int j = 0; j < MAX_COUNT; j++)
                for (int k = 0; k < MAX_COUNT; k++)
                    for (int l = 0; l < MAX_COUNT; l++)
                        for (int m = 0; m < MAX_COUNT; m++)
                            R[i][j][k][l][m] = 0;

        for (int i = 0; i < b + s ; i++)
            R[O[i][0]][O[i][1]][O[i][2]][O[i][3]][O[i][4]] = cost[i];

        for (int i = 0; i < b + s ; i++){
            for (int i1 = 0; i1 < MAX_COUNT; i1++){
                for (int i2 = 0; i2 < MAX_COUNT; i2++){
                    for (int i3 = 0; i3 < MAX_COUNT; i3++){
                        for (int i4 = 0; i4 < MAX_COUNT; i4++){
                            for (int i5 = 0; i5 < MAX_COUNT; i5++){
                                if(R[i1][i2][i3][i4][i5] == 0){
                                    if(     ((i1 + O[i][0]) <= amount[0]) &&
                                            ((i2 + O[i][1]) <= amount[1]) &&
                                            ((i3 + O[i][2]) <= amount[2]) &&
                                            ((i4 + O[i][3]) <= amount[3]) &&
                                            ((i5 + O[i][4]) <= amount[4]) ) {
                                        if (R[i1 + O[i][0]][i2 + O[i][1]][i3 + O[i][2]][i4 + O[i][3]][i5 + O[i][4]] > R[i1][i2][i3][i4][i5] + R[O[i][0]][O[i][1]][O[i][2]][O[i][3]][O[i][4]])
                                            R[i1 + O[i][0]][i2 + O[i][1]][i3 + O[i][2]][i4 + O[i][3]][i5 + O[i][4]] = R[i1][i2][i3][i4][i5] + R[O[i][0]][O[i][1]][O[i][2]][O[i][3]][O[i][4]];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        int minPay = Integer.MAX_VALUE;

        for (int i1 = 0; i1 < MAX_COUNT; i1++) {
            for (int i2 = 0; i2 < MAX_COUNT; i2++) {
                for (int i3 = 0; i3 < MAX_COUNT; i3++) {
                    for (int i4 = 0; i4 < MAX_COUNT; i4++) {
                        for (int i5 = 0; i5 < MAX_COUNT; i5++) {
                            if(R[i1][i2][i3][i4][i5] != 0)
                                if(R[i1][i2][i3][i4][i5] < minPay)
                                    minPay = R[i1][i2][i3][i4][i5];
                        }
                    }
                }
            }
        }

        System.out.println(R[amount[0]][amount[1]][amount[2]][amount[3]][amount[4]]);


    }
}
