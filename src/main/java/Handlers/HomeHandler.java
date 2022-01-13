package Handlers;

import Server.HTTPConstants;
import Server.ServerUtils;
import Util.HTTPRequest;
import Util.HTTPResponse;

import java.net.Socket;
import java.util.HashMap;

import static Dataset.CookiesDB.CookiesTable.*;
import static Server.HTTPConstants.NOT_UTHORIZED;
import static Util.HTMLExtra.*;

/**
 * HomeHandler Class.
 */
public class HomeHandler implements Handler {

    private HashMap<String, String> map;

    @Override
    public void handle(HTTPRequest request, HTTPResponse response, Socket socket) {

        map = ServerUtils.processHeaders(request.getInstream());

        if (checkCookie(map.get(HTTPConstants.COOKIE))) {

            if (request.getMethod().equals(HTTPConstants.GET)) {
                doGet(response);
            }

        } else {
            response.sendResponse(400);
            response.getWriter().write(NOT_UTHORIZED);
        }
    }


    /**
     * method that send the response for GET method.
     *
     * @param response
     */
    public void doGet(HTTPResponse response) {

        response.sendResponse(200);
        response.getWriter().write(getHomepage(returnName(map.get(HTTPConstants.COOKIE))));
    }
}
