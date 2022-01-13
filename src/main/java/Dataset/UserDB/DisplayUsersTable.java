package Dataset.UserDB;

import Dataset.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * DisplayUsersTable Class
 */
public class DisplayUsersTable {

    private Configuration config;

    public DisplayUsersTable(Configuration config) {
        this.config = config;
    }

    /**
     * helper method that visualize users table.
     */
    public void displayUsers() {
        try {
            String jdbcURL = this.config.getUrl();
            String userName = this.config.getRoot();
            String pass = this.config.getPassword();

            String query = "SELECT * FROM users;";
            Connection conn = DriverManager.getConnection(jdbcURL, userName, pass);
            Statement statement = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = statement.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {

                String username = rs.getString("username");
                String password = rs.getString("password");

                // print the results
                System.out.format("%s, %s\n", username, password);
            }
            statement.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }


    public static void main(String[] args) {

        Configuration config = new Configuration("configDB.txt");
        DisplayUsersTable obj = new DisplayUsersTable(config);
        obj.displayUsers();
    }
}
