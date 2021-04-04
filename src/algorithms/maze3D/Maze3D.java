package algorithms.maze3D;

public class Maze3D {
    // TODO: maybe private ?!?!?
    int numOfDepth;
    int numOfRow;
    int numOfCol;
    int[][][] map;
    Position3D StartPosition;
    Position3D GoalPosition;

    // constructor
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

    // this function checks if the position we got is good to go ( =0 )
    public Boolean possibleToGo(int z, int x, int y) {
        if (map[z][x][y] == 0)
            return true;
        return false;
    }

    // this function print the 3D maze as requested
    public void print() {
        System.out.println("{");
        for (int z = 0; numOfDepth > z; z++) {
            for (int i = 0; i < numOfRow; i++) {
                System.out.print("{ ");
                for (int j = 0; j < numOfCol; j++) {
                    System.out.print(this.map[z][i][j] + " ");
                }
                System.out.print("}");
                System.out.println();
            }
            if (z != numOfDepth - 1) {
                // print ----- between every 2 surfaces in the depth
                String str = "-";
                //System.out.println(str.repeat(2 * numOfCol + 3));
                System.out.println(new String(new char[2 * numOfCol + 3]).replace("\0","-"));
            }
        }
        System.out.println("}");
    }

    public int[][][] getMap() {
        return map;
    }
}