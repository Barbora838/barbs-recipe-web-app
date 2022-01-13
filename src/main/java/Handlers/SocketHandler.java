package Handlers;

import Server.HTTPConstants;
import Util.HTTPRequest;
import Util.HTTPResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class SocketHandler implements Runnable {

    private Socket socket;
    private HashMap<String, Handler> handlersHM;
    private static final Logger LOGGER = LogManager.getLogger(SocketHandler.class);

    /**
     * Constructor
     *
     * @param socket
     * @param handlersHM
     */
    public SocketHandler(Socket socket, HashMap<String, Handler> handlersHM) {
        this.socket = socket;
        this.handlersHM = handlersHM;
    }

    /**
     * This method validates requestLineParts and return true if size is 3, false otherwise.
     *
     * @param requestLineParts
     * @return
     */
    public boolean validateRequestLine(String[] requestLineParts) {
        return requestLineParts.length == 3;
    }

    /**
     * Method that return true if passed method is Get or Post, return false otherwise.
     *
     * @param method
     * @return
     */
    public boolean validateMethod(String method) {
        return method.equals(HTTPConstants.GET) || method.equals(HTTPConstants.POST);
    }

    @Override
    public void run() {

        // process request
        try (BufferedReader instream = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.ISO_8859_1));
             PrintWriter writer = new PrintWriter(socket.getOutputStream())) {

            HTTPResponse response = new HTTPResponse(writer);
            HTTPRequest request = new HTTPRequest(instream);

            LOGGER.info("Request: " + request.getRequestLine());

            if (request.getRequestLine() != (null)) {

                if (request.getRequestLine().equals("")) {
                    //Server cannot process the request due to a system overload; should be a temporary condition
                    response.sendResponse(503);

                } else {

                    //  validate request line & confirm it contains three substrings!
                    if (request.validateRequestLine()) {

                        LOGGER.debug("Method: " + request.getMethod());
                        LOGGER.debug("Path: " + request.getPath());
                        LOGGER.debug("Version: " + request.getVersion());

                       // System.out.println("Method: " + request.getMethod());
                       // System.out.println("Path: " + request.getPath());

                        Path filePath = Paths.get("." + request.getPath());

                        // only allow GET & POST requests
                        if (!validateMethod(request.getMethod())) {
                            // Method Not Allowed
                            response.sendResponse(405);

                        } else {

                            Handler handler;
                            String[] splitPath = request.getPath().split("/");
                            if (splitPath.length > 1) {
                                handler = this.handlersHM.get("/" + splitPath[1]);
                            } else {
                                handler = this.handlersHM.get("/");
                            }
                            if (handler == null) {
                                //	Requested file was not found
                                response.sendResponse(404);

                            } else {
                                handler.handle(request, response, socket);
                            }
                        }
                    } else {
                        // Request does not specify the file name, or the directory or the file does not have the permission that allows the pages to be viewed from the web
                        LOGGER.info(HTTPConstants.VALIDATION_FAILED);
                        response.sendResponse(403);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
