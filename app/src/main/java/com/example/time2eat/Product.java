package com.example.time2eat;

public class Product{

    public static final String tableName = "products_table";
    public static final String[] fields = new String[]{"id", "name", "price", "ingredient"};
    private String name, ingredient;
    private int price, id;
    private static int ID = 0;

    public Product(String name, int price, String ingredient){
        this.id = ID++;
        this.name = name;
        this.price = price;
        this.ingredient = ingredient;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getIngredient() {
        return ingredient;
    }

    public int getID(){
        return this.id;
    }
}