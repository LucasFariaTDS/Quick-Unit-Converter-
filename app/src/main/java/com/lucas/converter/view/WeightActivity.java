package com.lucas.converter.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import androidx.appcompat.app.AppCompatActivity;
import com.lucas.converter.R;

public class WeightActivity extends AppCompatActivity {
    private Spinner spinner_weight_to, spinner_weight_from;
    private EditText et_WeightValues;
    private Button btn_Convert;
    private TextView tx_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_conversion_activity);

        et_WeightValues = findViewById(R.id.etWeightValue);
        btn_Convert = findViewById(R.id.btnConvertWeight);
        tx_result = findViewById(R.id.tvResultWeight);
        spinner_weight_to = findViewById(R.id.spinnerTo);
        spinner_weight_from = findViewById(R.id.spinnerFrom);

        setupSpinner();

        btn_Convert.setOnClickListener(v -> {
            String spinner_from = spinner_weight_from.getSelectedItem().toString();
            String spinner_to = spinner_weight_to.getSelectedItem().toString();
            String input = et_WeightValues.getText().toString().trim();

            if (!input.isEmpty()) {
                try {
                    double value = Double.parseDouble(input);

                    double valueInKg = 0;
                    switch (spinner_from) {
                        case "Kilogram":
                            valueInKg = value;
                            break;
                        case "Gram":
                            valueInKg = value * 0.001;
                            break;
                        case "Pound":
                            valueInKg = value * 0.45359237;
                            break;
                        case "Ounce":
                            valueInKg = value * 0.0283495;
                            break;
                    }

                    double result = 0;
                    String unitLabel = "";

                    switch (spinner_to) {
                        case "Kilogram":
                            result = valueInKg;
                            unitLabel = "kg";
                            break;
                        case "Gram":
                            result = valueInKg / 0.001;
                            unitLabel = "g";
                            break;
                        case "Pound":
                            result = valueInKg / 0.45359237;
                            unitLabel = "lb";
                            break;
                        case "Ounce":
                            result = valueInKg / 0.0283495;
                            unitLabel = "oz";
                            break;
                    }

                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    DecimalFormat df = new DecimalFormat("#.##", symbols);
                    String formattedResult = df.format(result);

                    tx_result.setText(formattedResult + " " + unitLabel);

                    Toast.makeText(this, "Converted to " + spinner_to, Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    tx_result.setText("Invalid input");
                }
            } else {
                tx_result.setText("Please, enter a value");
            }
        });
    }
    public void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Weight_list,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_weight_to.setAdapter(adapter);
        spinner_weight_from.setAdapter(adapter);
    }
}
