package com.sae.tukangin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sae.tukangin.R;
import com.sae.tukangin.utils.OrderData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailPaymentActivity extends AppCompatActivity {
    TextView tvKategori, tvLayanan, tvTanggalOrder, tvalamat, tvTukang, tvHarga, tvBayarSebelum;
    Button btnkirim;
    ImageView pilihGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_payment);
        tvKategori = findViewById(R.id.textView69);
        tvLayanan = findViewById(R.id.textView71);
        tvTanggalOrder = findViewById(R.id.textView73);
        tvalamat = findViewById(R.id.textView75);
        tvTukang = findViewById(R.id.textView78);
        tvHarga = findViewById(R.id.textView84);
        tvBayarSebelum = findViewById(R.id.textView86);
        pilihGambar = findViewById(R.id.imageView46);
        btnkirim = findViewById(R.id.button4);

        JSONObject params = new JSONObject();
        try {
            params.put("order_id", getIntent().getStringExtra("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "http://192.168.56.1/Tukangin-API/public/api/menungguPembayaranJoinById";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("success").equals("true")) {
                        System.out.println(response.toString());
                        tvKategori.setText(response.getJSONObject("data").getString("kategori_name"));
                        tvLayanan.setText(response.getJSONObject("data").getString("layanan_name"));
                        tvTanggalOrder.setText(response.getJSONObject("data").getString("order_date"));
                        tvalamat.setText(response.getJSONObject("data").getString("order_address"));
                        tvTukang.setText(response.getJSONObject("data").getString("tukang_name"));
                        tvHarga.setText(response.getJSONObject("data").getString("order_price"));
                        tvBayarSebelum.setText(response.getJSONObject("data").getString("order_end"));
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


        btnkirim.setOnClickListener(view -> {
            JSONObject params1 = new JSONObject();
            try {
                params1.put("order_id", getIntent().getStringExtra("id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String url1 = "http://192.168.56.1/Tukangin-API/public/api/pembayaran";
            JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.POST, url1, params1, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getString("success").equals("true")) {
                            Intent intent = new Intent(DetailPaymentActivity.this, MainActivity.class);
                            startActivity(intent);
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
            RequestQueue queue1 = Volley.newRequestQueue(this);
            queue1.add(request1);
        });
    }
}