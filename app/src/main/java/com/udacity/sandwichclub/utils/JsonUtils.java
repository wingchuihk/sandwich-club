package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try{
            if (json == "" || json == null)
                return null;

            System.out.println("json:"+json);



            //mainName
            String mainName = new JSONObject(json).getJSONObject("name").getString("mainName");
            JSONArray alsoKnownAs = new JSONObject(json).getJSONObject("name").getJSONArray("alsoKnownAs");

            //alsoKnownAs
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i =0; i < alsoKnownAs.length(); i++){
                alsoKnownAsList.add(alsoKnownAs.get(i).toString());
            }

            //placeOfOrigin
            String placeOfOrigin = new JSONObject(json).getString("placeOfOrigin");

            //description
            String description = new JSONObject(json).getString("description");

            //image
            String image = new JSONObject(json).getString("image");

            //"ingredients"
            List<String> ingredientList = new ArrayList<>();
            JSONArray ingredients = new JSONObject(json).getJSONArray("ingredients");
            for (int i=0; i < ingredients.length(); i++){
                ingredientList.add(ingredients.get(i).toString());
//                System.out.println("in:"+ingredients.get(i).toString());
            }

            return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientList) ;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
