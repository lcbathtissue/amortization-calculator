package com.aldenocain.amortizationcalculator;

import android.app.Activity;    // Import for the Activity class, which represents an Android activity.
import android.content.Intent;  // Import for the Intent class, used for interactivity and navigation between activities.
import android.os.Bundle;       // Import for the Bundle class, used for passing data between activities.
import android.view.View;       // Import for the View class, used for creating UI components.
import android.widget.Button;   // Import for the Button class, used to create buttons in the user interface.
import android.widget.TextView; // Import for the TextView class, used to display text in the user interface.

/**
 * ShowMortgageDetails Activity: This activity displays the calculated monthly mortgage payment
 * received from the CalculateMortgage activity. It retrieves the monthly payment data from the
 * intent and displays it in a TextView on the user interface.
 */
public class ShowMortgageDetails extends Activity {

    private TextView monthlyPaymentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_mortgage_details);

        // Initialize UI element
        monthlyPaymentTextView = findViewById(R.id.monthly_payment);

        // Retrieve the calculated monthly payment from the intent and display it on the UI.
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("monthly_payment")) {
            double monthlyPayment = intent.getDoubleExtra("monthly_payment", 0.0);
            displayMonthlyPayment(monthlyPayment);
        } else {
            // Handle the case where the intent does not contain the expected data.
            monthlyPaymentTextView.setText("Monthly Payment Not Available");
        }

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate back to the CalculateMortgage activity
                Intent intent = new Intent(ShowMortgageDetails.this, CalculateMortgage.class);
                startActivity(intent);
                // Finish the current activity (optional)
                finish();
            }
        });

    }

    /**
     * Displays the calculated monthly mortgage payment on the user interface.
     *
     * @param monthlyPayment The monthly mortgage payment amount to be displayed.
     */
    private void displayMonthlyPayment(double monthlyPayment) {
        // Set the text of the monthlyPaymentTextView to display the calculated monthly payment
        // in a formatted string with two decimal places.
        monthlyPaymentTextView.setText(String.format("Monthly Payment: $%.2f", monthlyPayment));
    }
}
