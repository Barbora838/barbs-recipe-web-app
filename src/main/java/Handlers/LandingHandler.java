package Handlers;

import Server.HTTPConstants;
import Util.HTMLExtra;
import Util.HTTPRequest;
import Util.HTTPResponse;

import java.net.Socket;


/**
 * LandingHandler Class.
 */
public class LandingHandler implements Handler {


    @Override
    public void handle(HTTPRequest request, HTTPResponse response, Socket socket) {

        if (request.getMethod().equals(HTTPConstants.GET)) {
            doGet(request, response);
        }

    }

    /**
     * method that send the response for GET method.
     *
     * @param request
     * @param response
     */
    protected void doGet(HTTPRequest request, HTTPResponse response) {

        response.sendResponse(200);
        response.getWriter().write(HTMLExtra.getLandingPage());
    }
}