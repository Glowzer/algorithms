import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class task56 {

    public static void main(String[] args)throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        long  n = in.nextLong();
        int count = 0;

        FileWriter fr = new FileWriter(new File("output.txt"));
        while(n > 0) {
            if((n & 1) != 0) {
                fr.write(String.valueOf(count) + "\n");
            }
            count++;
            n >>= 1;
        }
        fr.close();
    }
}
