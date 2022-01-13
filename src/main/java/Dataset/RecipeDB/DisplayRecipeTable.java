package Dataset.RecipeDB;

import Dataset.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * DisplayUsersTable Class
 */
public class DisplayRecipeTable {

    private Configuration config;

    public DisplayRecipeTable(Configuration config) {
        this.config = config;
    }

    /**
     * helper method that visualize users table.
     */
    public void displayRecipes() {
        try {
            String jdbcURL = this.config.getUrl();
            String userName = this.config.getRoot();
            String pass = this.config.getPassword();

            String query = "SELECT * FROM recipes2;";
            String command = "CREATE TABLE recipes2 (" +
                    "title varchar(255),\n" +
                    "ingredients varchar(1020),\n" +
                    "instructions varchar(2040),\n" +
                    "picture varchar(255)\n"
                    + ");";
            
            String update = "UPDATE recipes2 \n" +
                    "SET \n" +
                    "    picture = 'https://www.spendwithpennies.com/wp-content/uploads/2015/03/SpendWithPennies-Oven-Roasted-Potatoes-22.jpg'\n" +
                    "WHERE\n" +
                    "    title = 'roasted potatoes';";
            Connection conn = DriverManager.getConnection(jdbcURL, userName, pass);
            Statement statement = conn.createStatement();


            int row = statement.executeUpdate(update);
            ResultSet rs = statement.executeQuery(query);
            int count = 0;
            // iterate through the java resultset
            while (rs.next()) {

                String title = rs.getString("title");
                String ingredients = rs.getString("ingredients");
                String instructions = rs.getString("instructions");
                String picture = rs.getString("picture");
                System.out.println(count + " title --> " + title + "   pic: " + picture);
                //System.out.format("%s, %s, %s\n", ingredients, instructions, picture);
                count = count + 1;
            }

            statement.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }


    public static void main(String[] args) {

        Configuration config = new Configuration("configDB.txt");
        DisplayRecipeTable obj = new DisplayRecipeTable(config);
        obj.displayRecipes();
    }
}