package com.example.justin.js_project2_unitconversion;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class UnitConversionActivity extends AppCompatActivity {

    private Button mConvertButton;
    private Button mSwapButton;
    private Spinner mFromSpinner;
    private Spinner mToSpinner;

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
                Toast.makeText(getApplicationContext(),"Selected Currency : " + fromCurrency +
                        ", at position: " + position, Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView arg0) {
                //TODO Auto-generated method stub
            }
        });

        mToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapter, View v, int position, long id){
                toCurrency = adapter.getItemAtPosition(position).toString();
                toCurrencyPos = position;
                Toast.makeText(getApplicationContext(),"Selected Currency : " + toCurrency +
                        ", at position: " + position, Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView arg0) {
                //TODO Auto-generated method stub
            }
        });

        mConvertButton = (Button) findViewById(R.id.convert_button);
        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //need to add code

                conversionRate = conversionInfo[fromCurrencyPos][toCurrencyPos];


                Toast.makeText(UnitConversionActivity.this,"Rate will be from " + fromCurrency +
                        " to " + toCurrency + " at " + conversionRate + " " + toCurrency +
                        " per " + fromCurrency, Toast.LENGTH_LONG).show();
            }
        });
        mSwapButton = (Button) findViewById(R.id.swap_button);
        mSwapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp;

                temp = toCurrencyPos;
                toCurrencyPos = fromCurrencyPos;
                fromCurrencyPos = temp;

                mToSpinner.setSelection(toCurrencyPos);
                mFromSpinner.setSelection(fromCurrencyPos);
            }
        });
    }
}
