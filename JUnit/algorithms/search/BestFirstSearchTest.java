package algorithms.search;
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
        //System.out.println("start test isLessThenMin");
        IMazeGenerator mg = new MyMazeGenerator();
        long time = mg.measureAlgorithmTimeMillis(1000, 1000);
        boolean isLessThenMin = false;
        if (time <= 60000)
            isLessThenMin = true;
        assertTrue(isLessThenMin);
        //System.out.println("End test isLessThenMin");
    }

    @Test
    public void name() throws Exception {
        //System.out.println("start test name");
        BestFirstSearch b = new BestFirstSearch();
        String s = "BestFirstSearch";
        assertEquals(b.getName(), s);
        //System.out.println("End test name");
    }

    @Test
    public void startPoint() throws Exception {
        //System.out.println("start test startPoint");
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(20, 20);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch best = new BestFirstSearch();
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        assertEquals(maze.getStartPosition(), ((MazeState) solutionPath.get(0)).getPos());
        //System.out.println("End test startPoint");
    }

    @Test
    public void endPoint() throws Exception {
        //System.out.println("start test endPoint");
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
        //System.out.println("End test endPoint");
    }

    @Test
    void solveMaze1() throws Exception {
        //System.out.println("start test solveMaze1");
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
        Solution solution1 = bfs.solve(searchableMaze);
        ArrayList<AState> solutionPath1 = solution1.getSolutionPath();
        assertEquals(60, solutionPath1.get(solutionPath1.size() - 1).getCost());

        // best
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        assertEquals(55, solutionPath.get(solutionPath.size() - 1).getCost());

        //System.out.println("End test solveMaze1");
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
}