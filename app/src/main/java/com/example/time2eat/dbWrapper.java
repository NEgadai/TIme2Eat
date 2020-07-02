package com.example.time2eat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class dbWrapper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Time2Eat.db";
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Table> tables = new ArrayList<Table>();

    public dbWrapper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Product.tableName +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,INGREDIENTS TEXT,PRICE INT)");
        db.execSQL("create table " + Table.tableName +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT_NAME TEXT,PRODUCT_PRICE INT,PRODUCT_INGREDTIENTS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Product.tableName);
        db.execSQL("DROP TABLE IF EXISTS " + Table.tableName);
        onCreate(db);
    }

    public void insertTable(Table table) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        tables.add(table);
        ArrayList<Product> current_products = table.getProducts();
        for(int i=0; i < current_products.size(); i++){
            contentValues.put("ID", table.getID());
            contentValues.put("PRODUCT_NAME", current_products.get(i).getName());
            contentValues.put("PRODUCT_PRICE", current_products.get(i).getPrice());
            contentValues.put("PRODUCT_INGREDTIENTS", current_products.get(i).getIngredient());
            db.insert(Table.tableName,null ,contentValues);
        }
    }

    public boolean insertProdcut(Product prod) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        products.add(prod);
        contentValues.put("ID", prod.getID());
        contentValues.put("NAME", prod.getName());
        contentValues.put("INGREDIENTS", prod.getIngredient());
        contentValues.put("PRICE", prod.getPrice());
        long result = db.insert(Product.tableName,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+tableName,null);
        return res;
    }

    public Cursor getAllDataSorted(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName + " order by average desc",null);
        return res;
    }

    public Integer deleteData(String id, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, "ID = ?",new String[] {id});
    }
}