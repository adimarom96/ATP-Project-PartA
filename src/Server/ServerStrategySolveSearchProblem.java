package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class ServerStrategySolveSearchProblem implements IServerStrategy, Serializable {

    private ISearchingAlgorithm searcher;
    private HashMap<String, String> map;
    private int counter = 0;

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        this.searcher = new BreadthFirstSearch();//Configurations?
        map = new HashMap<>();
        Solution sol;
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            Maze clientMaze = (Maze) fromClient.readObject();
            byte[] mazeKey = clientMaze.toByteArray();
            String mazeKeyString = Arrays.toString(mazeKey);
            if (map.containsKey(mazeKeyString)) {// check if we already solved this maze
                String solFile = map.get(mazeKeyString);
                File solutionFileRead = new File(tempDirectoryPath, solFile);
                FileInputStream inFile = new FileInputStream(solutionFileRead);
                ObjectInputStream inPut = new ObjectInputStream(inFile);
                sol = (Solution) inPut.readObject();
                inPut.close();
            } else {
                SearchableMaze searchableMaze = new SearchableMaze(clientMaze);
                sol = searcher.solve(searchableMaze);//Configurations?
                counter++;
                String solFileName = "Sol" + counter + ".txt"; // create value for the hashMap
                map.put(mazeKeyString,solFileName); // add to hashMap
                File newSol = new File(tempDirectoryPath,solFileName);// create file
                ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(newSol));
                outFile.writeObject(sol);// write to the file.
            }

            toClient.writeObject(sol);
            toClient.flush();
            toClient.close();
            fromClient.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}