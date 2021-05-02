package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    private ExecutorService executor;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        executor = Executors.newFixedThreadPool(3);

    }
    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + port);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());

                    try {
                        strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
                        clientSocket.getInputStream().close(); // new adi add
                        clientSocket.getOutputStream().close();
                        clientSocket.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                } catch (SocketTimeoutException e){
                    System.out.println("Socket timeout");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void stop() {
        stop =true;
    }
}
