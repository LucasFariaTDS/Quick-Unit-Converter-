package com.lucas.converter.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.lucas.converter.model.User;

public class UserController {
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String NAME_PREFERENCE = "data_users";
    private final SharedPreferences preferences;

    public UserController(Context context) {
        preferences = context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE);
    }

    public void saveUsers(User user) {
        SharedPreferences.Editor dataUsers = preferences.edit();
        dataUsers.putString(KEY_USER_NAME, user.getName());
        dataUsers.putString(KEY_PASSWORD, user.getPassword());
        dataUsers.putString(KEY_EMAIL, user.getEmail());
        dataUsers.apply();
    }
    public User loadUser(){
        String userName = preferences.getString(KEY_USER_NAME, "");
        String password = preferences.getString(KEY_PASSWORD, "");
        String email = preferences.getString(KEY_EMAIL, "");
        return new User(userName,password,email);
    }
}

