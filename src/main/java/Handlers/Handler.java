package Handlers;

import Util.HTTPRequest;
import Util.HTTPResponse;

import java.net.Socket;

/**
 * Handler Interface
 *
 * @author bnovakova
 */
public interface Handler {

    /**
     * Handle method.
     *
     */
    public void handle(HTTPRequest request, HTTPResponse response, Socket socket);
}
