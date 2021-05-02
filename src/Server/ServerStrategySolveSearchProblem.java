package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    private ISearchingAlgorithm searcher;

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
    try{
        ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
        Maze clientMaze = (Maze)fromClient.readObject();
        Solution sol;

        SearchableMaze searchableMaze = new SearchableMaze(clientMaze);
        sol = searcher.solve(searchableMaze);
        String mazeFileName = "maze";
        String solFileName  = "mazesol";
        //writeAll(solFileName,sol,mazeFileName,clientMaze);

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    }
    /*private synchronized void writeAll (String solName, Solution sol, String mazeName, Maze outM, String newMazeKey)
    {
        writeSolToFile(solName, sol);
        writeMazeToFile(mazeName, outM);
        solAndMazeDict.put(newMazeKey, solName);
    }*/
}

