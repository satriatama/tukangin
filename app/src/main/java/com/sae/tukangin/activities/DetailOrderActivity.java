package com.sae.tukangin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sae.tukangin.ApiConnect;
import com.sae.tukangin.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailOrderActivity extends AppCompatActivity {
    TextView tvKategori, tvLayanan, tvTanggal, tvAlamat, tvTukang, tvTagihan;
    ImageView kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        tvKategori = findViewById(R.id.textView69);
        tvLayanan = findViewById(R.id.textView71);
        tvTanggal = findViewById(R.id.textView73);
        tvAlamat = findViewById(R.id.textView75);
        tvTukang = findViewById(R.id.textView78);
        tvTagihan = findViewById(R.id.textView81);
        kembali = findViewById(R.id.imageView56);

        kembali.setOnClickListener(view -> {
            finish();
        });

        String url = ApiConnect.BASE_URL +"/invoiceJoin";
        JSONObject params = new JSONObject();
        String order_id = getIntent().getStringExtra("order_id");
        String user_id = getIntent().getStringExtra("user_id");
        try {
            params.put("order_id", order_id);
            params.put("user_id", user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("success").equals("true")) {
                        tvKategori.setText(response.getJSONObject("data").getString("kategori_name"));
                        tvLayanan.setText(response.getJSONObject("data").getString("layanan_name"));
                        tvTanggal.setText(response.getJSONObject("data").getString("order_date"));
                        tvAlamat.setText(response.getJSONObject("data").getString("order_address"));
                        tvTukang.setText(response.getJSONObject("data").getString("tukang_name"));
                        tvTagihan.setText(response.getJSONObject("data").getString("order_price"));
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