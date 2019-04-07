import javafx.util.Pair;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;


public class Labyrinth {

    public static final int DOWN = 10;
    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public static final int UP = -10;

    static Stack<Pair<Integer, Integer>> route = new Stack<>();

    public static boolean search(int n, int m, int k, int[][] labyrinth, int[] exit, Stack<Pair<Integer, Integer>> route, int dir)
    {
        Pair<Integer, Integer> npos = new Pair<>(dir / 10 + route.peek().getKey(), dir % 10 + route.peek().getValue());
        if (npos.getKey() < 0 || npos.getKey() >= n || npos.getValue() < 0 || npos.getValue() >= m || labyrinth[npos.getKey()][npos.getValue()] != 0)
            return false;
        else
        {
            route.push(npos);
            if (npos.getKey() != n - 1)
                labyrinth[npos.getKey()][npos.getValue()] = 1;
            if (npos.getKey() == n - 2 && labyrinth[npos.getKey() + 1][npos.getValue()] != 0){
                for (int i = 0; i < k; i++) {
                    if(npos.getValue() == exit[i] - 1) {
                        route.push(new Pair<>(npos.getKey() + 1, npos.getValue()));
                        return true;
                    }
                }
            }
            if (npos.getKey() == n - 1) {
                for (int i = 0; i < k; i++) {
                    if(npos.getValue() == exit[i] - 1) {
                        return true;
                    }
                    if( npos.getValue() - 1 == exit[i] - 1) {
                        route.push(new Pair<>(npos.getKey(), npos.getValue() - 1));
                        return true;
                    }
                }
            }
            else
            {
			    int[] priorityDirs = new int[3];
                switch (dir)
                {
                    case DOWN:
                        priorityDirs[0] = LEFT;
                        priorityDirs[1] = DOWN;
                        priorityDirs[2] = RIGHT;
                    break;
                    case LEFT:
                        priorityDirs[0] = LEFT;
                        priorityDirs[1] = DOWN;
                        priorityDirs[2] = UP;
                    break;
                    case RIGHT:
                        priorityDirs[0] = DOWN;
                        priorityDirs[1] = RIGHT;
                        priorityDirs[2] = UP;
                    break;
                    case UP:
                        priorityDirs[0] = LEFT;
                        priorityDirs[1] = RIGHT;
                        priorityDirs[2] = UP;
                    break;
                }
                boolean found;
                for (int i = 0; i < 3; i++)
                {
                    found = search(n, m, k, labyrinth, exit, route, priorityDirs[i]);
                    if (found)
                        return true;
                }
                labyrinth[npos.getKey()][npos.getValue()] = 0;
                route.pop();
                return false;
            }
        }
        return false;
    }

    public static Stack<Pair<Integer, Integer>> findRoute(int n, int m, int k, int[][] labyrinth, int[] exit, int entrance)
    {
        route.clear();
        route.push(new Pair<>(0, entrance));
        int[] priorityDirs = new int[3];
        priorityDirs[0] = LEFT;
        priorityDirs[1] = DOWN;
        priorityDirs[2] = RIGHT;
        search(n, m, k, labyrinth, exit, route, priorityDirs[0]);
        for (int i = 1; i < 3; i++)
        {
            if(route.size() == 1) {
                route.clear();
                route.push(new Pair<>(0, entrance));
                search(n, m, k, labyrinth, exit, route, priorityDirs[i]);
            }
        }
        return route;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        int[] entry = new int[k];
        for(int i = 0; i < k; i++)
            entry[i] = in.nextInt();

        int[] exit = new int[k];
        for(int i = 0; i < k; i++)
            exit[i] = in.nextInt();

        int[][] labyrinth = new int[n][m];
        for(int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                labyrinth[i][j] = in.nextInt();

        int count = 0;
        for (int i = 0, number = 0; i < k; i++)
        {
            if (labyrinth[0][entry[i] - 1] == 0)
            {
                number++;
                Stack<Pair<Integer, Integer>> route = findRoute(n, m, k, labyrinth, exit, entry[i] - 1);
                if (route.size() > 1) {
                    count++;
                    while (!route.empty())
                    {
                        Pair<Integer, Integer> pos = route.peek();
                        labyrinth[pos.getKey()][pos.getValue()] = 1 + number;
                        route.pop();
                    }
                }
            }
        }

        FileWriter fr = new FileWriter(new File("output.txt"));
        fr.write(count + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                fr.write(labyrinth[i][j] + " ");
            fr.write("\n");
        }
        fr.close();
    }
}
