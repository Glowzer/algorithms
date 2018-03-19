import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Hoses {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        Stack<Integer> stack = new Stack<>();

        int n =  Integer.parseInt(in.nextLine());
        for(int i = 0; i < n; i++){
            String s = in.nextLine();
            String[] tmp = s.split(" ");
            int val = Integer.parseInt(tmp[tmp.length - 1]);

            if(stack.empty())
                stack.push(val);
            else
                if(stack.peek() == val)
                stack.pop();
                else
                    stack.push(val);
        }

        File file = new File("output.txt");
        FileWriter fr = null;
        fr = new FileWriter(file);
        if(stack.empty())
            fr.write("Yes");
        else
            fr.write("No");

        fr.close();
    }
}
