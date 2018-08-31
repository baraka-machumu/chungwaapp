package com.chungwaapp.barakamachumu.chungwaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ValidateCodeActivity extends AppCompatActivity {
    EditText editvalidate;
    Button btnValidate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validatecode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editvalidate = findViewById(R.id.validate);

        btnValidate = findViewById(R.id.button_validate);


        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String saveCode = prefs.getString("verificationCode", null);

                String verifyCode =  editvalidate.getText().toString();
                Log.d("F saved code "," "+saveCode);

                if (verifyCode.equals(saveCode)){
                    Log.d("saved code ",""+saveCode);
                    Log.d("user input code",verifyCode);
                } else {
                    Log.d("F saved code ","");
                    Log.d( " F user input code",""+verifyCode);
                    Log.d(" F code","false Please ENTER CODE THAT WAS SEND TO YOUR EMAIL");

                }
            }
        });




    }

}
