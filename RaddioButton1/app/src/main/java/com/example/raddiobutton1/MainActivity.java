//A111222009
package com.example.raddiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.raddiobutton1.MainActivity2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views
        RadioButton rdbBoy = findViewById(R.id.rdbBoy);
        RadioButton rdbGirl = findViewById(R.id.rdbGirl);
        RadioGroup rgType = findViewById(R.id.rgType);
        EditText edtNumber = findViewById(R.id.edtNumber);
        TextView lblOutput = findViewById(R.id.lblOutput);

        // Set event listeners
        View.OnClickListener radioButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOutput(lblOutput, rdbBoy, rdbGirl, rgType, edtNumber);
            }
        };

        rdbBoy.setOnClickListener(radioButtonListener);
        rdbGirl.setOnClickListener(radioButtonListener);
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOutput(lblOutput, rdbBoy, rdbGirl, rgType, edtNumber);
            }
        });
        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateOutput(lblOutput, rdbBoy, rdbGirl, rgType, edtNumber);
            }
        });

        // Set initial output
        updateOutput(lblOutput, rdbBoy, rdbGirl, rgType, edtNumber);

        // Set button click listeners
//        Button button = findViewById(R.id.btnchoose);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Same logic as before
//                // ...
//            }
//        });

        Button buttoncorret = findViewById(R.id.buttoncorret);
        buttoncorret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rdbBoy = findViewById(R.id.rdbBoy);
                RadioButton rdbGirl = findViewById(R.id.rdbGirl);
                RadioGroup rgType = findViewById(R.id.rgType);
                EditText edtNumber = findViewById(R.id.edtNumber);

                String gender = (rdbBoy.isChecked()) ? getResources().getString(R.string.male) : getResources().getString(R.string.female);
                String ticketType = "";
                if (rgType.getCheckedRadioButtonId() == R.id.rdbAdult) {
                    ticketType = getResources().getString(R.string.regularticket);
                } else if (rgType.getCheckedRadioButtonId() == R.id.rdbChild) {
                    ticketType = getResources().getString(R.string.childticket);
                } else if (rgType.getCheckedRadioButtonId() == R.id.rdbStudent) {
                    ticketType = getResources().getString(R.string.studentticket);
                }
                String ticketCountStr = edtNumber.getText().toString();
                int ticketCount = (ticketCountStr.isEmpty()) ? 0 : Integer.parseInt(ticketCountStr);

                double ticketPrice = calculateTicketPrice(ticketType, ticketCount);

                // Create Intent
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("gender", gender);
                intent.putExtra("ticketType", ticketType);
                intent.putExtra("ticketCount", ticketCount);
                intent.putExtra("ticketPrice", ticketPrice);
                startActivity(intent);
            }
        });
    }

    // Method to update output based on user selections
    private void updateOutput(TextView lblOutput, RadioButton rdbBoy, RadioButton rdbGirl, RadioGroup rgType, EditText edtNumber) {
        // Same logic as before to retrieve user selections
        String gender = (rdbBoy.isChecked()) ? getResources().getString(R.string.male) : getResources().getString(R.string.female);
        String ticketType = "";
        if (rgType.getCheckedRadioButtonId() == R.id.rdbAdult) {
            ticketType = getResources().getString(R.string.regularticket);
        } else if (rgType.getCheckedRadioButtonId() == R.id.rdbChild) {
            ticketType = getResources().getString(R.string.childticket);
        } else if (rgType.getCheckedRadioButtonId() == R.id.rdbStudent) {
            ticketType = getResources().getString(R.string.studentticket);
        }
        String ticketCountStr = edtNumber.getText().toString();
        int ticketCount = (ticketCountStr.isEmpty()) ? 0 : Integer.parseInt(ticketCountStr);

        double ticketPrice = calculateTicketPrice(ticketType, ticketCount);

        // Construct output string
        String outputStr = "性別: " + gender + "\n"
                + "票種: " + ticketType + "\n"
                + "購買張數: " + ticketCount + "\n"
                + "金額: $" + formatAmount(ticketPrice);

        // Update lblOutput with the new output string
        lblOutput.setText(outputStr);
    }

    // Method to calculate ticket price
    private double calculateTicketPrice(String ticketType, int ticketCount) {
        double ticketPrice = 0.0 ;
        // Calculate ticket price based on ticket type
        if (ticketType.equals(getResources().getString(R.string.regularticket))) {
            ticketPrice = 500.0 * ticketCount; // Regular ticket price is $500
        } else if (ticketType.equals(getResources().getString(R.string.studentticket))) {
            ticketPrice = 400.0 * ticketCount; // Student ticket price is $400
        } else if (ticketType.equals(getResources().getString(R.string.childticket))) {
            ticketPrice = 250.0 * ticketCount; // Child ticket price is $250
        }
        return ticketPrice;
    }

    // Method to format amount
    private String formatAmount(double amount) {
        // Convert amount to string
        String amountString = String.format("%.2f", amount);

        // Remove trailing zeros after decimal point
        if (amountString.indexOf(".") > 0) {
            amountString = amountString.replaceAll("0*$", "").replaceAll("\\.$", "");
        }

        return amountString;
    }
}