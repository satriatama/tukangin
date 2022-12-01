package com.sae.tukangin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sae.tukangin.ApiManager;
import com.sae.tukangin.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView btnClose;
    TextView btnRegister;
    EditText etName, etEmail, etPassword, etConfirmPassword;
    String name, email, password, confirmPassword;
    ApiManager apiManager = ApiManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnClose = findViewById(R.id.btnClose);

        btnRegister.setOnClickListener(this);

        btnClose.setOnClickListener(v -> finish());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                JSONObject params = new JSONObject();
                String url = "http://192.168.56.1/Tukangin-API/public/api/register";
                try {
                    params.put("name", etName.getText().toString());
                    params.put("email", etEmail.getText().toString());
                    params.put("password", etPassword.getText().toString());
                    params.put("password_confirmation", etConfirmPassword.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("Response: "+ response.toString());
                                try {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }catch (Exception e){
                                    System.out.println(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("Error: "+ error.toString());
                            }
                        })
                ;
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(jsonObjectRequest);
        }
    }
}