package com.chungwaapp.barakamachumu.chungwaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.chungwaapp.barakamachumu.chungwaapp.Apiutilis.ApiService;
import com.chungwaapp.barakamachumu.chungwaapp.model.RegisterResponse;
import com.chungwaapp.barakamachumu.chungwaapp.network.ApiNetworkServer;
import com.tumblr.remember.Remember;

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
    RadioGroup gender;
    RadioButton radioSexButton;


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
        gender = findViewById(R.id.gender);
        btn_register = findViewById(R.id.btn_register);


        validate();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get selected radio button from radioGroup
                int genderData = gender.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSexButton = findViewById(genderData);


                String usernameData = username.getText().toString();
                String phoneData = phone.getText().toString();
                String passwordData = pswd.getText().toString();
                String confirmPass = pswd2.getText().toString();
                String email =  Remember.getString("register_email",null);
                Log.d("response-register", "username " + usernameData);
                Log.d("response-register", "phone " + phoneData);
                Log.d("response-register", "password " + passwordData);
                Log.d("response-register", "pswd2 " + confirmPass);
                Log.d("response-register", "email " + email);
                Log.d("response-register", "gender " + genderData);


                ApiService service = ApiNetworkServer.getClient().create(ApiService.class);

                Call<RegisterResponse> registerResponse = service.userFinalRegister(usernameData, genderData, phoneData, passwordData,email);
                registerResponse.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        Log.d("response-register", "server reachable");

                        String result = response.body().getSuccess().toString();

                        Log.d("result  test", result);


                        if (response.isSuccessful()){
                            if (result.equals("true")) {
                                Log.d("result success ", result);

                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);

                            } else {
                                Log.d("result fail", result);

                            }
                        }  else {
                            Log.d("response Fail", "No response from body");

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

    public boolean validate() {
        boolean valid = true;

        String name = username.getText().toString();
        String phoneNumber = phone.getText().toString();
        String password = pswd.getText().toString();
        String reEnterPassword = pswd2.getText().toString();

        if (name.isEmpty() || name.length() < 4) {
            username.setError("at least 4 characters");
            valid = false;
        } else {
            username.setError(null);
        }

        if (phoneNumber.isEmpty() || phoneNumber.length() < 10 || phoneNumber.length() >20) {
            phone.setError("between  10 number");
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

