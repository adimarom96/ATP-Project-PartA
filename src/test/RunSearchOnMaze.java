package test;
import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        //IMazeGenerator mg = new MyMazeGenerator();
        IMaze3DGenerator mg3 = new MyMaze3DGenerator();
        //Maze maze = mg.generate(200, 200);
        Maze3D maze3d = mg3.generate(100,100, 100);
        //maze.print();
        maze3d.print();
        //SearchableMaze searchableMaze = new SearchableMaze(maze);
        SearchableMaze3D searchableMaze3d = new SearchableMaze3D(maze3d);
        solveProblem(searchableMaze3d, new BreadthFirstSearch());
        solveProblem(searchableMaze3d, new BestFirstSearch());
        solveProblem(searchableMaze3d, new DepthFirstSearch());
        /*solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());*/
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            AState x=solutionPath.get(i);
            System.out.println(String.format("%s.%s",i,x));
        }
    }
}