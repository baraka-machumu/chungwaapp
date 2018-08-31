package com.chungwaapp.barakamachumu.chungwaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chungwaapp.barakamachumu.chungwaapp.Apiutilis.ApiService;
import com.chungwaapp.barakamachumu.chungwaapp.model.RegisterResponse;
import com.chungwaapp.barakamachumu.chungwaapp.network.ApiNetworkServer;

import java.util.Random;

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

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameData = username.getText().toString();
                String phoneData = phone.getText().toString();
                String passwordData = pswd.getText().toString();
                String confirmPass = pswd2.getText().toString();
                Log.d("response-register", "username " + usernameData);
                Log.d("response-register", "phone " + phoneData);
                Log.d("response-register", "password " + passwordData);
                Log.d("response-register", "pswd2 " + confirmPass);


                ApiService service = ApiNetworkServer.getClient().create(ApiService.class);

                Call<RegisterResponse> registerResponse = service.userFinalRegister(usernameData, phoneData, passwordData);
                registerResponse.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        Log.d("response-register", "server reachable");

                        String result = response.body().getSuccess().toString();
                        if (result.equals("true")) {
                            Log.d("result success ", result);

                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Log.d("result fail", result);

                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        Log.d("Server Not Reachable", "ERROR " + t.getLocalizedMessage());

                    }
                });
            }
        });
    }
}

