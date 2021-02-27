package com.sanket.safewe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etEmail, etPhn, etAddress, etPassword;
    Button btnRegister, goToLogin;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreference = new SharedPreference(this);

        etName = findViewById(R.id.name);
        goToLogin = findViewById(R.id.gotologin);
        etEmail = findViewById(R.id.email);
        etPhn = findViewById(R.id.phn);
        etAddress = findViewById(R.id.address);

        etPassword = findViewById(R.id.password);
        btnRegister = findViewById(R.id.register);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString().trim();
                final String email = etEmail.getText().toString().trim();
                final String phone = etPhn.getText().toString().trim();
                final String address = etAddress.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "all fields are important", Toast.LENGTH_LONG).show();

                }
                else if(password.length() < 8){
                    Toast.makeText(RegisterActivity.this, "Password Length is too Short", Toast.LENGTH_LONG).show();
                }
                else{
                    btnRegister.setEnabled(false);

                    Call<ResponseBody> call = retrofit_client.getInstance().getapi().create_user(name, email, password, phone, address);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            assert response.body() != null;
                            String string = null;
                            try {
                                if (response.code() == 200) {
                                    string = response.body().string();

                                    Toast.makeText(RegisterActivity.this,"Registered",Toast.LENGTH_LONG).show();
                                    sharedPreference.setLoggedIn(true);
                                    startActivity(new Intent(RegisterActivity.this, EmergencyContacts.class));
                                    finish();

                                } else {
                                    assert response.errorBody() != null;
                                    string = response.errorBody().string();
                                    btnRegister.setEnabled(true);

                                }
                                //string = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                                btnRegister.setEnabled(true);
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "error" + t.getMessage(), Toast.LENGTH_LONG).show();
                            btnRegister.setEnabled(true);

                        }
                    });

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(sharedPreference.isContactSaved()){
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }

        if(sharedPreference.isLoggedIn()){
            startActivity(new Intent(RegisterActivity.this, EmergencyContacts.class));
            finish();
        }
    }
}