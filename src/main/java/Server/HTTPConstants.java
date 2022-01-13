package Server;

import Util.HTMLButtons;

import static Util.HTMLExtra.*;

/**
 * HttpConstants Class.
 * A helper class to store various constants used for the HTTP server.
 */
public class HTTPConstants {

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";

    public static final String VERSION = "HTTP/1.1";

    public static final String OK = "200 OK";
    public static final String REDIRECT = "301 Moved Permanently";

    public static final String BAD = "400 Bad Request";
    public static final String FORBIDDEN = "403 Forbidden";
    public static final String NOT_FOUND = "404 Not Found";
    public static final String NOT_ALLOWED = "405 Method Not Allowed";
    public static final String SERVICE_UNAVAILABLE = "503 Service Unavailable";
    public static final String HTTP_NOT_SUPORTED = "505 HTTP Version Not Supported";

    public static final String CONTENT_LENGTH = "Content-Length:";
    public static final String CONNECTION_CLOSE = "Connection: close";
    public static final String VALIDATION_FAILED = "Validation failed!";
    public static final String COOKIE = "Cookie:";
    public static final String USERNAME = "Username:";
    public static final String SESSIONID = "Session-ID:";

    public static final String FILE_PATH = "/files";
    public static final String IMAGE_PATH = "/files/images";

    public static final String NOT_FOUND_USER = returnModifiedHeader("NotFoundUser") +
            returnColorBackground("ffcccc") +
            addingPicture("notFound.png", 300, 200) +
            "  <p>User Not Found. Wrong credentials!</p>\n" +
            "\n" +
            HTMLButtons.LOGIN_BUTTON +
            HTTPConstants.PAGE_FOOTER;

    public static final String NOT_UTHORIZED = returnModifiedHeader("NotAuthorized") +
            returnColorBackground("ffcccc") +
            addingPicture("wrong.jpg", 300, 200) +
            "  <p>User Not Logged In!</p>\n" +
            "\n" +
            HTMLButtons.LOGIN_BUTTON +
            HTTPConstants.PAGE_FOOTER;

    public static final String PAGE_HEADER = "<!DOCTYPE html>\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head>\n" +
            "  <title>Log in with Slack</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n";

    public static final String PAGE_FOOTER = "\n" +
            "</body>\n" +
            "</html>";

    public static final String FIND_HTML = returnHeader("#fdff7d") +
            "<div class=\"w3-col m6 w3-padding-large w3-hide-small\">\n" +
            "            <img src=\"/files/images/find.jpg\" class=\"w3-round w3-image w3-opacity-min\" alt=\"find\" width=\"500\" height=\"400\"></img>\n" +
            "        </div>" +
            "<form action=\"/find\" method=\"post\">\n" +
            "   <label for=\"recipe\">" + "\n\n" + "Enter Exact Recipe Name</label><br/>\n" +
            "   <input type=\"text\" id=\"recipe\" name=\"recipe\"/><br/>\n" +
            "   <input type=\"submit\" value=\"Submit\"/>\n" +
            "</form>" + HTTPConstants.PAGE_FOOTER;

    public static final String SEARCH_HTML = returnHeader("#a6edf5") +
            "<div class=\"w3-col m6 w3-padding-large w3-hide-small\">\n" +
            "            <img src=\"/files/images/search.jpg\" class=\"w3-round w3-image w3-opacity-min\" alt=\"search\" width=\"500\" height=\"350\"></img>\n" +
            "        </div>" +
            "<form action=\"/search\" method=\"post\">\n" +
            "   <label for=\"query\"><h1>What are we cooking today?</h1>" + "\n" + "Enter Partial Recipe Name</label><br/>\n" +
            "   <input type=\"text\" id=\"query\" name=\"query\"/><br/>\n" +
            "   <input type=\"submit\" value=\"Submit\"/>\n" +
            "</form>" + HTTPConstants.PAGE_FOOTER;


    /**
     * Returns logouPage HTML
     *
     * @param name
     * @return
     */
    public static String getLogoutPage(String name) {
        return returnModifiedHeader("LogoutPage") +
                returnColorBackground("ffcccc") +
                addingPicture("bye.jpg", 700, 450) +
                "\n<h2> Thanks for visiting <i>" + name + "</i> !</h2>" +
                HTTPConstants.PAGE_FOOTER;
    }

    /**
     * REturns authorized HTML with user name.
     *
     * @param name
     * @return
     */
    public static String returnAuthorized(String name) {
        return returnModifiedHeader("Authorized") +
                returnColorBackground("ffcccc") +
                addingPicture("logged.jpg", 400, 280) +
                "  <p><h2><b>" + name + ",</b></h2><h3>You are already Logged In!</h3></p>\n" +
                "\n" +
                HTMLButtons.HOME_BUTTON +
                HTTPConstants.PAGE_FOOTER;
    }
}
