package Dataset;

import Dataset.RecipeDB.Recipe;

import java.util.*;

/**
 * CommandLineInterface class
 *
 * @author bnovakova
 */
public class CommandLineInterface {

    /**
     * This method executes UI
     *
     * @param recipeHM
     */
    public void executeUI(HashMap<String, Recipe> recipeHM) {
        String command;
        System.out.println("Please enter next Command:");
        Scanner reader = new Scanner(System.in);
        command = reader.nextLine();
        String recipe = "";

        while (!command.equals("exit")) {

            String[] split = command.split(" ");

            if (checkInputParameters(split)) {

                recipe = getCorrectTerm(split);

                switch (split[0]) {

                    case "find" -> find(recipe, recipeHM);
                    case "search" -> search(recipe, recipeHM);

                    default -> System.out.println("Incorrect command, please try again!");
                }
            }
            System.out.println("Enter your next Recipe or type exit to quit");
            command = reader.nextLine();
        }
    }

    /**
     * Given the Recipe name displays the recepie Object.
     */
    public static void find(String recipe, HashMap<String, Recipe> recipeHM) {

        if (recipeHM.containsKey(recipe)) {
            System.out.println(recipeHM.get(recipe));
        } else {
            System.out.println("Recipe not found.");
        }
    }

    /**
     * Given a one-word or multiple-word term, display a 10 random Recipe containing this term in the title.
     */
    public static void search(String recipe, HashMap<String, Recipe> recipeHM) {

        boolean flag = false;
        int countRecipe = 0;
        // https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        // Used online resource to understand how to stript all punctuation
        String newWord = recipe.replaceAll("\\p{Punct}", "").toLowerCase(Locale.ROOT);

        for (Map.Entry<String, Recipe> elem : recipeHM.entrySet()) {
            if (elem.getKey().contains(newWord) && countRecipe < 10) {
                flag = true;
                System.out.println(elem);
                countRecipe = countRecipe + 1;
            }
        }

        System.out.println("Found " + countRecipe + " Recipes for findPartial");
        if (!flag) {
            System.out.println(recipe + " not found for function findPartial!");
        }
    }


    /**
     * Helper method that check the input parameters from the user. if they are correct return true otherwise return false.
     *
     * @param userInput
     * @return
     */
    public boolean checkInputParameters(String[] userInput) {
        if (userInput.length == 1) {
            System.out.println("Incorrect number of parameters.");
            return false;
        }
        return true;
    }

    /**
     * Combine multiple term word into one.
     *
     * @param split
     * @return
     */
    public String getCorrectTerm(String[] split) {
        String term = "";
        for (int i = 1; i < split.length; i++) {
            term += split[i];

            if (i < split.length - 1) {
                term += " ";
            }
        }
        return term;
    }
}

