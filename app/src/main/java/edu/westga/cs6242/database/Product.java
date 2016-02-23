package edu.westga.cs6242.database;

/**
 * Created by rcarswel on 2/23/2016.
 */
public class Product {

    private int _id;
    private String _productname;
    private int _quantity;

    public Product() {

    }

    public Product(int id, String productname, int quantity) {
        this._id = id;
        this._productname = productname;
        this._quantity = quantity;
    }

    public Product(String productname, int quantity) {
        this._productname = productname;
        this._quantity = quantity;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getProductName() {
        return this._productname;
    }

    public void setProductName(String productname) {
        this._productname = productname;
    }

    public int getQuantity() {
        return this._quantity;
    }

    public void setQuantity(int quantity) {
        this._quantity = quantity;
    }
}