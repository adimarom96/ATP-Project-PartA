package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy ,Serializable{
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] size = (int[]) fromClient.readObject();
            Configurations confi;
            confi = Configurations.getInstance();
            AMazeGenerator generator = new MyMazeGenerator();//Configurations?
            Maze maze = generator.generate(size[0], size[1]);

            ByteArrayOutputStream bais = new ByteArrayOutputStream();
            MyCompressorOutputStream comp = new MyCompressorOutputStream(bais);//Configurations?
            comp.write(maze.toByteArray());
            toClient.writeObject(((ByteArrayOutputStream)comp.out).toByteArray());
            toClient.flush();
            toClient.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}