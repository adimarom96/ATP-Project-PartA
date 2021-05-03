package test;

import Client.Client;
import Client.IClientStrategy;
import IO.MyDecompressorInputStream;
import Server.Server;
import Server.ServerStrategyGenerateMaze;
import Server.ServerStrategySolveSearchProblem;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.AState;
import algorithms.search.Solution;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class RunCommunicateWithServers {
    public static void main(String[] args) {
        //Initializing servers
        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());

        //Starting servers
        solveSearchProblemServer.start();
        mazeGeneratingServer.start();

        //Communicating with servers
        CommunicateWithServer_MazeGenerating();
        CommunicateWithServer_SolveSearchProblem();


        //Stopping all servers
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();
    }

    private static void CommunicateWithServer_MazeGenerating() {
        try {
            Client client1 = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        int x = 100;
                        int[] mazeDimensions = new int[]{x, x};
                        toServer.writeObject(mazeDimensions); //send maze dimensions to server
                        toServer.flush();
                        byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with MyCompressor)from server
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        //byte[] b = new byte[x * x * 4 + 24]; //allocating byte[] for the decompressed maze -
                        byte[] b = new byte[x * x + 24]; //allocating byte[] for the decompressed maze -
                        is.read(b); //Fill decompressedMaze with bytes
                        Maze maze = new Maze(b);
                        //maze.print();
                        System.out.println("success1!");//delete
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client1.communicateWithServer();

           /* Client client2 = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        int x = 100;
                        int[] mazeDimensions = new int[]{x, x};
                        toServer.writeObject(mazeDimensions); //send maze dimensions to server
                        toServer.flush();
                        byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with MyCompressor)from server
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        //byte[] b = new byte[x * x * 4 + 24]; //allocating byte[] for the decompressed maze -
                        byte[] b = new byte[x * x + 24]; //allocating byte[] for the decompressed maze -
                        is.read(b); //Fill decompressedMaze with bytes
                        Maze maze = new Maze(b);
                        //maze.print();
                        System.out.println("success2!");//delete
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client2.communicateWithServer();*/

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void CommunicateWithServer_SolveSearchProblem() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        MyMazeGenerator mg = new MyMazeGenerator();
                        Maze maze = mg.generate(12, 12);
                        maze.print();
                        toServer.writeObject(maze); //send maze to server
                        toServer.flush();
                        Solution mazeSolution = (Solution) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server

                        //Print Maze Solution retrieved from the server
                        System.out.println(String.format("Solution steps: %s", mazeSolution));
                        ArrayList<AState> mazeSolutionSteps = mazeSolution.getSolutionPath();
                        for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                            System.out.println(String.format("%s. %s", i, mazeSolutionSteps.get(i).toString()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}