package Util;


import Dataset.Configuration;
import Server.HTTPConstants;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Handlers.FindHandler.adjustTitle;

/**
 * ResponseUtils Class.
 */
public class ResponseUtils {

    private static Configuration config = new Configuration("configDB.txt");

    public static final String RESPONSE_200 = HTTPConstants.VERSION + " " + HTTPConstants.OK + "\n" + HTTPConstants.CONNECTION_CLOSE + "\n";

    public static final String RESPONSE_301 = HTTPConstants.VERSION + " " + HTTPConstants.REDIRECT + "\n" + "Location: http://localhost:1024/home" + "\n" + "Connection: Keep-Alive" + "\n";

    public static final String RESPONSE_400 = HTTPConstants.VERSION + " " + HTTPConstants.BAD + "\n" + HTTPConstants.CONNECTION_CLOSE + "\n";
    public static final String RESPONSE_403 = HTTPConstants.VERSION + " " + HTTPConstants.FORBIDDEN + "\n" + HTTPConstants.CONNECTION_CLOSE + "\n";
    public static final String RESPONSE_404 = HTTPConstants.VERSION + " " + HTTPConstants.NOT_FOUND + "\n" + HTTPConstants.CONNECTION_CLOSE + "\n";
    public static final String RESPONSE_405 = HTTPConstants.VERSION + " " + HTTPConstants.NOT_ALLOWED + "\n" + HTTPConstants.CONNECTION_CLOSE + "\n";

    public static final String RESPONSE_503 = HTTPConstants.VERSION + " " + HTTPConstants.SERVICE_UNAVAILABLE + "\n" + HTTPConstants.CONNECTION_CLOSE + "\n";
    public static final String RESPONSE_505 = HTTPConstants.VERSION + " " + HTTPConstants.HTTP_NOT_SUPORTED + "\n" + HTTPConstants.CONNECTION_CLOSE + "\n";

    /**
     * Method that returns the Response.
     *
     * @param instream
     * @param contentLength
     * @return
     */
    public static String getResponse(BufferedReader instream, int contentLength) {

        char[] bodyArr = new char[contentLength];
        try {
            instream.read(bodyArr, 0, bodyArr.length);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        String body = new String(bodyArr);
        return body;
    }

    /**
     * Returns Response from Slack.
     *
     * @param con
     * @return
     */
    public static String getResponseSlack(HttpsURLConnection con) {

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {

            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    /**
     * Returns the recipe elements from DB based on title that is requested by user.
     *
     * @param recipeName
     * @return
     */
    public static ArrayList<String> findDB(String recipeName) {
        ArrayList<String> recipes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword())) {

            Statement statement = conn.createStatement();
            String queryFindRecipe = "SELECT * FROM recipes2 WHERE title='" + recipeName + "';";

            ResultSet rs = statement.executeQuery(queryFindRecipe);

            if (rs.next()) {

                String title = rs.getString("title");
                String ingredients = rs.getString("ingredients");
                String instructions = rs.getString("instructions");
                String picture = rs.getString("picture");
                //System.out.format("%s, %s, %s, %s\n", title, ingredients, instructions, picture);

                recipes.add(adjustTitle(title));
                recipes.add(ingredients);
                recipes.add(instructions);
                recipes.add(picture);
            }
            statement.close();

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return recipes;
    }
}


