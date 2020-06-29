package com.example.time2eat;

public class Product {

    private String name, ingredient;
    private int price;

    public Product(String name, int price, String ingredient){
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


}