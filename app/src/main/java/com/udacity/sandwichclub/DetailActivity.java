package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        // Add TextView variables
        TextView mAlsoKnownAs = findViewById(R.id.detail_also_known_as);
        TextView mIngredients = findViewById(R.id.detail_ingredients);
        TextView mPlaceOfOrigin = findViewById(R.id.detail_place_of_origin);
        TextView mDescription = findViewById(R.id.detail_description);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        //----------------------------

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

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
