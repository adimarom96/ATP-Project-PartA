package test;
import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
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

        Maze maze = mazeGenerator.generate(3/*rows*/, 3/*columns*/);
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
        System.out.println(String.format("Maze generation time(ms): %s", maze3DGenerator.measureAlgorithmTimeMillis(150, 150, 150)));
        // generate another maze
        System.out.println("Print 3D");
        //Maze3D maze3d = maze3DGenerator.generate(50/*depth*/,50/*rows*/, 50/*columns*/);
        //maze3d.print();

        /*// prints the maze
        System.out.println("in main - run maze generator");
        // get the maze entrance
        Position startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));*/
    }
}