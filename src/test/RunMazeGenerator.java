package test;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;
import algorithms.mazeGenerators.*;

import java.util.Random;

public class RunMazeGenerator {
    public static void main(String[] args) {
        //testMazeGenerator(new EmptyMazeGenerator());
        //testMazeGenerator(new SimpleMazeGenerator());
        //testMazeGenerator(new MyMazeGenerator());
        //testMaze3DGenerator(new MyMaze3DGenerator());
    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(1000, 1000)));
        // generate another maze

        Maze maze = mazeGenerator.generate(2/*rows*/, 2/*columns*/);
        System.out.println("Print 2D");
        maze.print();

        // prints the maze
        // get the maze entrance
        Position startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }

    private static void testMaze3DGenerator(IMaze3DGenerator maze3DGenerator) {
        // prints the time it takes the algorithm to run
        int size = 20;
        //System.out.println(String.format("Maze generation time(ms): %s", maze3DGenerator.measureAlgorithmTimeMillis(size, size, size)));
        // generate another maze
        System.out.println("Print 3D");
        Maze3D maze3d = maze3DGenerator.generate(20/*depth*/, 20/*rows*/, 20/*columns*/);
        maze3d.print();

        // prints the maze
        System.out.println("in main - run maze generator");
        // get the maze entrance
        Position3D startPosition = maze3d.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{depth,row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze3d.getGoalPosition()));
    }
}