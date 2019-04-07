import java.io.File;
import java.io.IOException;
import java.util.Scanner;


class Point{
    int x;
    int y;

    Point (int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class PolygonTriangulation {

    public double min = 1000000;

    public static void printMatrix(double [][]a){
        for (int i = 0; i < a.length; i++){
            System.out.print("");
            for(int j = 0; j < a.length; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static double dist(Point points[], int i, int j) {
        Point p1 = points[i], p2 = points[j];
        return (Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) +
                (p1.y - p2.y)*(p1.y - p2.y)));
    }

    public static int Find(int a, int b, double[][] S, int n)
    {
        double min = 1000000;
        int min_i = -1;
        if (b < a)
        {
            b = b + n;
        }
        for (int count = a + 1; count < b; count++)
        {
            if (min > (S[a][count % n] + S[count % n][b % n]))
            {
                min = S[a][count % n] + S[count % n][b % n];
                min_i = count;
            }
        }
        return min_i;
    }

    public static double solution(Point[] polygon, int n){

        double min = 1000000;
        double[][] S = new double[n][n];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                S[i][j] = 0;
            }
            S[i][i] = 0;
            S[i][(i + 1) % n] = 0;
        }
        for (int k = 2; k < n; k++)
        {
            for (int p = 0; p < n; p++)
            {
                int i = Find(p,p + k, S, n);
                if (i != (-1))
                    S[p][(p + k) % n]= dist(polygon, p,(p + k) % n) + min;
                else
                    S[p][(p + k) % n] = dist(polygon, p,(p + k) % n);

            }
        }
        printMatrix(S);

        min = 1000000;
        for (int p = 0; p < n; p++)
        {
            if (S[p][(p + n - 2) % n] < min)
                min = S[p][(p + n - 2) % n];
        }

        return min;
    }


    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new File("input.txt"));

        int n = in.nextInt();
        Point[] polygon = new Point[n];

        for (int i = 0; i < n; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            polygon[i] = new Point(x, y);
        }

        System.out.println(solution(polygon, n));


    }
}
