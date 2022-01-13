package Handlers;

import Dataset.Configuration;
import Server.HTTPConstants;
import Server.ServerUtils;
import Util.HTMLButtons;
import Util.HTTPRequest;
import Util.HTTPResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import static Server.HTTPConstants.*;
import static Server.ServerUtils.readBody;
import static Dataset.CookiesDB.CookiesTable.*;
import static Util.HTMLExtra.*;
import static Util.ResponseUtils.findDB;

/**
 * SearchHandler Class.
 */
public class SearchHandler implements Handler {

    private static final Logger LOGGER = LogManager.getLogger(FindHandler.class);
    private static Configuration config = new Configuration("configDB.txt");
    private ArrayList<String> tempSearch;

    public SearchHandler() {
        this.tempSearch = new ArrayList<>();
    }

    @Override
    public void handle(HTTPRequest request, HTTPResponse response, Socket socket) {


        HashMap<String, String> map = ServerUtils.processHeaders(request.getInstream());

        if (checkSessionIDAndCookie(map.get(HTTPConstants.SESSIONID), map.get(HTTPConstants.COOKIE))) {
            printCookiesTable();

            if (request.getMethod().equals(HTTPConstants.GET)) {
                doGet(response);

            } else if (request.getMethod().equals(HTTPConstants.POST)) {
                doPost(request, response, Integer.parseInt(map.get(HTTPConstants.CONTENT_LENGTH)));
            }
        } else {
            response.sendResponse(400);
            response.getWriter().write(returnModifiedHeader("NotAuthenticated"));
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
        response.getWriter().write(SEARCH_HTML);
    }

    /**
     * method that send the response for POST method.
     *
     * @param request
     * @param response
     * @param contentLength
     */
    public void doPost(HTTPRequest request, HTTPResponse response, int contentLength) {

        String bodyValue = readBody(request.getInstream(), contentLength, "query");
        System.out.println("BodyValue: " + bodyValue);

        if (!bodyValue.equals("wrong")) {
            ArrayList<String> result = searchDB(bodyValue);
            response.sendResponse(200);
            if (result.size() > 1) {
                response.getWriter().println(setResponse(result, bodyValue, "query"));
                // System.out.println("HTMLValidator.isValid(): " + HTMLValidator.isValid(setResponse(result, bodyValue, "query")));
            } else {
                response.getWriter().println(notFoundRecipe(bodyValue));
            }

        } else {
            response.sendResponse(400);
        }
    }

    /**
     * Method that set Response into correct format.
     *
     * @param result
     * @param bodyValue
     * @param tag
     * @return
     */
    public String setResponse(ArrayList<String> result, String bodyValue, String tag) {
        StringBuilder sb = new StringBuilder();
        sb.append(returnModifiedHeader("SearchResult"));
        sb.append(returnColorBackground("ffcccc"));
        sb.append(addingPicture("heart.jpg", 100, 100));
        sb.append("<h3>Results for " + tag + "=" + bodyValue + "</h3>\n");
        sb.append("<ul>\n");
        for (String res : result) {
            ArrayList<String> result2 = findDB(res);

            if (result2.size() == 4 && isValidISO(result2.get(0)) == true) {
                String returnString = getRecipePage(result2);
                this.tempSearch.add(returnString);
                String fixedName = updateFilesName(result2.get(0));
                writeHTML(returnString, fixedName);
                sb.append("<a href=/files/tempSearch/" + fixedName + ".html" + ">" + result2.get(0) + "</a><br>");
            }
        }
        sb.append("</ul>\n");
        sb.append(HTMLButtons.HOME_BUTTON);
        sb.append(HTTPConstants.PAGE_FOOTER);
        return sb.toString();
    }

    /**
     * Filter international characters in the title
     *
     * @param input
     * @return
     */
    private static boolean isValidISO(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        String content = new String(bytes, StandardCharsets.ISO_8859_1);
        return input.equals(content);
    }

    /**
     * Helper method for renaming the file's name.
     *
     * @param old
     * @return
     */
    public String updateFilesName(String old) {
        return old.replace(" ", "");
    }

    /**
     * Helper method for writing temp HTML files.
     *
     * @param html
     * @param name
     */
    public void writeHTML(String html, String name) {

        String outputFile = name + ".html";
        Path path2 = Paths.get("/Users/barbora_1/IdeaProjects/side-project-Barbora838/files/tempSearch" + "/" + outputFile);

        try (BufferedWriter br = Files.newBufferedWriter(path2)) {

            br.write(html);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Helper method that search recipe name in the database.
     *
     * @param recipeName
     * @return
     */
    public ArrayList<String> searchDB(String recipeName) {
        ArrayList<String> recipes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword())) {

            Statement statement = conn.createStatement();
            String querySearchRecipe = "SELECT title FROM recipes2 WHERE title LIKE '%" + recipeName + "%';";

            int row = 0;

            ResultSet rs = statement.executeQuery(querySearchRecipe);

            while (rs.next() && row < 30) {

                String title = rs.getString("title");
                recipes.add(title);
                row = row + 1;
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
