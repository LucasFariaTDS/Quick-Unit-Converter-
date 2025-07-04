package com.lucas.converter.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.lucas.converter.R;

public class SecondActivity extends AppCompatActivity {
    private Button btnWeight, btnDistance, btnTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        btnWeight = findViewById(R.id.btnWeight);
        btnTime = findViewById(R.id.btnTime);
        btnDistance = findViewById(R.id.btnDistance);

        btnWeight.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, WeightActivity.class);
            startActivity(intent);
        });
        btnDistance.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, DistanceActivity.class);
            startActivity(intent);
        });
        btnTime.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, TimeActivity.class);
            startActivity(intent);
        });
    }
}
