package edu.westga.cs6242.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {

    TextView idView;
    EditText productBox;
    EditText quantityBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        quantityBox =
                (EditText) findViewById(R.id.productQuantity);
    }

    public void newProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean check = false;
        int quantity = -1;
        Product product = null;

        if (productBox.getText().length() == 0) {
            makeToast("Product is Blank!");
        } else if (quantityBox.getText().length() == 0) {
            makeToast("Quantity is Blank!");
        } else {
            quantity = Integer.parseInt(quantityBox.getText().toString());

            Product notUnique = dbHandler.findProduct(productBox.getText().toString());

            if (quantity < 0) {
                makeToast("Quantity is Less Than 0!");
            } else if (notUnique != null) {
                makeToast("Product Name Already Exist!");
            } else
                check = true;
        }

        if (check) {
            product = new Product(productBox.getText().toString(), quantity);
            dbHandler.addProduct(product);
            idView.setText(R.string.record_added);
            makeToast(product.getProductName() + " was added.");
            productBox.setText("");
            quantityBox.setText("");
        }
    }

    public void lookupProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean check = false;

        Product product = dbHandler.findProduct(productBox.getText().toString());

        if (productBox.getText().length() == 0) {
            makeToast("Product is Blank!");
        } else if (product == null) {
            makeToast("Product Name Doesn't Exist!");
        } else
            check = true;

        if (check) {
            idView.setText(String.valueOf(product.getID()));

            quantityBox.setText(String.valueOf(product.getQuantity()));
        }
    }

    public void removeProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean check = false;

        Product product = dbHandler.findProduct(productBox.getText().toString());

        if (productBox.getText().length() == 0) {
            makeToast("Product is Blank!");
        } else if (product == null) {
            makeToast("Product Name Doesn't Exist!");
        } else
            check = true;

        if (check) {
            boolean result = dbHandler.deleteProduct(productBox.getText().toString());

            if (result) {
                idView.setText(R.string.record_deleted);
                makeToast(product.getProductName() + " was removed.");
                productBox.setText("");
                quantityBox.setText("");
            }
        }
    }

    public void updateProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean check = false;
        int newQuantity = -1;
        Product product = null;

        if (productBox.getText().length() == 0) {
            makeToast("Product is Blank!");
        } else if (quantityBox.getText().length() == 0) {
            makeToast("Quantity is Blank!");
        } else {
            newQuantity = Integer.parseInt(quantityBox.getText().toString());

            Product notUnique = dbHandler.findProduct(productBox.getText().toString());

            if (newQuantity < 0) {
                makeToast("Quantity is Less Than 0!");
            } else if (notUnique != null) {
                makeToast("Product Name Already Exist!");
            } else
                check = true;
        }

        if (check) {
            int idNumber = Integer.parseInt(idView.getText().toString());

            Product newProduct = new Product(idNumber, productBox.getText().toString(), newQuantity);

            boolean result = dbHandler.updateProduct(newProduct);

            if (result) {
                idView.setText(R.string.record_updated);
                makeToast(product.getProductName() + " was updated.");
                productBox.setText("");
                quantityBox.setText("");
            } else
                makeToast("No Matching Product ID!");
        }
    }

    public void removeAll(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        boolean result = dbHandler.removeAll();

        if (result) {
            idView.setText(R.string.record_deleted);
            makeToast("All records removed!");
            productBox.setText("");
            quantityBox.setText("");
        } else
            makeToast("No Matching Product!");
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
