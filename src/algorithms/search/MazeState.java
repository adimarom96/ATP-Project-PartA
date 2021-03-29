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



    @Override
    public boolean equals(Object obj) {
      if(this.pos.getColumnIndex() == ((MazeState)obj).getPos().getColumnIndex() && this.pos.getRowIndex() == ((MazeState)obj).getPos().getRowIndex())
        return true;
      return false;
    }

    @Override
    public String toString() {

        return this.getPos().toString();
    }
}
