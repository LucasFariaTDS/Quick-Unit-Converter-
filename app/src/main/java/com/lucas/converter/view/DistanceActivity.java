package com.lucas.converter.view;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.lucas.converter.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DistanceActivity extends AppCompatActivity {

    private Button btn_Convert, btn_Back;
    private Spinner spinner_distance_to, spinner_distance_from;
    private EditText et_DistanceValues;
    private TextView tx_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distance_conversion_activity);

        CreatNotification();

        btn_Back = findViewById(R.id.btnBack);
        et_DistanceValues = findViewById(R.id.etDistanceValue);
        btn_Convert = findViewById(R.id.btnConvertDistance);
        tx_result = findViewById(R.id.tvResultDistance);
        spinner_distance_to = findViewById(R.id.spinnerTo);
        spinner_distance_from = findViewById(R.id.spinnerFrom);

        setupSpinner();

        btn_Convert.setOnClickListener(v -> {
            String spinner_from = spinner_distance_from.getSelectedItem().toString();
            String spinner_to = spinner_distance_to.getSelectedItem().toString();
            String input = et_DistanceValues.getText().toString().trim();

            if (!input.isEmpty()) {
                try {
                    double value = Double.parseDouble(input);

                    double valueInMetres = 0;
                    switch (spinner_from) {
                        case "Kilometre":
                            valueInMetres = value * 1000;
                            break;
                        case "Metre":
                            valueInMetres = value;
                            break;
                        case "Centimetre":
                            valueInMetres = value / 100;
                            break;
                        case "Millimetre":
                            valueInMetres = value / 1000;
                            break;
                    }

                    double result = 0;
                    String unitLabel = "";

                    switch (spinner_to) {
                        case "Kilometre":
                            result = valueInMetres / 1000;
                            unitLabel = "km";
                            break;
                        case "Metre":
                            result = valueInMetres;
                            unitLabel = "m";
                            break;
                        case "Centimetre":
                            result = valueInMetres * 100;
                            unitLabel = "cm";
                            break;
                        case "Millimetre":
                            result = valueInMetres * 1000;
                            unitLabel = "mm";
                            break;
                    }

                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    DecimalFormat df = new DecimalFormat("#.########", symbols);
                    String formattedResult = df.format(result);

                    tx_result.setText(formattedResult + " " + unitLabel);
                    Toast.makeText(v.getContext(), "Converted to " + spinner_to, Toast.LENGTH_SHORT).show();
                    SendNotification();

                } catch (NumberFormatException e) {
                    tx_result.setText("Invalid input");
                }
            } else {
                tx_result.setText("Please, enter a value");
            }
        });

        btn_Back.setOnClickListener(v->{
            Intent intent = new Intent(DistanceActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }

    public void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Distance_list,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_distance_from.setAdapter(adapter);
        spinner_distance_to.setAdapter(adapter);
    }
    private void SendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "canal_Converter")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Converted measurement")
                .setContentText("Your conversion has been confirmed, thank you for your preference")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(1, builder.build());
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
        }
    }

    private void CreatNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel("canal_Converter", "Notificações Converter", NotificationManager.IMPORTANCE_DEFAULT);
            canal.setDescription("Chanel for notification of order");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(canal);
        }
    }
}