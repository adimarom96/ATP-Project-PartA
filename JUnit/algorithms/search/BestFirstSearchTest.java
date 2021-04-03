package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import javafx.geometry.Pos;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    @Test
    public void isLessThenMin() throws Exception {
        System.out.println("start test isLessThenMin");
        IMazeGenerator mg = new MyMazeGenerator();
        long time = mg.measureAlgorithmTimeMillis(1000, 1000);
        boolean isLessThenMin = false;
        if (time <= 60000)
            isLessThenMin = true;
        assertTrue(isLessThenMin);
        System.out.println("End test isLessThenMin");
    }

    @Test
    public void name() throws Exception {
        System.out.println("start test name");
        BestFirstSearch b = new BestFirstSearch();
        String s = "BestFirstSearch";
        assertEquals(b.getName(), s);
        System.out.println("End test name");
    }

    @Test
    public void startPoint() throws Exception {
        System.out.println("start test startPoint");
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(20, 20);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch best = new BestFirstSearch();
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        assertEquals(maze.getStartPosition(), ((MazeState) solutionPath.get(0)).getPos());
        System.out.println("End test startPoint");
    }

    @Test
    public void endPoint() throws Exception {
        System.out.println("start test endPoint");
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
        System.out.println("End test endPoint");
    }
}