package com.chungwaapp.barakamachumu.chungwaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chungwaapp.barakamachumu.chungwaapp.Apiutilis.ApiService;
import com.chungwaapp.barakamachumu.chungwaapp.model.RegisterResponse;
import com.chungwaapp.barakamachumu.chungwaapp.network.ApiNetworkServer;
import com.tumblr.remember.Remember;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneActivity extends AppCompatActivity {
    EditText email;
    Button btnRegister;
    SharedPreferences preferences ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = PreferenceManager.getDefaultSharedPreferences(PhoneActivity.this);

        email = findViewById(R.id.email);


        btnRegister = findViewById(R.id.btnValidate);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailData = email.getText().toString();
                Log.d("response-register","email "+emailData);


                final int min = 10201;
                final int max = 90000;
                final String verificationCode = Integer.toString(new Random().nextInt((max - min) + 1) + min);
//                final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PhoneActivity.this);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("verificationCode",verificationCode);
////                editor.putString("r", emailData);
//                editor.apply();
                Remember.putString("verificationCode",verificationCode);
                Remember.putString("register_email",emailData);


                ApiService service = ApiNetworkServer.getClient().create(ApiService.class);

                Call<RegisterResponse> registerResponse = service.userRegister(emailData,verificationCode);
                registerResponse.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        Log.d("response-register","server reachable");

                        String result = response.body().getSuccess().toString();
                        if (result.equals("true")){
                            Log.d("result success ",result);
                            String code = preferences.getString("verificationCode","");
                            Log.d("code number", ""+code);

                            Log.d("saved email", " "+Remember.getString("register_email",null));

                            Intent intent = new Intent(PhoneActivity.this, ValidateCodeActivity.class);
                            startActivity(intent);

                        } else {
                            Log.d("result fail",result);

                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        Log.d("Server Not Reachable","ERROR "+t.getLocalizedMessage());

                    }
                });
            }
        });




    }

}
