package com.example.app1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ingredientsLayout;
    private Button addIngredientButton, combineButton, infoButton;

    private final String[] ingredients = {
            "Butter", "Flour", "Sugar", "Salt", "Yeast", "Milk", "White Sugar", "Eggs", "Red Food Coloring",
            "Cocoa", "Buttermilk", "Vanilla Extract", "All-Purpose Flour", "White Vinegar", "Baking Soda",
            "Melted Butter", "Cornstarch", "Brown Sugar", "Egg Yolk", "Baking Powder", "Large Egg",
            "Vegetable Oil", "Dry Yeast", "Warm Water", "Lukewarm Milk", "Shortening", "Confectioners’ Sugar",
            "Double-Crust Pie", "Ground Cinnamon", "Apples", "Ice Cold Water", "Rough Puff Pastry", "Danish Pastry",
            "Lean Dough", "Powdered Sugar", "Unsweetened Cocoa Powder", "Cheese", "Bread Crumbs", "Canola Oil",
            "Nondairy Milk", "Platinum Yeast", "Soft Dinner Rolls", "Puerto Rican Pan Sobao", "Tortilla", "Kosher Salt",
            "Almond Extract", "Fruit Tart Crust", "Fruit Tart Filling", "Sliced Strawberries", "Blackberries",
            "Raspberries", "Mandarins", "Blueberries", "Apricot Preserves", "Pumpkin Puree", "Condensed Milk", "Ground Ginger",
            "Ground Nutmeg", "Unbaked Pie Crust", "Cinnamon Raisin Bagels", "Dried Oregano", "Dried Thyme", "Dried Basil",
            "Ground Black Pepper", "Olive Oil", "Mozzarella", "Parmesan Cheese", "Focaccia Bread", "Water", "Chocolate Chips", "Vanilla"
    };

    private final Map<String, String> recipes = new HashMap<String, String>() {{
        put("Butter + Flour + Sugar + Salt + Yeast + Milk", "Croissant Dough");
        put("White Sugar + Eggs + Red Food Coloring + Cocoa + Buttermilk + Salt + Vanilla Extract + All-Purpose Flour + White Vinegar + Baking Soda", "Red Velvet Cake");
        put("Flour + Melted Butter + Cornstarch + Brown Sugar + Egg Yolk + Salt + Baking Soda + Vanilla + Chocolate Chips", "Cookie Dough");
        put("All-Purpose Flour + Baking Powder + Salt + Sugar + Large Egg + Milk + Vegetable Oil", "Muffin");
        put("Dry Yeast + Warm Water + Lukewarm Milk + White Sugar + Salt + Eggs + Shortening + All-Purpose Flour + Vegetable Oil + Butter + Confectioners’ Sugar + Vanilla + Hot Water", "Donut");
        put("Double-Crust Pie + White Sugar + Ground Cinnamon + Apples + Butter", "Apple Pie");
        put("All-Purpose Flour + Granulated Sugar + Salt + Unsalted Butter + Ice Cold Water", "Rough Puff Pastry");
        put("Flour + Butter + Milk + Egg + Sugar + Salt + Yeast", "Danish Pastry");
        put("Warm Water + Bread Flour + Brown Sugar + Salt", "Lean Dough");
        put("Eggs + Water + Powdered Sugar + Unsweetened Cocoa Powder + Oil + Vanilla Extract", "Chocolate Brownie");
        put("Sliced Sandwich Bread + Hotdogs + Cheese + Eggs + Milk + Bread Crumbs + Canola Oil", "Cheese Dog Bread Rolls");
        put("Nondairy Milk + Platinum Yeast + Sugar + Egg + Butter + Salt + All-Purpose Flour", "Soft Dinner Rolls");
        put("Warm Water + Dry Yeast + White Granulated Sugar + Lard + Bread Flour + Salt + Cooking Spray", "Puerto Rican Pan Sobao");
        put("Flour + Baking Powder + Salt + Lard + Water", "Tortilla");
        put("All-Purpose Flour + Granulated Sugar + Kosher Salt + Melted Butter + Almond Extract", "Fruit Tart Crust");
        put("Heavy Cream + Mascarpone Cheese + Cream Cheese + Powdered Sugar + Lemon Juice + Almond Extract", "Fruit Tart Filling");
        put("Sliced Strawberries + Blackberries + Raspberries + Mandarins + Blueberries + Apricot Preserves", "Fruit Tart Topping");
        put("Pumpkin Puree + Condensed Milk + Eggs + Ground Cinnamon + Ground Ginger + Ground Nutmeg + Salt + Unbaked Pie Crust", "Pumpkin Pie");
        put("Warm Water + Yeast + Bread Flour + Brown Sugar + Vanilla Extract + Raisins + Cinnamon", "Cinnamon Raisin Bagels");
        put("All-Purpose Flour + Dry Yeast + Salt + White Sugar + Garlic Powder + Dried Oregano + Dried Thyme + Dried Basil + Ground Black Pepper + Water + Olive Oil + Mozzarella & Parmesan Cheese", "Focaccia Bread");
    }};

    private final List<Spinner> ingredientSpinners = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingredientsLayout = findViewById(R.id.ingredients_layout);
        addIngredientButton = findViewById(R.id.add_ingredient_button);
        combineButton = findViewById(R.id.combine_button);

        infoButton = new Button(this);
        infoButton.setText("Info");
        infoButton.setBackgroundColor(Color.parseColor("#6200EE")); // Example purple color
        infoButton.setTextColor(Color.WHITE);
        ((LinearLayout) findViewById(R.id.main_layout)).addView(infoButton);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoDialog();
            }
        });

        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredientSpinner();
            }
        });

        combineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combineIngredients();
            }
        });

        // Add initial spinners
        addIngredientSpinner();
        addIngredientSpinner();
        addIngredientSpinner();
    }

    private void addIngredientSpinner() {
        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ingredients);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ingredientSpinners.add(spinner);
        ingredientsLayout.addView(spinner);
    }

    private void combineIngredients() {
        StringBuilder combinationKey = new StringBuilder();

        for (Spinner spinner : ingredientSpinners) {
            if (combinationKey.length() > 0) {
                combinationKey.append(" + ");
            }
            combinationKey.append(spinner.getSelectedItem().toString());
        }

        String result = recipes.get(combinationKey.toString());

        if (result != null) {
            showResultDialog(result);
        } else {
            Toast.makeText(this, "No matching pastry found for this combination!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showResultDialog(String result) {
        new AlertDialog.Builder(this)
                .setTitle("Pastry Created")
                .setMessage("You have created: " + result)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showInfoDialog() {
        new AlertDialog.Builder(this)
                .setTitle("App Information")
                .setMessage("Bakery Shop\n\nCombine ingredients to create a pastry! Use the 'Add Ingredient' button to add ingredients, then click 'Combine Ingredients' to see what pastry you've created. If the combination matches a known recipe, you'll see the result. Otherwise, try different combinations to discover new pastries.\n\n" +
                        "Ingredient list:\n\n" +
                        "Butter + Flour + Sugar + Salt + Yeast + Milk = ???\n\n" +
                        "White Sugar + Eggs + Red Food Coloring + Cocoa + Buttermilk + Salt + Vanilla Extract + All-Purpose Flour + White Vinegar + Baking Soda = ???\n\n" +
                        "Flour + Melted Butter + Cornstarch + Brown Sugar + Egg Yolk + Salt + Baking Soda + Vanilla + Chocolate Chips = ???\n\n" +
                        "Double-Crust Pie + White Sugar + Ground Cinnamon + Apples + Butter = ???\n\n" +
                        "Dry Yeast + Warm Water + Lukewarm Milk + White Sugar + Salt + Eggs + Shortening + All-Purpose Flour + Vegetable Oil + Butter + Confectioners’ Sugar + Vanilla + Hot Water = ???\n\n")
                .setPositiveButton("OK", null)
                .show();
    }
}
