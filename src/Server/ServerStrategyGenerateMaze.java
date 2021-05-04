package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy, Serializable {
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        OutputStream comp;
        Configurations config = Configurations.getInstance();
        AMazeGenerator generator = new SimpleMazeGenerator();
        String gen = config.getP("generateMaze");
        String compress = config.getP("compress");
        //String gen = config.getGenerator();
        //String compress = config.getCompress();
        boolean isMyComp = false;
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] size = (int[]) fromClient.readObject();
            if (gen.equals("MyMazeGenerator"))
                generator = new MyMazeGenerator();
            else if (gen.equals("EmptyMazeGenerator"))
                generator = new EmptyMazeGenerator();

            Maze maze = generator.generate(size[0], size[1]);
            ByteArrayOutputStream bais = new ByteArrayOutputStream();
            if (compress.equals("MyCompressorOutputStream")) {
                comp = new MyCompressorOutputStream(bais);
                isMyComp = true;
            } else
                comp = new SimpleCompressorOutputStream(bais);
            comp.write(maze.toByteArray());
            if (isMyComp)
                toClient.writeObject(((ByteArrayOutputStream) ((MyCompressorOutputStream) comp).out).toByteArray());
            else
                toClient.writeObject(((ByteArrayOutputStream) ((SimpleCompressorOutputStream) comp).out).toByteArray());
            toClient.flush();
            toClient.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}