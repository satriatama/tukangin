package com.sae.tukangin.activities;

import static com.sae.tukangin.activities.LoginActivity.MyPREFERENCES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sae.tukangin.ApiConnect;
import com.sae.tukangin.R;
import com.sae.tukangin.adapters.OrderRecyclerAdapter;
import com.sae.tukangin.adapters.PaymentRecyclerAdapter;
import com.sae.tukangin.utils.OrderData;
import com.sae.tukangin.utils.WorkerData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PaymentActivity extends AppCompatActivity {
    private RecyclerView recyclerPayment;
    private ArrayList<OrderData> orderDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
            intent.putExtra("fragment", "order");
            startActivity(intent);
        });

        recyclerPayment = findViewById(R.id.recyclerPayment);
        orderDataList = new ArrayList<>();
        setPembayaranInfo();
        setAdapter();
    }

    private void setAdapter() {
        PaymentRecyclerAdapter adapter = new PaymentRecyclerAdapter(orderDataList);
        recyclerPayment.setItemAnimator(new DefaultItemAnimator());
        recyclerPayment.setAdapter(adapter);
    }

    private void setPembayaranInfo() {
        JSONObject params = new JSONObject();
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String id = sharedpreferences.getString("id", null);
        String url = ApiConnect.BASE_URL +"/menungguPembayaranJoin";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("success").equals("true")) {
                        JSONArray data = response.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject payment = data.getJSONObject(i);
                              String kategori_name = payment.getString("kategori_name");
                              String layanan_name = payment.getString("layanan_name");
                              String order_id = payment.getString("order_id");
                              String order_end = payment.getString("order_end");
                                orderDataList.add(new OrderData(kategori_name, layanan_name, order_end,order_id,id));
                        }
                        setAdapter();
                    } else {
                        System.out.println("gagal");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Gagal ambil data");
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }
}