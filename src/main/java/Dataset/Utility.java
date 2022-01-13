package Dataset;

import Dataset.RecipeDB.Recipe;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Utility {


    public void readAndStore(String[] files, HashMap<String, Recipe> recipeHM) {

        String dir = System.getProperty("user.dir");
        Gson gson = new Gson();
        Path path;
        int counter = 0;

        for (String inputFile : files) {
            path = Paths.get(dir + "/" + inputFile);


            try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1)) {

                Recipe recipe;
                String line = null;
                String lineConc = null;
                while ((line = br.readLine()) != null) {
                    lineConc += line;
                    if (lineConc.endsWith("{")) {
                        lineConc = "{";
                    }
                    if (lineConc.length() > 2) {
                        if (lineConc.endsWith("},") || lineConc.endsWith("}")) {

                            String newLine = lineConc.replace("   ", "");

                            if (lineConc.endsWith("},")) {
                                newLine = newLine.substring(0, newLine.length() - 1);
                            }

                            try {
                                recipe = gson.fromJson(newLine, Recipe.class);
                                if (recipe.getTitle() != null) {
                                    String title = recipe.getTitle().toLowerCase(Locale.ROOT);
                                    //recipe.setTitle(title.substring(0, title.length() - 1));

                                    recipe.setTitle(title.substring(0, title.length()));
                                    recipeHM.put(recipe.getTitle(), recipe);
                                    counter++;
                                }
                            } catch (com.google.gson.JsonSyntaxException ignore) {
                                System.out.println("JsonSyntaxException ignored");
                            }
                            lineConc = "";
                        }
                    }
                }
            } catch (IOException ioe) {
                System.out.println("File " + inputFile + " not found. " + ioe.getMessage());
                System.out.println("Please, try again with correct filename!");
                System.exit(0);
            }
        }
    }

    public void write(HashMap<String, Recipe> recipeHM) {

        String outputFile = "recipeDataset.txt";
        String dir = System.getProperty("user.dir");
        Path path2 = Paths.get(dir + "/" + outputFile);
        String finalWord = "";
        int count = 0;


        try (BufferedWriter br = Files.newBufferedWriter(path2)) {
            for (Map.Entry<String, Recipe> elem : recipeHM.entrySet()) {
                if (count < 1000) {

                    elem = cleanData(elem);

                    String[] trim = elem.toString().split("=");
                    finalWord = trim[1].replace("\n", "");

                    br.write(finalWord);
                    br.write("\n" + "\n");
                    count = count + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map.Entry<String, Recipe> cleanData(Map.Entry<String, Recipe> elem ){

        ArrayList<String> list = new ArrayList<>();
        String prohibitedChars = "({['']})";

        elem.getValue().setTitle(elem.getValue().getTitle().replace(prohibitedChars, ""));
        if (!elem.getValue().getInstructions().equals(null)) {
            for (int i = 0; i < elem.getValue().getIngredients().size(); i++) {
                list.add(elem.getValue().getIngredients().get(i).replace(prohibitedChars, ""));

            }
        }
        elem.getValue().setIngredients(list);
        elem.getValue().setInstructions(elem.getValue().getInstructions().replace(prohibitedChars, ""));
        // elem.getValue().setPictureLink(elem.getValue().getPictureLink().replace(prohibitedChars, ""));
        return  elem;
    }



}
