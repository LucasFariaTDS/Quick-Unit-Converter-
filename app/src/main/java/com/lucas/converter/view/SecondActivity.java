package com.lucas.converter.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.lucas.converter.R;

public class SecondActivity extends AppCompatActivity {
    private Button btnWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        btnWeight = findViewById(R.id.btnWeight);

        btnWeight.setOnClickListener(v ->{
            Intent intent = new Intent(SecondActivity.this, WeightActivity  .class);
            startActivity(intent);
        });

    }
}
