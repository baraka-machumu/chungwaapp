package com.chungwaapp.barakamachumu.chungwaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chungwaapp.barakamachumu.chungwaapp.ApiUtilis.ApiService;
import com.chungwaapp.barakamachumu.chungwaapp.model.RegisterResponse;
import com.chungwaapp.barakamachumu.chungwaapp.network.ApiNetworkServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneActivity extends AppCompatActivity {
    EditText email;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = findViewById(R.id.email);

        btnRegister = findViewById(R.id.btnValidate);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("response-register","btn clicked");
                String emailData = email.getText().toString();

                ApiService service = ApiNetworkServer.getClient().create(ApiService.class);

                Call<RegisterResponse> registerResponse = service.userRegister(emailData);
                registerResponse.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        Log.d("response-register","server reachable");

                        String result = response.body().getSuccess().toString();
                        if (result.equals("true")){
                            Log.d("result success ",result);

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
