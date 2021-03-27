package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    private Position pos;


    public MazeState(double cost, AState preState, Position pos) {
        super(cost, preState);
        this.pos=pos;

    }

    public Position getPos() {
        return pos;
    }
}
