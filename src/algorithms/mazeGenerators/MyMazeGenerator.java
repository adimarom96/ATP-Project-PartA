package algorithms.mazeGenerators;

import java.util.LinkedList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width); // init
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze.mazeArr[i][j] = 1;
            }
        }

        LinkedList<int[]> designated = new LinkedList<>();
        final Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        designated.add(new int[]{x, y, x, y});

        while (!designated.isEmpty()) {
            final int[] f = designated.remove(random.nextInt(designated.size()));
            x = f[2];
            y = f[3];
            if (maze.mazeArr[x][y] == 1) {
                maze.mazeArr[f[0]][f[1]] = maze.mazeArr[x][y] = 0;
                if (x >= 2 && maze.mazeArr[x - 2][y] == 1)
                    designated.add(new int[]{x - 1, y, x - 2, y});
                if (y >= 2 && maze.mazeArr[x][y - 2] == 1)
                    designated.add(new int[]{x, y - 1, x, y - 2});
                if (x < height - 2 && maze.mazeArr[x + 2][y] == 1)
                    designated.add(new int[]{x + 1, y, x + 2, y});
                if (y < width - 2 && maze.mazeArr[x][y + 2] == 1)
                    designated.add(new int[]{x, y + 1, x, y + 2});
            }
        }

        /*LinkedList<int[]> designated = new LinkedList<>();
        Random r = new Random();
        //int rnd = r.nextInt((1 - 0) + 1);
        int x = r.nextInt(row);//TODO: maybe need "-1"
        int y = r.nextInt(col);
        //x = 0;
        //y = 0;
        int[] toAdd = new int[]{x, y, x, y};
        designated.add(toAdd);
        while (!designated.isEmpty()) {
            final int[] f = designated.remove(r.nextInt(designated.size()));
            x = f[2];
            y = f[3];
            if (maze.mazeArr[x][y] == 1) {
                maze.mazeArr[x][y] = 0;
                maze.mazeArr[f[0]][f[1]] = 0;
                if (x >= 2 && maze.mazeArr[x - 2][y] == 1)
                    designated.add(new int[]{x - 1, y, x - 2, y});
                if (y >= 2 && maze.mazeArr[x][y - 2] == 1)
                    designated.add(new int[]{x, y - 1, x, y - 2});
                if (x < col - 2 && maze.mazeArr[x + 2][y] == 1)
                    designated.add(new int[]{x + 1, y, x + 2, y});
                if (y < row - 2 && maze.mazeArr[x][y + 2] == 1)
                    designated.add(new int[]{x, y + 1, x, y + 2});

                System.out.println("*****************");
                maze.Print();
                System.out.println("+++++++++++++++++");
            }
        }*/
        return maze;
    }
}
