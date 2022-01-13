package Dataset.RecipeDB;

import Dataset.RecipeDB.Recipe;
import Dataset.Utility;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that include a main method.
 *
 * @author bnovakova
 */
public class RecipeSearch {


    public static void main(String[] args) {

        System.out.println("Start!");

        HashMap<String, Recipe> recipeHM = new HashMap<>();
        Utility utility = new Utility();
        long startTime = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");

        String doc1 = "recipes_raw_nosource_epi.json";
        String doc2 = "recipes_raw_nosource_epi.json";
        String doc3 = "recipes_raw_nosource_fn.json";

        String[] files = new String[]{doc3};

        utility.readAndStore(files, recipeHM);
        System.out.println(recipeHM.size() + " recipes.");
        int counter = 1;
        for (Map.Entry<String, Recipe> entry : recipeHM.entrySet()) {
            System.out.println(counter + " title--> " + entry.getKey());
            counter = counter + 1;
        }

        //utility.write(recipeHM);

        //CommandLineInterface ui = new CommandLineInterface();
        //ui.executeUI(recipeHM);


        long endTime = System.currentTimeMillis();
        System.out.println("Execution of readData: " + formatter.format((endTime - startTime) / 1000d) + " seconds");
    }

}
