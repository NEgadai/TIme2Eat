package com.example.time2eat;

import java.util.ArrayList;

public class Table{

    public static final String tableName = "tables_table";
    public static final String[] fields = new String[]{"id"};
    private ArrayList<Product> products = new ArrayList<>();
    private boolean occupied = false;
    private int id;
    private static int ID = 0;

    public Table(){
        this.id = ID++;
    }

    public int getID(){
        return this.id;
    }

    public ArrayList<Product> getProducts(){
        return this.products;
    }

    public void addProduct(Product prod){
        this.products.add(prod);
    }
}