package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    private HashMap<String, String> solvedMazes;
    private static int numberOfMazes = 0;

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

    }
}
