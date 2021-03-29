package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Random;

public class SearchableMaze implements ISearchable {
    Maze maze;
    MazeState[][] statesArray;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
        Random random = new Random();
        int x;
        MazeState[][] statesArray = new MazeState[maze.getNumOfRow()][maze.getNumOfCol()];
        for (int i = 0; i < maze.getNumOfRow(); i++) {
            for (int j = 0; j < maze.getNumOfCol(); j++) {
                x =random.nextInt(50);
                statesArray[i][j] = new MazeState(x, null, new Position(i, j));
            }
        }
        this.statesArray = statesArray;
    }

    @Override
    public AState getStart() {
        Position pos= maze.getStartPosition();
        MazeState state = new MazeState(1, null, pos);
        return state;
    }

    @Override
    public AState getGoal() {
        MazeState state = new MazeState(1, null, maze.getGoalPosition()); //TODO: maybe need -1 insted of 1
        return state;
    }

    private boolean inBorder(int x, int y) {
        if (x > -1 && x < maze.getNumOfRow()) {
            return y > -1 && y < maze.getNumOfCol();
        }
        return false;
    }

    @Override
    public ArrayList<AState> getAllPossible(AState state) { // return all the neighbors that i can go from this current pos
        ArrayList<AState> possibleState = new ArrayList<AState>();
        int x = ((MazeState) state).getPos().getRowIndex();
        int y = ((MazeState) state).getPos().getColumnIndex();

        //-----------------------------right
        if (inBorder(x, y + 1)) {
            if (maze.possibleToGo(x, y + 1)) {
                possibleState.add(statesArray[x][y + 1]);

                //right up
                if (inBorder(x - 1, y + 1)) {
                    if (maze.possibleToGo(x - 1, y + 1)) {
                        possibleState.add(statesArray[x - 1][y + 1]);
                    }
                }


                // right bottom
                if (inBorder(x + 1, y + 1)) {
                    if (maze.possibleToGo(x + 1, y + 1)) {
                        possibleState.add(statesArray[x + 1][y + 1]);
                    }
                }
            }
        }
        //------------------------------left
        if (inBorder(x, y - 1)) {
            if (maze.possibleToGo(x, y - 1)) {
                possibleState.add(statesArray[x][y - 1]);

                //left up
                if (inBorder(x - 1, y - 1)) {
                    if (maze.possibleToGo(x - 1, y - 1)) {
                        possibleState.add(statesArray[x - 1][y - 1]);
                    }
                }

                // left bottom
                if (inBorder(x + 1, y - 1)) {
                    if (maze.possibleToGo(x + 1, y - 1)) {
                        possibleState.add(statesArray[x + 1][y - 1]);
                    }
                }
            }
        }
        //-------------------------------up
        if (inBorder(x-1, y)) {
            if (maze.possibleToGo(x-1,y)) {
                possibleState.add(statesArray[x-1][y]);

                //up left
                if (inBorder(x - 1, y-1 )) {
                    if (maze.possibleToGo(x - 1, y - 1) && !possibleState.contains(statesArray[x - 1][y - 1])) {
                        possibleState.add(statesArray[x - 1][y - 1]);
                    }
                }

                // up right
                if (inBorder(x - 1, y + 1)) {
                    if (maze.possibleToGo(x - 1, y + 1)&& !possibleState.contains(statesArray[x - 1][y + 1])) {
                        possibleState.add(statesArray[x - 1][y + 1]);
                    }
                }
            }
        }
        // ------------------------------bottom
        if (inBorder(x+1, y )) {
            if (maze.possibleToGo(x+1, y )) {
                possibleState.add(statesArray[x+1][y]);

                //bottom left
                if (inBorder(x+1, y -1)) {
                    if (maze.possibleToGo(x + 1, y - 1)) {
                        possibleState.add(statesArray[x + 1][y - 1]);
                    }
                }

                // bottom right
                if (inBorder(x + 1, y + 1)) {
                    if (maze.possibleToGo(x + 1, y + 1)) {
                        possibleState.add(statesArray[x + 1][y + 1]);
                    }
                }
            }
        }

        return possibleState;
    }

    @Override
    public void restStates() {
        for (int i = 0; i <maze.getNumOfRow(); i++) {
            for (int j = 0; j <maze.getNumOfCol() ; j++) {
                statesArray[i][j].setPreState(null);
            }
        }
    }
}
