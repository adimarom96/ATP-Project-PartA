package algorithms.mazeGenerators;

public class Maze {
    int numOfRow;
    int numOfCol;
    int mazeArr[][];
    Position StartPosition;
    Position GoalPosition;

    public Position getStartPosition() {
        return StartPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
    }

    public Maze(int row, int col) {
        numOfCol = col;
        numOfRow = row;
    }

    public void print() {
        //TODO: to implement !
    }
}