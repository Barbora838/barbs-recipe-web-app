package Dataset.RecipeDB;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    /**
     * {
     * "rmK12Uau.ntP510KeImX506H6Mr6jTu": {
     * "title": "Slow Cooker Chicken and Dumplings",
     * "ingredients": [
     * "4 skinless, boneless chicken breast halves ADVERTISEMENT",
     * "2 tablespoons butter ADVERTISEMENT",
     * "2 (10.75 ounce) cans condensed cream of chicken soup ADVERTISEMENT",
     * "1 onion, finely diced ADVERTISEMENT",
     * "2 (10 ounce) packages refrigerated biscuit dough, torn into pieces ADVERTISEMENT",
     * "ADVERTISEMENT"
     * ],
     * "instructions": "Place the chicken, butter, soup, and onion in a slow cooker, and fill with enough water to cover.\nCover, and cook for 5 to 6 hours on High. About 30 minutes before serving, place the torn biscuit dough in the slow cooker. Cook until the dough is no longer raw in the center.\n",
     * "picture_link": "55lznCYBbs2mT8BTx6BTkLhynGHzM.S"
     * },
     */

    private String title;
    private ArrayList<String> ingredients;
    private String instructions;
    private String picture_link;

    public Recipe(String title, ArrayList<String> ingredients, String instructions, String pictureLink) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.picture_link = pictureLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPictureLink() {
        return picture_link;
    }

    public void setPictureLink(String pictureLink) {
        this.picture_link = pictureLink;
    }

    @Override
    public String toString() {
        return "('" + title + "','" + ingredients + "','" + instructions + "','" + picture_link + "')," + "\n";
    }
}
