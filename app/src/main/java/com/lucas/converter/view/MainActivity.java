package com.lucas.converter.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.lucas.converter.R;
import com.lucas.converter.controller.DBController;
import com.lucas.converter.controller.UserController;
import com.lucas.converter.model.User;

public class MainActivity extends AppCompatActivity {
    private EditText et_Username, et_Email, et_Password;
    private Button btnRegister;
    private UserController usersController;
    private DBController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usersController = new UserController(this);
        dbController = new DBController(this);
        et_Username = findViewById(R.id.et_Username);
        et_Password = findViewById(R.id.et_Password);
        et_Email = findViewById(R.id.et_Email);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            if (et_Username.getText().toString().trim().isEmpty() || et_Password.getText().toString().trim().isEmpty() || et_Email.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.msg_please_fill_in_all_fields), Toast.LENGTH_SHORT).show();
                return;
            } else if (et_Username.getText().toString().trim().length() < 3 && et_Username.getText().toString().trim().length() > 12) {
                Toast.makeText(this, getString(R.string.msg_error_User), Toast.LENGTH_SHORT).show();
                return;
            } else if (et_Password.getText().toString().trim().length() < 8) {
                Toast.makeText(this, getString(R.string.msg_error_Password), Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User(et_Username.getText().toString(), et_Password.getText().toString(), et_Email.getText().toString());
            usersController.saveUsers(user);
            dbController.insertData(user.getName(), user.getPassword(), user.getEmail());

            Toast.makeText(this, getString(R.string.msg_savedData), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
        loadUser();
    }
    public void loadUser() {
        User user = usersController.loadUser();
        et_Username.setText(user.getName());
        et_Password.setText(user.getPassword());
        et_Email.setText(user.getEmail());
    }
}