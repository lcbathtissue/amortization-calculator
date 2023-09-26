package com.aldenocain.amortizationcalculator;

import android.app.Activity;        // Import for the Activity class, which represents an Android activity.
import android.content.Intent;       // Import for the Intent class, used for interactivity and navigation between activities.
import android.os.Bundle;            // Import for the Bundle class, used for passing data between activities.
import android.view.View;            // Import for the View class, used for creating UI components.
import android.widget.Button;        // Import for the Button class, used to create buttons in the user interface.
import android.widget.EditText;      // Import for the EditText class, used to create input fields.
import android.widget.TextView;      // Import for the TextView class, used to display text in the user interface.


/**
 * CalculateMortgage Activity: This activity allows the user to input mortgage details
 * such as the principal amount, interest rate, and amortization period and calculates
 * the monthly mortgage payment. The calculated result is then passed to the
 * ShowMortgageDetails activity for display.
 */
public class CalculateMortgage extends Activity {

    private EditText principalEditText;
    private EditText interestRateEditText;
    private EditText amortizationPeriodEditText;
    private TextView resultTextView;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_mortgage);

        // Initialize UI elements
        principalEditText = findViewById(R.id.principal);
        interestRateEditText = findViewById(R.id.interest_rate);
        amortizationPeriodEditText = findViewById(R.id.amortization_period);
        resultTextView = findViewById(R.id.result);
        calculateButton = findViewById(R.id.calculate_button);

        // Set a click listener for the Calculate button to trigger the mortgage calculation.
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateMortgage();
            }
        });
    }

    private void calculateMortgage() {
        // Get the mortgage principal amount from the principalEditText field.
        // Get the annual interest rate from the interestRateEditText field and convert it to a monthly rate.
        // Get the amortization period in years from the amortizationPeriodEditText field and convert it to months.
        double principal = Double.parseDouble(principalEditText.getText().toString());
        double interestRate = Double.parseDouble(interestRateEditText.getText().toString()) / 100 / 12; // Monthly interest rate
        int amortizationPeriod = Integer.parseInt(amortizationPeriodEditText.getText().toString()) * 12; // Total number of payments

        // Calculate the monthly mortgage payment using the provided formula.
        double monthlyPayment = principal * (interestRate * Math.pow(1 + interestRate, amortizationPeriod)) / (Math.pow(1 + interestRate, amortizationPeriod) - 1);

        // Create an intent to navigate to the ShowMortgageDetails activity and pass the calculated monthly payment as an extra.
        Intent intent = new Intent(this, ShowMortgageDetails.class);
        intent.putExtra("monthly_payment", monthlyPayment);

        // Start the ShowMortgageDetails activity.
        startActivity(intent);
    }
}
