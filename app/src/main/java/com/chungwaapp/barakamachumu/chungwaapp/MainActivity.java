package com.chungwaapp.barakamachumu.chungwaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chungwaapp.barakamachumu.chungwaapp.Apiutilis.ApiService;
import com.chungwaapp.barakamachumu.chungwaapp.model.UserDataResponse;
import com.chungwaapp.barakamachumu.chungwaapp.network.ApiNetworkServer;
import com.tumblr.remember.Remember;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView register,errLogin;
    EditText editemail,editpassword;
    Handler handler;
    private SharedPreferences pref;
    private ProgressBar progressBar;

    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Remember.init(getApplicationContext(), "com.chungwaapp.barakamachumu.chungwaapp");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        register = findViewById(R.id.link_signup);
        login =  findViewById(R.id.buttonlogin);
        editemail =  findViewById(R.id.input_email);
        editpassword =  findViewById(R.id.input_password);
        progressBar =  findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        errLogin =  findViewById(R.id.errorLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn clicked","yes");

               final String email =  editemail.getText().toString().trim();
                final String password =  editpassword.getText().toString().trim();
                Log.d("credentials ","email="+email+" password="+password);

                ApiService service = ApiNetworkServer.getClient().create(ApiService.class);
                progressBar.setContentDescription("Please Wait while login");
                progressBar.setVisibility(View.VISIBLE);

                editemail.setEnabled(false);
                editpassword.setEnabled(false);
                login.setEnabled(false);
                register.setEnabled(false);

                Call<UserDataResponse> userResponse = service.userLogin(email,password);
                userResponse.enqueue(new Callback<UserDataResponse>() {

                    @Override
                    public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {

                        if (response.isSuccessful()){

                            if (response.body().success==1){

                                Remember.putString("id", String.valueOf(response.body().id));
                                Remember.putString("email",response.body().email);
                                Remember.putString("phone",response.body().phone);
                                Remember.putString("username",response.body().username);
                                Remember.putString("token",response.body().token);

                                Log.d("success","succes code "+response.body().success);
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();

                            }
                             else {

                                Log.d("Login Fail","Fail code "+response.body().success);
                                editemail.setEnabled(true);
                                editpassword.setEnabled(true);
                                login.setEnabled(true);
                                register.setEnabled(true);
                                progressBar.setVisibility(View.GONE);

                                errLogin.setText(response.body().message);


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDataResponse> call, Throwable t) {
                        editemail.setEnabled(true);
                        editpassword.setEnabled(true);
                        login.setEnabled(true);
                        register.setEnabled(true);
                        progressBar.setVisibility(View.GONE);

                        Log.d("Server Not Found "," "+t.getLocalizedMessage());

                    }
                });

            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhoneActivity.class);
                startActivity(intent);
            }
        });
    }

}
