package algorithms.maze3D;

public class Position3D {

    private int row;
    private int col;
    private int depth;

    public Position3D(int depth,int row, int col) {
        this.row = row;
        this.col = col;
        this.depth = depth;
    }

    public int getRowIndex() {
        return row;
    }

    public int getColumnIndex() {
        return col;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        // print in format of {row,column}
        return "{"+ depth +  "," + row + "," + col + "}";
    }
}
