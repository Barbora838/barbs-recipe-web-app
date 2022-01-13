package Dataset.DB;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class DeleteDB {
    public static ArrayList<String> read() {

        ArrayList<String> list = new ArrayList<>();
        String dir = System.getProperty("user.dir");
        Path path;
        int counter = 0;


        path = Paths.get(dir + "/" + "delete.txt");


        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1)) {

            String line = null;
            while ((line = br.readLine()) != null) {

                list.add(line);
                System.out.println(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {

        ArrayList<String> list = DeleteDB.read();
        for (int i = 0; i < list.size(); i++) {
            String sql3 = "DELETE FROM recipes WHERE picture ='" + list.get(i) + "';";

           // String sql3 = "DELETE FROM recipes WHERE picture ='';";
            try {
                String jdbcURL = "jdbc:mysql://localhost:3306/mysql";
                String userName = "root";
                String pass = "kiklop123";

                Connection conn = DriverManager.getConnection(
                        jdbcURL, userName, pass);


                Statement statement = conn.createStatement();

                int res = statement.executeUpdate(sql3);
                System.out.println(res);

                statement.close();


            } catch (
                    Exception e) {
                e.printStackTrace();
            }
        }
    }


}
