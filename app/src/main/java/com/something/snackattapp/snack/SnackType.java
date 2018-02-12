package com.something.snackattapp.snack;

/**
 * Possible types of snack
 */
public enum SnackType {
    VEGGIE(0, "Veggie"),
    NON_VEGGIE(1, "Non-Veggie");

    int id;
    String displayName;

    SnackType(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int id() {
        return this.id;
    }

    public String displayName() {
        return this.displayName;
    }
}
