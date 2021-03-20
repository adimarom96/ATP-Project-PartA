package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {
        Maze empty_maze = new Maze(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                empty_maze.mazeArr[i][j] = 0;
            }
        }
        return empty_maze;
    }
}