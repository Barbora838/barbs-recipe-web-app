package Dataset.DB;

import Dataset.Configuration;
import Dataset.RecipeDB.Recipe;
import Dataset.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CreateDB {


    public static void main(String[] args) {


        Configuration config = new Configuration("configDB.txt");

        HashMap<String, Recipe> recipeHM = new HashMap<>();
        Utility utility = new Utility();

        String doc1 = "recipes_raw_nosource_epi.json";
        String doc2 = "recipes_raw_nosource_epi.json";
        String doc3 = "recipes_raw_nosource_fn.json";

        String[] files = new String[]{doc3};

        utility.readAndStore(files, recipeHM);
        System.out.println(recipeHM.size() + " recipes.");

        int count = 0;
        for (Map.Entry<String, Recipe> elem : recipeHM.entrySet()) {

            try {
                String jdbcURL = config.getUrl();
                String userName = config.getRoot();
                String pass = config.getPassword();


                //if (count >= 0 && count < 100 && count < recipeHM.size()) {
                if(count < recipeHM.size()){
                    String sql = "INSERT INTO recipes2 (title, ingredients, instructions, picture) VALUES" + elem.getValue().toString().substring(0, elem.getValue().toString().length() - 2) + ";";
                    Connection conn = DriverManager.getConnection(jdbcURL, userName, pass);
                    Statement statement = conn.createStatement();
                    int row = statement.executeUpdate(sql);
                    statement.close();
                }
                count = count + 1;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("last count: "+count);
    }
}
