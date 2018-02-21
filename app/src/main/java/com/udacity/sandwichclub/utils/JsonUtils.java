package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();

        try {
            JSONObject root = new JSONObject(json);

            JSONObject jName = root.optJSONObject("name");
            if (jName == null) Log.e(TAG, "no 'name' object in json:" + json);
            else {
                sandwich.setMainName(jName.optString("mainName"));
                JSONArray jaAlsoKnownAs = jName.optJSONArray("alsoKnownAs");
                if (jaAlsoKnownAs == null) Log.e(TAG, "no 'alsoKnownAs' array in json:" + json);
                else sandwich.setAlsoKnownAs(jsonArrayToList(jaAlsoKnownAs));
            }

            sandwich.setPlaceOfOrigin(root.optString("placeOfOrigin"));
            sandwich.setDescription(root.optString("description"));
            sandwich.setImage(root.optString("image"));

            JSONArray jaIngredients = root.optJSONArray("ingredients");
            if (jaIngredients == null) Log.e(TAG, "no 'ingredients' array in json:" + json);
            sandwich.setIngredients(jsonArrayToList(jaIngredients));

            return sandwich;

        } catch (JSONException e) {
            Log.e(TAG, "illegal json: " + json, e);
            return null;
        }


    }

    public static List<String> jsonArrayToList(JSONArray jsonArray) throws JSONException {
        int length = jsonArray.length();
        List<String> resList = new ArrayList<>();
        for (int i = 0; i < length; i++) resList.add(jsonArray.getString(i));
        return resList;
    }


}
