package edu.westga.cs6242.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

        int quantity =
                Integer.parseInt(quantityBox.getText().toString());

        Product product =
                new Product(productBox.getText().toString(), quantity);

        dbHandler.addProduct(product);
        idView.setText(R.string.record_added);
        productBox.setText("");
        quantityBox.setText("");
    }

    public void lookupProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        Product product =
                dbHandler.findProduct(productBox.getText().toString());

        if (product != null) {
            idView.setText(String.valueOf(product.getID()));

            quantityBox.setText(String.valueOf(product.getQuantity()));
        } else {
            idView.setText(R.string.no_match_found);
        }
    }

    public void removeProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,
                null, 1);

        boolean result = dbHandler.deleteProduct(
                productBox.getText().toString());

        if (result) {
            idView.setText(R.string.record_deleted);
            productBox.setText("");
            quantityBox.setText("");
        } else
            idView.setText(R.string.no_match_found);
    }

    public void updateProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        int result = dbHandler.updateProduct();

        if (result > 0) {
            idView.setText(R.string.record_updated);
            productBox.setText("");
            quantityBox.setText("");
        } else
            idView.setText(R.string.no_match_found);
    }

    public void removeAll(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        int result = dbHandler.removeAll();

        if (result > 0) {
            idView.setText(R.string.record_deleted);
            productBox.setText("");
            quantityBox.setText("");
        } else
            idView.setText(R.string.no_match_found);
    }
}
