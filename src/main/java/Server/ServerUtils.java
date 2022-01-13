package Server;

import Util.HTTPResponse;
import Util.ResponseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ServerUtils Class.
 */
public class ServerUtils {

    /**
     * Send the status line of an HTTP 200 OK response.
     *
     * @param writer
     */
    public static void send200(PrintWriter writer) {
        writer.printf(ResponseUtils.RESPONSE_200);
    }


    /**
     * Send the status line of an HTTP 400 Bad response.
     *
     * @param writer
     */
    public static void send400(PrintWriter writer) {
        writer.printf(ResponseUtils.RESPONSE_400);
    }

    /**
     * Send the status line of an HTTP 403 Forbidden.
     *
     * @param writer
     */
    public static void send403(PrintWriter writer) {
        writer.printf(ResponseUtils.RESPONSE_403);
    }

    /**
     * Send the status line of an HTTP 403 Forbidden.
     *
     * @param writer
     */
    public static void send301(PrintWriter writer) {
        writer.printf(ResponseUtils.RESPONSE_301);
    }

    /**
     * Send the status line of an HTTP 404 Not Found response.
     *
     * @param writer
     */
    public static void send404(PrintWriter writer) {
        writer.printf(ResponseUtils.RESPONSE_404);
    }

    /**
     * Send the status line of an HTTP 405 Method Not Allowed response.
     *
     * @param writer
     */
    public static void send405(PrintWriter writer) {
        writer.printf(ResponseUtils.RESPONSE_405);
    }

    /**
     * Send the status line of an HTTP 503 SERVICE UNAVAILABLE
     *
     * @param writer
     */
    public static void send503(PrintWriter writer) {
        writer.printf(ResponseUtils.RESPONSE_503);
    }

    /**
     * Send the status line of an HTTP 505 SERVICE UNAVAILABLE
     *
     * @param writer
     */
    public static void send505(PrintWriter writer) {
        writer.printf(ResponseUtils.RESPONSE_505);
    }

    /**
     * Send the status line of 200 for the content-type image.
     *
     * @param writer
     * @throws IOException
     */
    public static void send200JPG(OutputStream writer) throws IOException {
        writer.write("HTTP/1.1 200 OK\n".getBytes(StandardCharsets.UTF_8));
        writer.write("Content-Type: image/jpeg; charset=utf-8\n\n".getBytes(StandardCharsets.UTF_8));
    }


    public static void sendCookie(PrintWriter writer, String cookie) {
        writer.printf(HTTPConstants.VERSION + " " + HTTPConstants.OK + "\n" + "Set-Cookie: myCookie=" + cookie + "\n" + HTTPConstants.CONNECTION_CLOSE + "\n");
    }

    public static void sendUserName(PrintWriter writer, String name) {
        writer.printf(HTTPConstants.VERSION + " " + HTTPConstants.OK + "\n" + "Set-Cookie: myName=" + name + "\n" + HTTPConstants.CONNECTION_CLOSE + "\n");
    }

    /**
     * This method processesHeader and return the integer contentLength that is in the CONTENT_LENGTH Header.
     *
     * @param instream
     * @return
     */
    public static HashMap<String, String> processHeaders(BufferedReader instream) {

        int contentLength = 0;
        HashMap<String, String> map = new HashMap<>();
        List<String> headers = new ArrayList<>();
        String header = null;
        while (true) {
            try {
                if (!!(header = instream.readLine()).isEmpty()) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            headers.add(header);
            //System.out.println(header);

            if (header.startsWith(HTTPConstants.CONTENT_LENGTH)) {
                String[] contentLengthParts = header.split("\\s");
                contentLength = Integer.parseInt(contentLengthParts[1]);
                map.put(HTTPConstants.CONTENT_LENGTH, String.valueOf(contentLength));
            }

            if (header.startsWith(HTTPConstants.COOKIE)) {
                System.out.println(header);
                String[] contentLengthParts = header.split(";");

                for (String contentLengthPart : contentLengthParts) {

                    if (contentLengthPart.startsWith(" myCookie")) {
                        String[] split = contentLengthPart.split("=");
                        map.put(HTTPConstants.COOKIE, split[1]);
                        System.out.println("Server Utils-> myCookie: " + split[1]);
                    }
                    if (contentLengthPart.startsWith(" myName")) {
                        String[] split = contentLengthPart.split("=");
                        map.put(HTTPConstants.USERNAME, split[1]);
                        System.out.println("Server Utils-> myName: " + split[1]);
                    }

                    if (contentLengthPart.startsWith(" JSESSIONID")) {
                        String[] split = contentLengthPart.split("=");
                        map.put(HTTPConstants.SESSIONID, split[1]);
                        System.out.println("Server Utils-> Session-ID: " + split[1]);
                    }
                }
            }
        }
        return map;
    }

    /**
     * Set Logger if passed true.
     *
     * @param flag
     */
    public static void setLogger(boolean flag) {
        if (flag) {
            LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
            File file = new File("/Users/barbora_1/IdeaProjects/side-project-Barbora838/log4j2.xml");
            // this will force a reconfiguration
            context.setConfigLocation(file.toURI());
        }
    }

    /**
     * This method reads the body of the message that has contentLength
     *
     * @param instream
     * @param contentLength
     * @return
     */
    public static String readBody(BufferedReader instream, int contentLength, String type) {

        char[] bodyArr = new char[contentLength];
        try {
            instream.read(bodyArr, 0, bodyArr.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String body = new String(bodyArr);
        String bodyValue = null;
        try {

            String firstPart = body.substring(0, body.indexOf("="));
            if (!firstPart.equals(type)) {
                return "wrong";
            }
            bodyValue = URLDecoder.decode(body.substring(body.indexOf("=") + 1, body.length()), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bodyValue;
    }

    /**
     * Method that handles HtmlFile.
     *
     * @param response
     * @param filePath
     */
    public static void handleHtmlFile(HTTPResponse response, Path filePath) {

        // assume that the path is releative to the current directory
        try (BufferedReader file = Files.newBufferedReader(filePath)) {
            // file was found -> send 200 OK status line
            response.sendResponse(200);

            // write file contents
            String line;
            while ((line = file.readLine()) != null) {
                response.getWriter().println(line);
            }

        } catch (IOException ioe) {
            //file was not found -> send 404
            response.sendResponse(404);
        }
        response.getWriter().close();
    }
}