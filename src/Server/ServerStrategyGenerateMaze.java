package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] size = (int[]) fromClient.readObject();
            AMazeGenerator maze = new MyMazeGenerator();
            Maze m = maze.generate(size[0], size[1]);
            SimpleCompressorOutputStream comp = new SimpleCompressorOutputStream(toClient);
            comp.write(m.toByteArray());
            comp.flush();
            comp.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
