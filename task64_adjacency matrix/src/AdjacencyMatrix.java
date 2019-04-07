import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjacencyMatrix {

    public static void main(String[] args)throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        int m = in.nextInt();

        ArrayList<Integer> [] l = (ArrayList<Integer>[]) new Object [n];

        for (int i = 0; i < m; i++){
            int v = in.nextInt();
            int u = in.nextInt();
            l.add(v - 1, new Integer(u));
        }

        FileWriter fr  = new FileWriter(new File("output.txt"));

        fr.close();
    }
}
