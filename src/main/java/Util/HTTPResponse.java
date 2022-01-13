package Util;

import Server.ServerUtils;

import java.io.PrintWriter;

/**
 * HTTPResponse class.
 */
public class HTTPResponse {

    private PrintWriter writer;

    /**
     * Constructor
     *
     * @param writer
     */
    public HTTPResponse(PrintWriter writer) {
        this.writer = writer;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    /**
     * Method that based on error code sends appropriate response
     *
     * @param errorCode
     */
    public void sendResponse(int errorCode) {

        switch (errorCode) {

            case 505:
                ServerUtils.send505(this.writer);
                break;
            case 503:
                ServerUtils.send503(this.writer);
                break;
            case 400:
                ServerUtils.send400(this.writer);
                break;
            case 405:
                ServerUtils.send405(this.writer);
                break;
            case 404:
                ServerUtils.send404(this.writer);
                break;
            case 403:
                ServerUtils.send403(this.writer);
                break;

            case 301:
                ServerUtils.send301(this.writer);
                break;
            default:
                ServerUtils.send200(this.writer);
                break;
        }
    }

    /**
     * Method that sends Cookie.
     *
     * @param cookie
     */
    public void sendCookie(String cookie) {
        ServerUtils.sendCookie(this.writer, cookie);
    }

    /**
     * Method that sends Name.
     *
     * @param name
     */
    public void sendUserName(String name) {
        ServerUtils.sendUserName(this.writer, name);
    }
}


