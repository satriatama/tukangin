package com.sae.tukangin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class PersiapanOrder extends AppCompatActivity {

    TextView kategori, layanan, harga, jumlahHari;
    Spinner jenisPembayaran;
    EditText tanggalMulai, alamat;
    ImageView tambahHari, kurangHari;
    Button btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persiapan_order);
        kategori = findViewById(R.id.textView50);
        layanan = findViewById(R.id.textView52);
        tanggalMulai = findViewById(R.id.etDateStart);
        jumlahHari = findViewById(R.id.textView55);
        alamat = findViewById(R.id.textView59);
        jenisPembayaran = findViewById(R.id.spinnerPaymentMethod);
        tambahHari = findViewById(R.id.imageView41);
        kurangHari = findViewById(R.id.imageView40);
        harga = findViewById(R.id.textView63);
        btnOrder = findViewById(R.id.button5);
        String url = ApiConnect.BASE_URL +"/getLayanan";
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String userId = sharedpreferences.getString("id", null);
        Intent in = getIntent();

        //get intent from previous activity
        String idLayanan = in.getStringExtra("layanan_id");
        JSONObject params = new JSONObject();
        try {
            params.put("layanan_id", Integer.parseInt(idLayanan));
        }catch (JSONException e){
            System.out.println(e);
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    kategori.setText(response.getJSONObject("data").getString("kategori_name"));
                    layanan.setText(response.getJSONObject("data").getString("layanan_name"));
                    harga.setText(response.getJSONObject("data").getString("layanan_price"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PersiapanOrder.this, "Login Gagal", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(PersiapanOrder.this);
        queue.add(request);

        Integer harga_layanan = Integer.parseInt(harga.getText().toString());

        tambahHari.setOnClickListener(v -> {
            int jumlah = Integer.parseInt(jumlahHari.getText().toString());
            jumlah++;
            jumlahHari.setText(String.valueOf(jumlah));
            int i = Integer.parseInt(harga.getText().toString()) + harga_layanan;
            harga.setText(String.valueOf(i));
        });
        kurangHari.setOnClickListener(v -> {
            int jumlah = Integer.parseInt(jumlahHari.getText().toString());
            jumlah--;
            jumlahHari.setText(String.valueOf(jumlah));
            int i = Integer.parseInt(harga.getText().toString()) - harga_layanan;
            harga.setText(String.valueOf(i));
        });

        btnOrder.setOnClickListener(view -> {
            String url1 = ApiConnect.BASE_URL +"/pemesanan";
            JSONObject params1 = new JSONObject();
            try {
                params1.put("layanan_id", Integer.parseInt(idLayanan));
                params1.put("user_id", Integer.parseInt(userId));
                params1.put("order_date", tanggalMulai.getText().toString());
                params1.put("order_address", alamat.getText().toString());
                params1.put("order_time", jumlahHari.getText().toString());
                params1.put("order_price", harga.getText().toString());
            }catch (JSONException e){
                System.out.println(e);
            }
            JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.POST, url1, params1, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Intent intent = new Intent(PersiapanOrder.this, ChooseWorkerActivity.class);
                        intent.putExtra("order_id", response.getJSONObject("data").getInt("order_id"));
                        startActivity(intent);
                    } catch (Exception e) {
                        System.out.println("Yahh eror");
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PersiapanOrder.this, "Order Gagal", Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue queue1 = Volley.newRequestQueue(PersiapanOrder.this);
            queue1.add(request1);
        });
    }
}