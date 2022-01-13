package Handlers;

import Server.HTTPConstants;
import Server.ServerUtils;
import Util.HTTPRequest;
import Util.HTTPResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import static Server.HTTPConstants.*;
import static Server.ServerUtils.readBody;
import static Dataset.CookiesDB.CookiesTable.*;
import static Util.HTMLExtra.*;
import static Util.ResponseUtils.findDB;

public class FindHandler implements Handler {

    private static final Logger LOGGER = LogManager.getLogger(FindHandler.class);

    /**
     * GET /find
     * This request will return a web page containing, at minimum, a text box and button. When the user enters a message in
     * the text box and clicks the button the POST method described below will be called.
     * <p>
     * POST /find
     * The body of this request will look as follows: asin=123456789 where the value is a URL-encoded string.
     * <p>
     * You will return a web page listing all of the review and qa documents with the provided ASIN.
     */

    @Override
    public void handle(HTTPRequest request, HTTPResponse response, Socket socket) {

        HashMap<String, String> map = ServerUtils.processHeaders(request.getInstream());

        if (checkSessionIDAndCookie(map.get(HTTPConstants.SESSIONID), map.get(HTTPConstants.COOKIE))) {

            if (request.getMethod().equals(HTTPConstants.GET)) {
                doGet(response);

            } else if (request.getMethod().equals(HTTPConstants.POST)) {
                doPost(request, response, Integer.parseInt(map.get(HTTPConstants.CONTENT_LENGTH)));
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
        response.getWriter().write(FIND_HTML);
    }

    /**
     * method that send the response for POST method.
     *
     * @param request
     * @param response
     * @param contentLength
     */
    public void doPost(HTTPRequest request, HTTPResponse response, int contentLength) {

        String bodyValue = readBody(request.getInstream(), contentLength, "recipe");
        System.out.println("BodyValue: " + bodyValue);

        if (!bodyValue.equals("wrong")) {
            ArrayList<String> result = findDB(bodyValue);
            response.sendResponse(200);

            if (result.size() == 4) {
                response.getWriter().println(getRecipePage(result));
            } else {
                response.getWriter().println(returnColorBackground("ffcccc"));
                response.getWriter().println(notFoundRecipe(bodyValue));
            }
        } else {
            response.sendResponse(400);
        }
    }


    /**
     * Helper method for Adjusting Title.
     *
     * @param title
     * @return
     */
    public static String adjustTitle(String title) {
        String newString = "";
        String[] res = title.split(" ");
        for (int i = 0; i < res.length; i++) {
            char letter = res[i].substring(0, 1).charAt(0);
            if (Character.isLowerCase(letter)) {
                String capital = String.valueOf(letter).toUpperCase();
                newString += capital + res[i].substring(1, res[i].length());
            }
            newString += " ";
        }

        if (newString.equals("  ")) {
            return title;
        }
        return newString;
    }
}
