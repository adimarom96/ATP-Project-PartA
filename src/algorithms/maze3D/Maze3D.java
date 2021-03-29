package algorithms.maze3D;

public class Maze3D {
    int numOfDepth;
    int numOfRow;
    int numOfCol;
    int[][][] map;
    Position3D StartPosition;
    Position3D GoalPosition;

    public Maze3D(int depth, int row, int col) {
        numOfDepth = depth;
        numOfCol = col;
        numOfRow = row;
        map = new int[depth][row][col];
    }

    public Position3D getStartPosition() {
        return StartPosition;
    }

    public void setStartPosition(Position3D startPosition) {
        StartPosition = startPosition;
    }

    public Position3D getGoalPosition() {
        return GoalPosition;
    }

    public void setGoalPosition(Position3D goalPosition) {
        GoalPosition = goalPosition;
    }

    public int getNumOfDepth() {
        return numOfDepth;
    }

    public int getNumOfRow() {
        return numOfRow;
    }

    public int getNumOfCol() {
        return numOfCol;
    }

    public Boolean possibleToGo(int z, int x, int y) {
        if (map[z][x][y] == 0 || map[z][x][y] == 5)
            return true;
        return false;
    }

    public void Print() {
        /*System.out.print("   ");
        for (int i = 0; i < numOfRow; i++) {
            System.out.print(" " + i);
        }
        System.out.println("");*/
        for (int z = 0; numOfDepth > z; z++) {
            for (int i = 0; i < numOfRow; i++) {
                for (int j = 0; j < numOfCol; j++) {
                    System.out.print(this.map[z][i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

        }
    }

    public int[][][] getMap() {
        return map;
    }
}
