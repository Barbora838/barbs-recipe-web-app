package Dataset.UserDB;

import Dataset.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class UserTable {

    /**
     * helper method that returns true if the password and username are in database, false otherwise
     *
     * @param userCredential
     * @return
     */
    public static boolean checkUserAndPassword(HashMap<String, String> userCredential) {
        Configuration config = new Configuration("configDB.txt");
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword())) {

            Statement statement = conn.createStatement();
            String query = "SELECT * FROM users WHERE username='" + userCredential.get("username") + "';";

            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {

                String username = rs.getString("username");
                String password = rs.getString("password");

                if (username.equals(userCredential.get("username")) && password.equals(userCredential.get("password"))) {
                    return true;
                }
            }
            statement.close();

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
