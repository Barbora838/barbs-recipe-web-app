package Dataset.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDB {



    public static void main(String[] args) {

        String sql1 = "CREATE TABLE recipes (title varchar(255),ingredients varchar(1020),instructions varchar(1020),picture varchar(255));";
        String sql2 = "INSERT INTO recipes (title, ingredients, instructions, picture) VALUES ('test', 'mushrooms', 'bake', 'null')";
        String sql3 = "DELETE FROM recipes WHERE picture ='gluten-free honey-nuts-n-oats pancake';";

        try {
            Connection conn = DBCPDataSource.getConnection();

            Statement statement = conn.createStatement();

//            int res = statement.executeUpdate(sql3);
//            System.out.println(res);

            String queryFindRecipe = "SELECT * FROM recipes WHERE title='spicy chicken and rice';";
            String querySearchRecipe = "SELECT * FROM recipes2 WHERE title LIKE '%spicy chicken and%';";
            String query = "select * from recipes";
            String update = "UPDATE recipes2 SET picture = 'https://theflavoursofkitchen.com/wp-content/uploads/2018/09/Sweet-Corn-Chicken-Soup-3.jpg' " +
                    "WHERE title = 'chilled corn soup with herbed chicken';";
            int row = 0;

            int rs = statement.executeUpdate(update);
            System.out.println(rs);
//            boolean find = true;
//            // iterate through the java result set
//            while (rs.next()) {
////                System.out.println("Recipe " + row +":");
//                // int id = rs.getInt("id");
//                String title = rs.getString("title");
//                System.out.println(title);
//                if (find) {
//                    if (row > 0 && row < 100) {
//                        String ingredients = rs.getString("ingredients");
//                        String instructions = rs.getString("instructions");
//                        String picture = rs.getString("picture");
//                        System.out.format("%s, %s, %s, %s\n", title, ingredients, instructions, picture);
//              //          System.out.println();
//                    }
//                } else {
//                    if (row < 20) {
//                        System.out.println(title);
//                    }
//                }
//                row = row + 1;
//            }
            statement.close();
            System.out.println("DataBase include " + row + " recipes!");


        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

}

