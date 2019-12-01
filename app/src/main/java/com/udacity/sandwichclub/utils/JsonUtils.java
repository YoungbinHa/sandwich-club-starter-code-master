package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject sandwichDetailJsonObject = new JSONObject(json);
        Sandwich sandwich = new Sandwich();

        // 1. get the Json object representing "name" and set values in sandwich object
        JSONObject name = sandwichDetailJsonObject.getJSONObject("name");
        sandwich.setMainName(name.getString("mainName"));
        JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
        List<String> alsoKnownAsList = new ArrayList<>();
        for (int i = 0; i < alsoKnownAs.length(); i++) {
            alsoKnownAsList.add(alsoKnownAs.get(i).toString());
        }
        sandwich.setAlsoKnownAs(alsoKnownAsList);

        // 2. get the Json Object representing "placeOfOrigin" and the value in sandwich object
        String placeOfOrigin = sandwichDetailJsonObject.getString("placeOfOrigin");
        sandwich.setPlaceOfOrigin(placeOfOrigin);

        // 3. get the Json Object representing "description" and the value in sandwich object
        String description = sandwichDetailJsonObject.getString("description");
        sandwich.setDescription(description);

        // 4. get the Json Object representing "image" and set the value in sandwich object
        String image = sandwichDetailJsonObject.getString("image");
        sandwich.setImage(image);

        // 4. get the Json Object representing "ingredients" and set values in sandwich object
        JSONArray ingredients = sandwichDetailJsonObject.getJSONArray("ingredients");
        List<String> ingredientsList = new ArrayList<>();
        for (int i = 0; i < ingredients.length(); i++) {
            ingredientsList.add(ingredients.get(i).toString());
        }
        sandwich.setIngredients(ingredientsList);

        // return sandwich object
        return sandwich;
    }
}
