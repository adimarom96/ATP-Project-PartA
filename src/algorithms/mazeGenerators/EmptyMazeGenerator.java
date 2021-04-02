package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {
        if (row < 2 || col < 2)
            throw new RuntimeException("Wrong parameters !");
        Maze empty_maze = new Maze(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                empty_maze.mazeArr[i][j] = 0;
            }
        }
        empty_maze.setStartPosition(new Position(0, 0));
        empty_maze.setGoalPosition(new Position(row - 1, col - 1));
        return empty_maze;
    }
}
