package Handlers;

import Server.HTTPConstants;
import Server.ServerUtils;
import Util.HTMLButtons;
import Util.HTTPRequest;
import Util.HTTPResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Socket;
import java.util.HashMap;

import static Dataset.UserDB.UserTable.checkUserAndPassword;
import static Server.HTTPConstants.*;
import static Server.ServerUtils.readBody;
import static Dataset.CookiesDB.CookiesTable.*;
import static Util.HTMLExtra.*;

/**
 * LoginHandler Class.
 */
public class LoginHandler implements Handler {

    private static final Logger LOGGER = LogManager.getLogger(LoginHandler.class);
    private HashMap<String, String> map;

    @Override
    public void handle(HTTPRequest request, HTTPResponse response, Socket socket) {

        map = ServerUtils.processHeaders(request.getInstream());

        if (!checkSessionIDAndCookie(map.get(HTTPConstants.SESSIONID), map.get(HTTPConstants.COOKIE))) {
            printCookiesTable();

            if (request.getMethod().equals(HTTPConstants.GET)) {
                doGet(response);

            } else if (request.getMethod().equals(HTTPConstants.POST)) {
                doPost(request, response, Integer.parseInt(map.get(HTTPConstants.CONTENT_LENGTH)));
            }

        } else {
            response.sendResponse(200);
            response.getWriter().write(returnModifiedHeader("NotAuthenticated"));
            String user = getUsername(map.get(HTTPConstants.SESSIONID));
            response.getWriter().write(returnAuthorized(user));
        }
    }

    /**
     * method that send the response for GET method.
     *
     * @param response
     */
    public void doGet(HTTPResponse response) {
        response.sendResponse(200);
        response.getWriter().write(LOGIN_HTML);
    }

    /**
     * method that send the response for POST method.
     *
     * @param request
     * @param response
     * @param contentLength
     */
    public void doPost(HTTPRequest request, HTTPResponse response, int contentLength) {

        String bodyValue = readBody(request.getInstream(), contentLength, "username");

        if (!bodyValue.equals("wrong")) {

            response.sendResponse(200);
            HashMap<String, String> userCredential = separateUsernameAndPassword(bodyValue);

            // check the database if user exist and if the password is correct
            if (checkUserAndPassword(userCredential)) {
                String userName = userCredential.get("username");
                handleCookies(response, map.get(HTTPConstants.SESSIONID), userName);
                response.getWriter().println(HTTPConstants.PAGE_HEADER);
                response.getWriter().println(addingPicture("fruit.jpg", 450,300));
                response.getWriter().println(returnColorBackground("ffcccc"));
                response.getWriter().println("<h1> Hello <b>" + userName + "<b></h1>");
                response.getWriter().println(HTMLButtons.HOME_BUTTON);
                response.getWriter().println(HTTPConstants.PAGE_FOOTER);

            } else {
                response.getWriter().println(HTTPConstants.NOT_FOUND_USER);
            }

        } else {
            response.sendResponse(400);
        }
    }

    /**
     * Helper method that gets username and password into hashMap.
     *
     * @param bodyValue
     * @return
     */
    public HashMap<String, String> separateUsernameAndPassword(String bodyValue) {
        HashMap<String, String> userCredential = new HashMap<>();
        String[] arr = bodyValue.split("&");
        userCredential.put("username", arr[0]);
        String[] arr2 = arr[1].split("=");
        if (arr2.length > 1) {
            userCredential.put("password", arr2[1]);
        }
        return userCredential;
    }
}
