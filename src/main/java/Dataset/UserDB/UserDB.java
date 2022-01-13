package Dataset.UserDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UserDB {

    private static UserDB instance;
    private String nameTable;
    private String nameDB;
    private Statement statement;
    private Connection conn;


    public UserDB(String nameDB) {
        this.nameDB = nameDB;
        this.nameTable = null;

        try {

            //Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/users", "root", "kiklop123");
            this.statement = this.conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserDB getInstance(String db) {

        if (UserDB.instance == null) {
            UserDB.instance = new UserDB(db);
        }
        return UserDB.instance;
    }

    public String createTable(String name) {
        this.nameTable = name;
        String command = "CREATE TABLE " + name + "(" +
                "username varchar(255),\n" +
                "password varchar(255),\n" +
                ");";
        return command;
    }

    public String getElement(String elem) {

        String command = "SELECT " + elem + " FROM " + this.nameTable + ";";
        return command;
    }


    public String deleteTable(String tableName) {
        String command = "DROP TABLE " + tableName + ";";
        return command;
    }


    public String searchUser(String term) {
        String command = "SELECT username FROM " + this.nameTable + "WHERE username LIKE " + term + ";";
        return command;
    }

    public String showTable() {
        String command = "SHOW FULL users; ";
        return command;
    }

    public void insertIntoUsers(String user, String password) {
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/mysql";
            String userName = "root";
            String pass = "kiklop123";

            String sql = "INSERT INTO users (username, password) VALUES ('" + user + "', '" + password + "');";
            //String sql = "SHOW TABLES;";
            Connection conn = DriverManager.getConnection(jdbcURL, userName, pass);
            Statement statement = conn.createStatement();
            int row = statement.executeUpdate(sql);
            System.out.println(row);
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


