package com.lucas.converter.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lucas.converter.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class TimeActivity extends AppCompatActivity {
    private Spinner spinner_time_to, spinner_time_from;
    private EditText et_TimeValues;
    private Button btn_Convert, btn_Back;
    private TextView tx_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_conversion_activity);

        btn_Back = findViewById(R.id.btnBack);
        et_TimeValues = findViewById(R.id.etTimeValue);
        btn_Convert = findViewById(R.id.btnConvertTime);
        tx_result = findViewById(R.id.tvResultTime);
        spinner_time_to = findViewById(R.id.spinnerTo);
        spinner_time_from = findViewById(R.id.spinnerFrom);

        setupSpinner();

        btn_Convert.setOnClickListener(v -> {
            String spinner_to = spinner_time_to.getSelectedItem().toString();
            String spinner_from = spinner_time_from.getSelectedItem().toString();
            String input = et_TimeValues.getText().toString().trim();

            if (!input.isEmpty()) {
                try {
                    double value = Double.parseDouble(input);

                    double valueInHours = 0;
                    switch (spinner_from) {
                        case "Hour":
                            valueInHours = value;
                            break;
                        case "Minute":
                            valueInHours = value / 60;
                            break;
                        case "Second":
                            valueInHours = value / 3600;
                            break;
                        case "Millisecond":
                            valueInHours = value / 3600000;
                            break;
                    }

                    double result = 0;
                    String unitLabel = "";

                    switch (spinner_to) {
                        case "Hour":
                            result = valueInHours;
                            unitLabel = "h";
                            break;
                        case "Minute":
                            result = valueInHours * 60;
                            unitLabel = "min";
                            break;
                        case "Second":
                            result = valueInHours * 3600;
                            unitLabel = "s";
                            break;
                        case "Millisecond":
                            result = valueInHours * 3600000;
                            unitLabel = "ms";
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

        btn_Back.setOnClickListener(v ->
        {
            Intent intent = new Intent(TimeActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }

    public void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Time_list,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_time_to.setAdapter(adapter);
        spinner_time_from.setAdapter(adapter);
    }
}

