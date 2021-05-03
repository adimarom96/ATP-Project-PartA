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

import static java.lang.Thread.sleep;

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
//        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());todo - bring back confi
        executor.setCorePoolSize(1);
        new Thread(() -> runServer()).start();
    }

    private void runServer() {
        //int i = 0;
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
                    //System.out.println("Socket timeout " + i);
                    //i++;
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
