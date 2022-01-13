package Handlers;

import Server.HTTPConstants;
import Server.ServerUtils;
import Util.HTMLExtra;
import Util.HTTPRequest;
import Util.HTTPResponse;

import java.net.Socket;
import java.util.HashMap;

import static Dataset.CookiesDB.CookiesTable.returnName;

/**
 * CommingSoonHandler Class.
 */
public class CommingSoonHandler implements Handler {

    private HashMap<String, String> map;

    @Override
    public void handle(HTTPRequest request, HTTPResponse response, Socket socket) {
        map = ServerUtils.processHeaders(request.getInstream());

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
        response.getWriter().write(HTMLExtra.getCommingSoon(returnName(map.get(HTTPConstants.COOKIE))));
    }
}