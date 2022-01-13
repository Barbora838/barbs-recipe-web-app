package Dataset.RecipeDB;

import Dataset.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RecipeDatabase {

    private static RecipeDatabase instance;
    private static Configuration config = new Configuration("configDB.txt");
    private String nameTable;
    private String nameDB;
    private Statement statement;
    private Connection conn;


    public RecipeDatabase(String nameDB) {
        this.nameDB = nameDB;
        this.nameTable = null;

        try {

            //Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(config.getUrl(), config.getRoot(), config.getPassword());
            this.statement = this.conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RecipeDatabase getInstance(String db) {

        if (RecipeDatabase.instance == null) {
            RecipeDatabase.instance = new RecipeDatabase(db);
        }
        return RecipeDatabase.instance;
    }

    public String createTable(String name) {
        this.nameTable = name;
        String command = "CREATE TABLE " + name + "(" +
                "title varchar(255),\n" +
                "ingredients varchar(1020),\n" +
                "instructions varchar(1020),\n" +
                "picture varchar(255)\n"
                + ");";
        return command;
    }

    public String getElement(String elem) {

        String command = "SELECT " + elem + " FROM " + this.nameTable + ";";
        return command;
    }


    public String insertDB(String element) {

        String command = "INSERT INTO " + this.nameTable + " (title, ingredients, instructions, picture) VALUES " + element + ";";
        return command;
    }

    public String deleteTable(String tableName) {
        String command = "DROP TABLE " + tableName + ";";
        return command;
    }

    /**
     * show databases;
     * use recipesDB;
     * INSERT INTO recipes VALUES ('cranberry martin','[3 large baking potatoes, peeled, 3/4 cup clarified butter, 1/2 teaspoon salt, 1/4 teaspoon freshly ground black pepper, 1-ounce cranberry juice, 1/2-ounce Chambord, 1-ounce vanilla vodka, Fresh cranberries, for garnish]','Combine all the ingredients in a cocktail shaker filled with ice. Shake and serve in a chilled martini glass. Garnish with fresh cranberries skewered on a cocktail skewer.','null')
     */


    public String findTitle(String term) {
        String command = "SELECT ingredients, instructions, picture\n" +
                "FROM " + this.nameTable +
                " WHERE title =" + term + ";";
        return command;
    }

    public String searchRecipe(String term) {
        String command = "SELECT title FROM " + this.nameTable + "WHERE title LIKE " + term + ";";
        return command;
    }

    public String showTable() {
        String command = "SHOW FULL recipes; ";
        return command;
    }

    public String deleteRow(String id) {
        String command = "DELETE FROM recipes \n" +
                "WHERE id = " + id + ";";
        return command;
    }

}
