package com.example.justin.js_project2_unitconversion;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

public class UnitConversionActivity extends AppCompatActivity {

    private Button mConvertButton;
    private Button mSwapButton;

    private Spinner mFromSpinner;
    private Spinner mToSpinner;

    private EditText mInEditText;
    private EditText mOutEditText;

    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.##");

    //conversion rate information
    double [][] conversionInfo = new double[][] {
            { 1, 113.93, 0.93, 21.52, 23.63 },
            { 0.0088, 1, 0.0082, 0.19, 0.21 },
            { 1.07, 122.23, 1, 23.09, 25.34 },
            { 0.046, 5.29, 0.043, 1, 1.1 },
            { 0.042, 4.82, 0.039, 0.91, 1 }
    };

    String toCurrency, fromCurrency;
    int toCurrencyPos, fromCurrencyPos;
    Double fromAmount, conversionRate, toAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_conversion);

        mInEditText = (EditText) findViewById(R.id.edit_text_in);
        mOutEditText = (EditText) findViewById(R.id.edit_text_out);

        mFromSpinner = (Spinner) findViewById(R.id.spinner3);
        mToSpinner = (Spinner) findViewById(R.id.spinner4);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mFromSpinner.setAdapter(adapter);
        mToSpinner.setAdapter(adapter);

        mFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapter, View v, int position, long id){
                fromCurrency = adapter.getItemAtPosition(position).toString();
                fromCurrencyPos = position;
            }
            public void onNothingSelected(AdapterView arg0) {
                //TODO Auto-generated method stub
            }
        });

        mToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapter, View v, int position, long id){
                toCurrency = adapter.getItemAtPosition(position).toString();
                toCurrencyPos = position;
            }
            public void onNothingSelected(AdapterView arg0) {
                //TODO Auto-generated method stub
            }
        });

        mConvertButton = (Button) findViewById(R.id.convert_button);
        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                conversionRate = conversionInfo[fromCurrencyPos][toCurrencyPos];

                if(!(mInEditText.getText().toString().equals(""))) {
                    fromAmount = Double.parseDouble(mInEditText.getText().toString());

                    toAmount = fromAmount * conversionRate;

                    mOutEditText.setText(REAL_FORMATTER.format(toAmount));
                }
                else
                {
                    mOutEditText.setText("");
                    Toast.makeText(UnitConversionActivity.this,"Please enter a number", Toast.LENGTH_LONG).show();
                }
            }
        });
        mSwapButton = (Button) findViewById(R.id.swap_button);
        mSwapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempInt;
                String tempString;

                tempInt = toCurrencyPos;
                toCurrencyPos = fromCurrencyPos;
                fromCurrencyPos = tempInt;

                tempString = mOutEditText.getText().toString();
                mOutEditText.setText(mInEditText.getText().toString());
                mInEditText.setText(tempString);

                mToSpinner.setSelection(toCurrencyPos);
                mFromSpinner.setSelection(fromCurrencyPos);
            }
        });
    }
}
