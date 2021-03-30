package algorithms.maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.MazeState;

public class Maze3DState extends AState {
    private Position3D pos;

    // constructor
    public Maze3DState(double cost, AState preState, Position3D pos) {
        super(cost, preState);
        this.pos = pos;
    }

    public Position3D getPos() {
        return pos;
    }

    // check if the state we have equals to the state we got
    @Override
    public boolean equals(Object obj) {
        if (this.pos.getColumnIndex() == ((Maze3DState) obj).getPos().getColumnIndex()
                && this.pos.getRowIndex() == ((Maze3DState) obj).getPos().getRowIndex()
                && this.pos.getDepthIndex() == ((Maze3DState) obj).getPos().getDepthIndex())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return this.getPos().toString();
    }
}