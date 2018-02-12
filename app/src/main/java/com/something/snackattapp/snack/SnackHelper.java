package com.something.snackattapp.snack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SnackHelper {
    public static ArrayList<Snack> defaultSnacks() {
        ArrayList<Snack> snacks = new ArrayList<>();

        snacks.add(new Snack("French Fries", SnackType.VEGGIE));
        snacks.add(new Snack("Veggieburger", SnackType.VEGGIE));
        snacks.add(new Snack("Carrots", SnackType.VEGGIE));
        snacks.add(new Snack("Apple", SnackType.VEGGIE));
        snacks.add(new Snack("Banana", SnackType.VEGGIE));
        snacks.add(new Snack("Milkshake", SnackType.VEGGIE));

        snacks.add(new Snack("Cheeseburger", SnackType.NON_VEGGIE));
        snacks.add(new Snack("Hamburger", SnackType.NON_VEGGIE));
        snacks.add(new Snack("Hot Dog", SnackType.NON_VEGGIE));

        return snacks;
    }

    public static HashMap<SnackType, Boolean> defaultSnackTypeFilter() {
        HashMap<SnackType, Boolean> typeFilter = new HashMap<>();

        typeFilter.put(SnackType.VEGGIE, true);
        typeFilter.put(SnackType.NON_VEGGIE, true);

        return typeFilter;
    }

    public static String getSnackDialogTitlePhrase() {
        String titlePhrase = " snack selection!";

        String[] superlatives = {"Delicious", "Magnificent", "Excellent", "Wonderful", "Impressive", "Splendid", "Tasty", "Glorious"};

        return superlatives[new Random().nextInt(superlatives.length)] + titlePhrase;
    }
}
