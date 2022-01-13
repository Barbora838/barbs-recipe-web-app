package Dataset.CookiesDB;

import Dataset.Configuration;
import Util.HTTPResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

/**
 * CookiesUtil Class.
 */
public class CookiesTable {

    private static Configuration config = new Configuration("configDB.txt");

    /**
     * Method that cretes and saves cookies into table.
     *
     * @param response
     */
    public static void handleCookies(HTTPResponse response, String sessionID, String name) {
        String cookie = generateRandomString();
        response.sendCookie(cookie);
        insertCookie(cookie, sessionID, name);
    }

    // used from this website: https://www.baeldung.com/java-random-string
    public static String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    /**
     * Method that inserts Cookie into table.
     *
     * @param cookie
     * @param sessionID
     * @param name
     */
    public static void insertCookie(String cookie, String sessionID, String name) {

        String query = "INSERT INTO cookies (cookie, session_id, name) VALUES ('" + cookie + "'" + ", '" + sessionID + "'" + ", '" + name + "'" + ")";
        System.out.println(query);
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword())) {
            Statement statement = conn.createStatement();
            int res = statement.executeUpdate(query);
            statement.close();

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUsername(String sessionID){
        String name = "";
        String query = "SELECT name from cookies WHERE session_id='" + sessionID + "'";
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword())) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("name");
            }
            statement.close();

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * Method that returns name from table.
     *
     * @param cookies
     */
    public static String returnName(String cookies) {
        String name = "";
        String query = "SELECT name from cookies WHERE cookie='" + cookies + "'";
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword())) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("name");
            }
            statement.close();

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return name;
    }


    /**
     * method that check if the cookies is present in the table and return true, and false otherwise.
     *
     * @param cookie
     * @return
     */
    public static boolean checkSessionIDAndCookie(String sessionID, String cookie) {
        String query = "SELECT * FROM cookies;";
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword())) {
            System.out.println("Database connected!");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String cookieDB = rs.getString("cookie");
                String sessionDB = rs.getString("session_id");
                if (sessionDB.equals(sessionID) && cookieDB.equals(cookie)) {
                    System.out.println("Cookie found");
                    return true;
                }
            }
            statement.close();

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        System.out.println("Cookie NOT found");
        return false;
    }

    /**
     * method that check if the cookies is present in the table and return true, and false otherwise.
     *
     * @param cookie
     * @return
     */
    public static boolean checkCookie(String cookie) {
        String query = "SELECT * FROM cookies;";
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword())) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String cookie1 = rs.getString("cookie");
                if (cookie1.equals(cookie)) {
                    System.out.println("Cookie found" + cookie1);
                    return true;
                }
            }
            statement.close();

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        System.out.println("Cookie NOT found");
        return false;
    }

    /**
     * Delete cookie from the table.
     *
     * @param cookie
     */
    public static void deleteCookie(String cookie) {
        String query = "DELETE FROM cookies WHERE cookie = '" + cookie + "'";
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword())) {
            Statement statement = conn.createStatement();
            int res = statement.executeUpdate(query);
            System.out.println(res + " deleted");
            statement.close();

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints elements in the cookies table.
     */
    public static void printCookiesTable() {
        System.out.println("from printCookiesTable()");
        String query = "SELECT * FROM cookies;";
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword())) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int count = 0;
            while (rs.next()) {
                String cookie1 = rs.getString("cookie");
                String name = rs.getString("name");
                String sessionID = rs.getString("session_id");
                count = count + 1;
                System.out.println(count + " @@@@@@@ printing @@@@@@@@ COOKIE: " + cookie1 + " NAME: " + name + " sessionID: " + sessionID);
            }

            if (count == 0) {
                System.out.println("cookie table is empty!");
            }

            statement.close();

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates cookies table with a name of the user
     *
     * @param name
     * @param cookie
     */
    public static void updateName(String name, String cookie) {
        String query = "UPDATE cookies SET name='" + name + "' WHERE cookie='" + cookie + "'";
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword())) {
            Statement statement = conn.createStatement();
            int res = statement.executeUpdate(query);
            System.out.println(res);
            statement.close();

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        deleteCookie("pzwzszujmg");
//        updateName("barb1", "tkbahvpxpa");
        printCookiesTable();

    }
}
