# Sandwich Club Project Assignment

## Fill parseSandwichJson Function
```java
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
```

## Add sandwich object value on TextView in Detail Activity

```java
// Set alsoKnownAs in Sandwich Object field value into TextView
        StringBuilder sb;
        if (!sandwich.getAlsoKnownAs().isEmpty()) {
            List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
            sb = new StringBuilder("Also Known as : ");
            for (String aka : alsoKnownAs) {
                sb.append(aka).append(", ");
            }
            // Remove last comma and space
            sb.delete(sb.length() - 2, sb.length());
            mAlsoKnownAs.setText(sb.toString());
        } else {
            sb = new StringBuilder("Also Known as : None");
            mAlsoKnownAs.setText(sb.toString());
        }


        // Set description in Sandwich Object field value into TextView
        String description = sandwich.getDescription();
        if (description != null && !description.equals("")) {
            sb = new StringBuilder();
            sb.append("Description : ").append(sandwich.getDescription());
            mDescription.setText(sb.toString());
        } else {
            sb = new StringBuilder();
            sb.append("Description : Unknown");
            mDescription.setText(sb.toString());
        }


        // Set ingredients in Sandwich Object field value into TextView
        if (!sandwich.getIngredients().isEmpty()){
            List<String> ingredients = sandwich.getIngredients();
            sb = new StringBuilder("Ingredients : ");
            for (String ingredient : ingredients) {
                sb.append(ingredient).append(", ");
            }
            // Remove last comma and space
            sb.delete(sb.length() - 2, sb.length());
            mIngredients.setText(sb.toString());
        } else {
            sb = new StringBuilder("Ingredients : Unknown");
            mAlsoKnownAs.setText(sb.toString());
        }


        // Set place of origin in Sandwich Object field value into TextView
        String placeOfOrigin = sandwich.getPlaceOfOrigin();
        if (placeOfOrigin != null && !placeOfOrigin.equals("")) {
            sb = new StringBuilder();
            sb.append("Get Place Of Origin : ").append(sandwich.getPlaceOfOrigin());
            mPlaceOfOrigin.setText(sb.toString());
        } else {
            sb = new StringBuilder();
            sb.append("Get Place Of Origin : Unknown");
            mPlaceOfOrigin.setText(sb.toString());
        }
```
