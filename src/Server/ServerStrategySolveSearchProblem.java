package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ServerStrategySolveSearchProblem implements IServerStrategy, Serializable {

    private static ConcurrentHashMap<String, String> map;
    private static ReentrantLock m;

    public ServerStrategySolveSearchProblem() {
        map = new ConcurrentHashMap<>();
        m = new ReentrantLock();
    }

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        ISearchingAlgorithm searcher;
        m.lock();
        File file = new File(System.getProperty("java.io.tmpdir"), "hash");
        file.createNewFile();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String l;
            while ((l = br.readLine()) != null) {
                String[] args = l.split("],", 2);
                if (args.length != 2) continue;
                String p = args[0]+"]";
                String b = args[1];
                map.put(p, b);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        m.unlock();

        Solution sol;
        Configurations config = Configurations.getInstance();
        String search = config.getP("problemSolver");
        if (search.equals("BreadthFirstSearch"))
            searcher = new BreadthFirstSearch();
        else if (search.equals("DepthFirstSearch"))
            searcher = new DepthFirstSearch();
        else
            searcher = new BestFirstSearch();
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");//C:\Users\mille\AppData\Local\Temp\
            //System.out.println(tempDirectoryPath);
            m.lock();
            Maze clientMaze = (Maze) fromClient.readObject();
            m.unlock();
            byte[] mazeKey = clientMaze.toByteArray();
            String mazeKeyString = Arrays.toString(mazeKey);
            int hash = mazeKeyString.hashCode();
            if (map.containsKey(mazeKeyString)) {// check if we already solved this maze
                String solFile = map.get(mazeKeyString);
                File solutionFileRead = new File(tempDirectoryPath, solFile);
                FileInputStream inFile = new FileInputStream(solutionFileRead);
                ObjectInputStream inPut = new ObjectInputStream(inFile);
                sol = (Solution) inPut.readObject();
                inPut.close();
                //System.out.println("old");
            }
            else { // solve for the first time.
                //System.out.println("new");
                SearchableMaze searchableMaze = new SearchableMaze(clientMaze);
                sol = searcher.solve(searchableMaze);//Configurations?
                String solFileName = "Sol" + hash + ".Solution"; // create value for the hashMap
                m.lock();
                map.put(mazeKeyString, solFileName); // add to hashMap
                //System.out.println("1");
                File newSol = new File(tempDirectoryPath, solFileName);// create file
                ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(newSol));
                outFile.writeObject(sol);// write to the file.
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    for (String p : map.keySet()) {
                        bw.write(p + "," + map.get(p));
                        bw.newLine();
                    }
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                m.unlock();
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