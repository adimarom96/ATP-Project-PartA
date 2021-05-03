package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    //private ExecutorService executor;
    private ThreadPoolExecutor executor;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        //executor = Executors.newFixedThreadPool(Configurations.readNumOfThreads());
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void start() {
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        new Thread(() -> runServer()).start();
    }

    private void runServer() {
        //int i = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            //System.out.println("Starting server at port = " + port);
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
                    //System.out.println("Client accepted: " + clientSocket.toString());
                } catch (SocketTimeoutException e) {
                    //System.out.println("Socket timeout " + i);
                    //i++;
                }
                    /*strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
                    clientSocket.getInputStream().close(); // new adi add
                    clientSocket.getOutputStream().close();
                    clientSocket.close();*/
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
