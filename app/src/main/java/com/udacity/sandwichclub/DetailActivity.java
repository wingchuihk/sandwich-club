package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;


    private TextView mTV_origin_tv;
    private TextView mTV_description_tv;
    private TextView mTV_ingredients_tv;
    private TextView mTV_also_known_tv;

//    private TextView mTextView_detail_also_known_as_label;
//    private TextView mTextView_detail_place_of_origin_label;
//    private TextView mTextView_detail_description_label;
//    private TextView mTextView_detail_ingredients_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            System.out.println("Extra Position not found in intent");

            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            System.out.println("Sandwich is null");

            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

       mTV_origin_tv =(TextView)findViewById(R.id.origin_tv);
       mTV_also_known_tv = (TextView)findViewById(R.id.also_known_tv);
       mTV_description_tv=(TextView)findViewById(R.id.description_tv);
       mTV_ingredients_tv=(TextView)findViewById(R.id.ingredients_tv);


        mTV_origin_tv.setText(sandwich.getPlaceOfOrigin());
        mTV_description_tv.setText(sandwich.getDescription());

        mTV_also_known_tv.setText(
                (Arrays.toString(sandwich.getAlsoKnownAs().toArray())).replace("[","").replace("]",""));

        mTV_ingredients_tv.setText(
                (Arrays.toString(sandwich.getIngredients().toArray())).replace("[","").replace("]",""));


    }
}
