package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int numberOfCoffees = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (numberOfCoffees == 100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        return;
    }
        numberOfCoffees += 1;
        display(numberOfCoffees);
    }
    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (numberOfCoffees==1) {
            Toast.makeText(this,"You cannot have less than one coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees -= 1;
        display(numberOfCoffees);
    }
    public String createOrderSummary(int number, boolean hasWhippedCream, boolean hasChocolate, String name){
        String name1 = name +"\nQuantity: ";
        int Quantity = number;
        int total = number*5;
        if( hasWhippedCream==true && hasChocolate==true)
            total += number*3;
        else if (hasWhippedCream==false && hasChocolate==true)
            total+=number*2;
        else if (hasWhippedCream==true && hasChocolate==false)
            total+=number*1;

        return (name1  +Quantity +"\nAdd Whipped Cream?"+hasWhippedCream+"\n"+"Add Chocolate?"+hasChocolate+"\n$ "+ total+ "\n" + "Thank You!");
    }
    public void submitOrder(View view) {
        CheckBox addWhippedCream = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = addWhippedCream.isChecked();
        CheckBox addChocolate = (CheckBox) findViewById(R.id.chocolate_cream_checkbox);
        boolean hasChocolate = addChocolate.isChecked();
        String priceMessage = "Total $: "+ calculatePrice();
        EditText naam = (EditText) findViewById(R.id.name_view);
        String name = naam.getText().toString();
        priceMessage = createOrderSummary(numberOfCoffees, hasWhippedCream, hasChocolate, name);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for "+name );
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage );
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }


    }
    private int calculatePrice(){
        int price = numberOfCoffees*5;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);


    }
    /**
     * This method displays the given price on the screen.
     */



}

