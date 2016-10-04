package com.example.kaina.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Calculator extends AppCompatActivity {

    // Arrays of digits and mathematic operations
    private int[] digits = {R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.decimal};
    private int[] operations = {R.id.divide, R.id.multiply, R.id.subtract, R.id.add};

    // 0 is divide, 1 is multiply, 2 is subtract, 3 is add
    int prevOperation;
    Float calculation;

    // Variable representing the type of the last input character
    // 0: none
    // 1: digit
    // 2: operation
    int lastCharType = 0;

    // Display variable
    TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Save reference to display
        display = (TextView) findViewById(R.id.display);

        // Initialize button listeners
        initDigitListeners();
        initOperationListeners();
    }

    private void initDigitListeners() {

        // Create a generic listener to be applied to all digits
        View.OnClickListener listen = new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                Button currButton = (Button) e;
                if (!(e.getId() == R.id.decimal && display.getText().toString().contains("."))) display.append(currButton.getText());
                lastCharType = 1;
            }
        };

        // Iterate through all digits and apply listener
        for (int id : digits) findViewById(id).setOnClickListener(listen);
    }

    private void initOperationListeners() {

        // Create a generic listener to be applied to all operations
        View.OnClickListener listen = new View.OnClickListener() {
            @Override
            public void onClick(View e) {

                if (display.getText() != null && lastCharType != 2) {
                    Button currButton = (Button) e;
                    Float currNum = Float.parseFloat(display.getText() + "");

                    if (calculation == null) calculation = currNum;
                    else {
                        if (prevOperation == 0) calculation = calculation / currNum;
                        else if (prevOperation == 1) calculation = calculation * currNum;
                        else if (prevOperation == 2) calculation = calculation - currNum;
                        else if (prevOperation == 3) calculation = calculation + currNum;
                    }

                    switch (currButton.getId()) {
                        case R.id.divide:
                            prevOperation = 0;
                            break;
                        case R.id.multiply:
                            prevOperation = 1;
                            break;
                        case R.id.subtract:
                            prevOperation = 2;
                            break;
                        case R.id.add:
                            prevOperation = 3;
                            break;
                        default:
                            break;
                    }
                }

                display.setText("");
                lastCharType = 2;
            }
        };

        // Iterate through all operations and apply listener
        for (int id : operations) findViewById(id).setOnClickListener(listen);

        // Backspace button
        findViewById(R.id.backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = display.getText().toString();
                if (str != null && str.length() > 0) str = str.substring(0, str.length()-1);
                display.setText(str);
            }
        });

        // Clear button
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("");
                calculation = null;
                lastCharType = 0;
            }
        });

        // Percent button
        findViewById(R.id.percent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float temp = Float.parseFloat(display.getText() + "") ;
                display.setText(temp/100 + "");
            }
        });

        // Equal button
        findViewById(R.id.equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float currNum = Float.parseFloat(display.getText() + "");
                if (prevOperation == 0) calculation = calculation / currNum;
                else if (prevOperation == 1) calculation = calculation * currNum;
                else if (prevOperation == 2) calculation = calculation - currNum;
                else if (prevOperation == 3) calculation = calculation + currNum;

                lastCharType = 1;
                prevOperation = -1;

                display.setText(calculation + "");
            }
        });
    }
}
