package com.lucas.converter.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
