package com.chungwaapp.barakamachumu.chungwaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chungwaapp.barakamachumu.chungwaapp.Apiutilis.ApiService;
import com.chungwaapp.barakamachumu.chungwaapp.model.RegisterResponse;
import com.chungwaapp.barakamachumu.chungwaapp.network.ApiNetworkServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText phone;
    EditText pswd;
    EditText pswd2;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        pswd = findViewById(R.id.pswd);
        pswd2 = findViewById(R.id.pswd2);
        btn_register = findViewById(R.id.btn_register);

        String usernameData = username.getText().toString();
        String phoneData = phone.getText().toString();
        String passwordData = pswd.getText().toString();
        String confirmPass = pswd2.getText().toString();

        // valid input fields
        validate();

        ApiService service = ApiNetworkServer.getClient().create(ApiService.class);

        Call<RegisterResponse> registerResponse = service.userRegister2(usernameData, phoneData, passwordData, confirmPass);
        registerResponse.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });

    }


    public boolean validate()
    {
        boolean valid = true;

        String name = username.getText().toString();
        String phoneNumber = phone.getText().toString();
        String password = pswd.getText().toString();
        String reEnterPassword = pswd2.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            username.setError("at least 3 characters");
            valid = false;
        } else {
            username.setError(null);
        }

        if (phoneNumber.isEmpty() || phoneNumber.length() < 3) {
            phone.setError("at least 10 Number");
            valid = false;
        } else {
            phone.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            pswd.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            pswd.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            pswd2.setError("Password Do not match");
            valid = false;
        } else {
            pswd2.setError(null);
        }

        return valid;
    }

}
