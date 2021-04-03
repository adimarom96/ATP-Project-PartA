package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    private Position pos;

    // constructor
    public MazeState(double cost, AState preState, Position pos) {
        super(cost, preState);
        this.pos = pos;
    }

    public Position getPos() {
        return pos;
    }

    // check if the state we have equals to the state we got
    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.pos.getColumnIndex() == ((MazeState) obj).getPos().getColumnIndex() && this.pos.getRowIndex() == ((MazeState) obj).getPos().getRowIndex())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return this.getPos().toString();
    }
}

// new condition in line 21 - if obj != null...