package com.something.snackattapp.snack;

/**
 * A tasty snack!
 */
public class Snack {
    private String name;
    private SnackType type;
    private boolean isSelected;

    public Snack(String name, SnackType type) {
        this.name = name;
        this.type = type;
        this.isSelected = false;
    }

    public String getName() { return name; }
    public SnackType getType() { return type; }
    public boolean isSelected() { return isSelected; }

    public void setName(String name) { this.name = name; }
    public void setType(SnackType type) { this.type = type; }
    public void setSelected(boolean selected) { isSelected = selected; }
}
