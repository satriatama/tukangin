package com.sae.tukangin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sae.tukangin.R;
import com.sae.tukangin.utils.ServiceMenuData;
import com.sae.tukangin.adapters.ServiceRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuDecorationActivity extends AppCompatActivity {
    private ArrayList<ServiceMenuData> serviceMenuDataArrayList;
    private RecyclerView recyclerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_decoration);
        recyclerService = findViewById(R.id.recyclerServiceDecoration);

        serviceMenuDataArrayList = new ArrayList<>();

        setMenuDecorationInfo();
        setAdapter();

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(MenuDecorationActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }


    private void setAdapter() {
        // added data from arraylist to adapter class.
        ServiceRecyclerAdapter adapter = new ServiceRecyclerAdapter(serviceMenuDataArrayList);

        // setting grid layout manager to implement grid view.
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        // at last set adapter to recycler view.
        recyclerService.setLayoutManager(layoutManager);
        recyclerService.setAdapter(adapter);
    }

    private void setMenuDecorationInfo() {
        JSONObject params = new JSONObject();
        String url = "http://192.168.56.1/Tukangin-API/public/api/showLayanan";
        try {
            params.put("kategori_id",getIntent().getIntExtra("kategori_id",0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println(response.getString("data"));
                    if (response.getString("success").equals("true")) {
                        JSONArray data = response.getJSONArray("data");
//                        JSONArray data = dataObj.getJSONArray("data");
                        System.out.println(data.toString());
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject service = data.getJSONObject(i);
                            serviceMenuDataArrayList.add(new ServiceMenuData(service.getString("layanan_name"), R.drawable.white_solid, service.getInt("layanan_id")));
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
                Toast.makeText(MenuDecorationActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
        // added data to array list
//        serviceMenuDataArrayList.add(new ServiceMenuData("Pengecatan Tembok", R.drawable.white_solid));
//        serviceMenuDataArrayList.add(new ServiceMenuData("Penataan Ruangan", R.drawable.white_solid));
//        serviceMenuDataArrayList.add(new ServiceMenuData("Pembersihan rumah", R.drawable.white_solid));
//        serviceMenuDataArrayList.add(new ServiceMenuData("Pemasangan Furniture", R.drawable.white_solid));
    }
}