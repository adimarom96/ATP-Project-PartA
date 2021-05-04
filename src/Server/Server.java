package Server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.*;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private ThreadPoolExecutor executor;
    private boolean stop;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy)  {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        Configurations config = Configurations.getInstance();
        //System.out.println("is Empty? "+Configurations.getInstance().getP("threadPoolSize"));
        String x = Configurations.getInstance().getP("threadPoolSize");//todo-check!
        if(x == null)
        {
            Configurations.setP("threadPoolSize", "5");
            Configurations.setP("problemSolver", "BreadthFirstSearch");
            Configurations.setP("generateMaze", "MyMazeGenerator");
            Configurations.setP("compress", "MyCompressorOutputStream");
        }
        String str = config.getP("threadPoolSize");
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Integer.parseInt(str));
    }

    public void start() {
        new Thread(() -> runServer()).start();
    }

    private void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    InputStream inFromClient = clientSocket.getInputStream();
                    OutputStream outToClient = clientSocket.getOutputStream();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            setServerStrategy(inFromClient, outToClient, clientSocket);
                        }
                    });
                } catch (SocketTimeoutException e) {
                    System.out.println("Socket timeout ");
                }
            }
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.MINUTES);
            serverSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setServerStrategy(InputStream inFromClient, OutputStream outToClient, Socket clientSocket) {
        try {
            this.strategy.ServerStrategy(inFromClient, outToClient);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stop = true;
    }
}
