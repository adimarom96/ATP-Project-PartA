package algorithms.mazeGenerators;

public class Maze {
    int numOfRow;
    int numOfCol;
    int[][] mazeArr;
    Position StartPosition;
    Position GoalPosition;

    public Position getStartPosition() {
        return StartPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
    }

    public void setStartPosition(Position startPosition) {
        StartPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        GoalPosition = goalPosition;
    }

    public Maze(int row, int col) {
        numOfCol = col;
        numOfRow = row;
        mazeArr = new int[row][col];
    }

    public void Print() {
        for (int i = 0; i < numOfRow; i++) {
            for (int j = 0; j < numOfCol; j++) {
                if(i == getStartPosition().getRowIndex() && j == getStartPosition().getColumnIndex())
                    System.out.print("S ");
                else if (i == getGoalPosition().getRowIndex() && j == getGoalPosition().getColumnIndex())
                    System.out.print("E ");
                else
                    System.out.print(this.mazeArr[i][j] + " ");
            }
            System.out.println(); // "\n"
        }
    }
}