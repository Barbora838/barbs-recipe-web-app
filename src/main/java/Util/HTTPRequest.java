package Util;


import java.io.BufferedReader;
import java.io.IOException;

/**
 * HTTPRequest Class.
 */
public class HTTPRequest {

    private BufferedReader instream;
    private String requestLine;
    private String[] requestLineParts;

    private String method;
    private String path;
    private String version;

    /**
     * Constructor.
     *
     * @param instream
     */
    public HTTPRequest(BufferedReader instream) {
        this.instream = instream;
        try {
            this.requestLine = instream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.requestLineParts = requestLine.split("\\s");

        if (validateRequestLine()) {
            this.method = requestLineParts[0];
            this.path = requestLineParts[1];
            this.version = requestLineParts[2];
        }
    }

    /**
     * Constructor.
     *
     * @param line
     */
    public HTTPRequest(String line) {

        this.requestLineParts = line.split("\\s");

    }

    /**
     * Return BufferedReader.
     *
     * @return
     */
    public BufferedReader getInstream() {
        return instream;
    }

    /**
     * Set instream.
     *
     * @param instream
     */
    public void setInstream(BufferedReader instream) {
        this.instream = instream;
    }

    /**
     * Return requestLine.
     *
     * @return
     */
    public String getRequestLine() {
        return requestLine;
    }

    /**
     * Set requestLine
     *
     * @param requestLine
     */
    public void setRequestLine(String requestLine) {
        this.requestLine = requestLine;

    }

    /**
     * This method validates requestLineParts and return true if size is 3, false otherwise.
     *
     * @return
     */
    public boolean validateRequestLine() {
        return this.requestLineParts.length == 3;
    }

    /**
     * Return Method
     *
     * @return
     */
    public String getMethod() {
        return method;
    }

    /**
     * Return Path
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * Return Version
     *
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     * Set Path
     *
     * @return
     */
    public void setPath(String newPath) {
        this.path = newPath;
    }
}

