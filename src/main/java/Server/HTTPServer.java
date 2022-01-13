package Server;

import Handlers.Handler;
import Handlers.SocketHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * HTTPServer Class.
*/
public class HTTPServer {

    private volatile boolean running;
    private int port;
    private ServerSocket server;
    private HashMap<String, Handler> mappingHM;
    private ExecutorService threadPool;

    private static final Logger LOGGER = LogManager.getLogger(HTTPServer.class);

    /**
     * Constructor that takes parameter port.
     *
     * @param port
     */
    public HTTPServer(int port) {
        this.port = port;
        this.running = true;
        this.mappingHM = new HashMap<>();

        try {
            server = new ServerSocket(port);
            System.out.println("server: server created on port: " + port);
            LOGGER.debug("Server: server created on port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.threadPool = Executors.newFixedThreadPool(5);

    }

    /**
     * Add path and corresponding handler into HashMap.
     *
     * @param path
     * @param handler
     */
    public void addMapping(String path, Handler handler) {
        this.mappingHM.put(path, handler);
    }

    /**
     * method that validates REquest line
     *
     * @param requestLineParts
     * @return
     */
    public boolean validateRequestLine(String[] requestLineParts) {
        if (requestLineParts.length == 3) {
            return true;
        }
        return false;
    }


    /**
     * Listen for Connections.
     */
    public void listenForConnection(ServerSocket server) {

        // block on accept until a new client connects
        // waiting until start communicating
        Socket clientSocket = null;
        try {
            clientSocket = server.accept();
        } catch (IOException e) {
            System.out.println("ServerSocket was closed!" + e);
        }
        LOGGER.debug("socket: " + clientSocket);

        if (running) {
            SocketHandler socketHandler = new SocketHandler(clientSocket, mappingHM);

            threadPool.execute(socketHandler);
        }
        LOGGER.debug("finished with socketHandler");
    }

    /**
     * Method that listen for new connections from Clients
     */
    public void startup() {

        while (running) {
            listenForConnection(server);
        }
        closeServer();
    }

    /**
     * Method that closes the Server.
     */
    public void closeServer() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

