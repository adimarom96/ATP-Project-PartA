package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.*;
import java.util.Properties;

public class Configurations {
    public static void main(String[] args) {
    }

    public static int readNumOfThreads() {
        // TODO: implement
        return 3;
    }
}