package test;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        int x = 4;
        Maze maze = mg.generate(x, x);
        System.out.println("size: " + x + "x" + x);
//        int[][] map ={{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
//                      { 1, 0, 1, 1, 1, 0, 1, 1, 1, 0 },
//                      { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
//                      { 1, 0, 1, 0, 1, 0, 1, 0, 1, 1 },
//                      { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
//                      { 1, 0, 1, 1, 1, 1, 1, 1, 1, 0 },
//                      { 1, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
//                      { 1, 0, 1, 0, 1, 0, 1, 1, 1, 0 },
//                      { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
//                      { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 }};
        int[][] map = {
                {1, 0, 0, 0},
                {1, 0, 1, 1},
                {1, 0, 0, 0},
                {1, 1, 1, 0}
        };
        maze.setMazeArr(map);
        maze.setStartPosition(new Position(0, 0));
        maze.setGoalPosition(new Position(3, 3));
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        //MazeState ms = new MazeState(new Position(3,3));
        //ArrayList<AState> possibleStates = searchableMaze.getAllSuccessors(ms);
        //BreadthFirstSearch bfs = new BreadthFirstSearch();
        //BestFirstSearch best = new BestFirstSearch();
        //while(bfs.solve(searchableMaze).getSolutionCost()==best.solve(searchableMaze).getSolutionCost()){}

        solveProblem(searchableMaze, new BreadthFirstSearch());
        //solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
        }
        System.out.println("final cost " + solutionPath.get(solutionPath.size() - 1).getCost());
    }
}