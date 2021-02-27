package com.sanket.safewe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPass;
    Button btnLogin;
    SharedPreference sharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreference = new SharedPreference(this);
        etEmail = findViewById(R.id.login_email);
        etPass = findViewById(R.id.login_pass);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String email = etEmail.getText().toString().trim();

                String password = etPass.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "all fields are important", Toast.LENGTH_LONG).show();

                }
                else if(password.length() < 8){
                    Toast.makeText(LoginActivity.this, "Password Length is too Short", Toast.LENGTH_LONG).show();
                }
                else{

                    Call<LoginResponse> call = retrofit_client.getInstance().getapi().userlogin(email,password);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if(response.code() == 200){
                                LoginResponse loginResponse = response.body();
                                Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                                if (loginResponse != null) {
                                    sharedPreference.saveUid(loginResponse.getUid());
                                }
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Error : "+ t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });
    }
}