/*package test;

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

        // 2D !
        *//*long time1 = System.currentTimeMillis();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        long time2 = System.currentTimeMillis();
        System.out.println("Time to create (ms): " + (time2 - time1));
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        if (maze.getNumOfRow() < 30)
            maze.print();*//*

        // 3D !
        *//*IMaze3DGenerator mg3 = new MyMaze3DGenerator();
        long time1 = System.currentTimeMillis();
        int x = 50;
        Maze3D maze3d = mg3.generate(x, x, x);
        long time2 = System.currentTimeMillis();
        System.out.println("Time to create: " + (time2 - time1));
        //maze3d.print();
        SearchableMaze3D searchableMaze3d = new SearchableMaze3D(maze3d);
        solveProblem(searchableMaze3d, new BreadthFirstSearch());
        solveProblem(searchableMaze3d, new BestFirstSearch());
        solveProblem(searchableMaze3d, new DepthFirstSearch());*//*
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        System.out.println("final cost " + searcher.getName() + " " + solutionPath.get(solutionPath.size() - 1).getCost());
        //System.out.println("Solution path:");
        *//*for (int i = 0; i < solutionPath.size(); i++) {
            AState x = solutionPath.get(i);
            System.out.println(String.format("%s.%s", i, x));
        }*//*
    }
}*/
//---------------------------------------MAOR-----------------------------------------------------------------

package test;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        int x = 5;
        Maze maze = mg.generate(4, x);
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
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {1, 0, 0, 1, 0},
                {1, 1, 0, 0, 0}
        };
        maze.setMazeArr(map);
        maze.setStartPosition(new Position(0, 0));
        maze.setGoalPosition(new Position(2, 4));
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        //MazeState ms = new MazeState(new Position(3,3));
        //ArrayList<AState> possibleStates = searchableMaze.getAllSuccessors(ms);
        //BreadthFirstSearch bfs = new BreadthFirstSearch();
        //BestFirstSearch best = new BestFirstSearch();
        //while(bfs.solve(searchableMaze).getSolutionCost()==best.solve(searchableMaze).getSolutionCost()){}

        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
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