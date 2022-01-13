package Handlers;

import Server.HTTPConstants;
import Server.ServerUtils;
import Util.HTTPRequest;
import Util.HTTPResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static Server.ServerUtils.handleHtmlFile;


/**
 * FileHandler Class.
 */
public class FileHandler implements Handler {

    private static final Logger LOGGER = LogManager.getLogger(FileHandler.class);
    private List<String> messages;

    /**
     * Constructor
     */
    public FileHandler() {
        this.messages = new ArrayList<>();
    }


    @Override
    public void handle(HTTPRequest request, HTTPResponse response, Socket socket) {

        //int contentLength = ServerUtils.processHeaders(instream);
        if (request.getMethod().equals(HTTPConstants.GET)) {
            doGet(request, response, socket);
        }
    }

    /**
     * method that send the response for GET method.
     *
     * @param request
     * @param response
     * @param socket
     */
    public void doGet(HTTPRequest request, HTTPResponse response, Socket socket) {

        Path filePath = Paths.get("." + request.getPath());

        if (request.getPath().startsWith(HTTPConstants.IMAGE_PATH) && Files.isRegularFile(filePath)) {
            handleImageFile(response, request.getPath(), socket);
        }
        // only serve files from a single path
        else if (request.getPath().startsWith(HTTPConstants.FILE_PATH) && Files.isRegularFile(filePath)) {

            handleHtmlFile(response, filePath);
        }
    }

    /**
     * Method that handle Image file.
     *
     * @param response
     * @param path
     * @param socket
     */
    public static void handleImageFile(HTTPResponse response, String path, Socket socket) {

        try (OutputStream writer = socket.getOutputStream()) {
            response.sendResponse(200);
            ServerUtils.send200JPG(writer);

            String dir = System.getProperty("user.dir");

            File f = new File(dir + path);
            InputStream input = new FileInputStream(f);

            byte[] buf = new byte[8192];
            int n;
            while ((n = input.read(buf)) != -1) {
                writer.write(buf, 0, n);
            }

        } catch (IOException ioe) {
            //file was not found -> send 404
            System.out.println("File not found -- " + ioe.getMessage());
            LOGGER.info("File not found -- " + ioe.getMessage());
            response.sendResponse(404);
        }
    }

}