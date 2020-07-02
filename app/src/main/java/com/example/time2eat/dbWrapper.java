package com.example.time2eat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class dbWrapper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Time2Eat.db";
    public static ArrayList<Product> products = new ArrayList<Product>();
    public static ArrayList<Table> tables = new ArrayList<Table>(){};

    public dbWrapper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // initial tables
        products.add(new Product("Bread", 25, "flour, water"));
        products.add(new Product("Pizza", 50, "cheese, tomato sauce, dough"));
        products.add(new Product("steak", 100, "ribeye steak"));
        tables.add(new Table());
        tables.add(new Table());
        tables.add(new Table());

        db.execSQL("create table " + Product.tableName +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,INGREDIENTS TEXT,PRICE INT)");
        db.execSQL("create table " + Table.tableName +" (ID INTEGER,PRODUCTS_IDS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Product.tableName);
        db.execSQL("DROP TABLE IF EXISTS " + Table.tableName);
        onCreate(db);
    }

    public void insertProdcutToTable(int table_id, ArrayList<Product> products) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < tables.size(); i++){
            if (tables.get(i).getID() == table_id){
                for(int j = 0; j < products.size(); j++){
                    Product cur_prod = products.get(i);
                    tables.get(i).addProduct(cur_prod);
                    contentValues.put("ID", table_id);
                    contentValues.put("PRODUCTS_IDS", getProdcutsIdByTableName(Product.tableName, table_id) + " " + cur_prod.getID());
                    db.insert(Product.tableName,null ,contentValues);
                }
            }
        }
    }

    public String getProdcutsIdByTableName(String tableName, int tableID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+tableName+"where ID = "+tableID,null);
        String rv = "";
        if (res.moveToFirst()) {
            rv = res.getString(res.getColumnIndex("PRODUCTS_IDS"));
        }
        return rv;
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