package algorithms.search;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BestFirstSearchTest {

    @Test
    public void isLessThenMin() throws Exception {
        IMazeGenerator mg = new MyMazeGenerator();
        long time = mg.measureAlgorithmTimeMillis(1000, 1000);
        boolean isLessThenMin = false;
        if (time <= 60000)
            isLessThenMin = true;
        assertTrue(isLessThenMin);
    }

    @Test
    public void name() throws Exception {
        BestFirstSearch b = new BestFirstSearch();
        String s = "BestFirstSearch";
        assertEquals(b.getName(), s);
    }

    @Test
    public void startPoint() throws Exception {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(20, 20);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch best = new BestFirstSearch();
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        assertEquals(maze.getStartPosition(), ((MazeState) solutionPath.get(0)).getPos());
    }

    @Test
    public void endPoint() throws Exception {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(20, 20);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch best = new BestFirstSearch();
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        Position g = ((MazeState) solutionPath.get(solutionPath.size() - 1)).getPos();
        Position g2 = maze.getGoalPosition();
        assertEquals(g2.getColumnIndex(), g.getColumnIndex());
        assertEquals(g2.getRowIndex(), g.getRowIndex());
    }

    @Test
    void solveMaze1() throws Exception {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(4, 5);
        int[][] map = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {1, 0, 0, 1, 0},
                {1, 1, 0, 0, 0}
        };
        maze.setMazeArr(map);
        maze.setStartPosition(new Position(0, 0));
        maze.setGoalPosition(new Position(2, 4));
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch best = new BestFirstSearch();
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        //bfs check
        //Solution solution1 = bfs.solve(searchableMaze);
        //ArrayList<AState> solutionPath1 = solution1.getSolutionPath();
        //assertEquals(60, solutionPath1.get(solutionPath1.size() - 1).getCost());

        // best
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        assertEquals(55, solutionPath.get(solutionPath.size() - 1).getCost());

    }

    @Test
    void solveMaze2() throws Exception {
        //System.out.println("start test solveMaze2");
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(5, 5);
        int[][] map = {
                {1, 0, 0, 0, 0},
                {1, 0, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 0, 1, 0, 0}
        };
        maze.setMazeArr(map);
        maze.setStartPosition(new Position(0, 1));
        maze.setGoalPosition(new Position(4, 1));
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch best = new BestFirstSearch();
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        //bfs check
        Solution solution1 = bfs.solve(searchableMaze);
        ArrayList<AState> solutionPath1 = solution1.getSolutionPath();
        assertEquals(50, solutionPath1.get(solutionPath1.size() - 1).getCost());

        // best check
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        assertEquals(40, solutionPath.get(solutionPath.size() - 1).getCost());

        //System.out.println("End test solveMaze2");
    }

    @Test
    void wrongSize2D() throws Exception {
        IMazeGenerator mg = new MyMazeGenerator();
        try {
            Maze maze = mg.generate(1, 3);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Wrong parameters !");
        }
    }

    @Test
    void wrongSize3D() throws Exception {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        try {
            Maze3D maze = mg.generate(1, 3, 3);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Wrong parameters !");
        }
    }
}