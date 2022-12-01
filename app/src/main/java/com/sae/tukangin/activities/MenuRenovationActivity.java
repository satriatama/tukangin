package com.sae.tukangin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sae.tukangin.ApiConnect;
import com.sae.tukangin.R;
import com.sae.tukangin.adapters.ServiceRecyclerAdapter;
import com.sae.tukangin.utils.ServiceMenuData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRenovationActivity extends AppCompatActivity {
    private ArrayList<ServiceMenuData> serviceDataArrayList;
    private RecyclerView recyclerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_renovation);
        recyclerService = findViewById(R.id.recyclerServiceRenovation);

        serviceDataArrayList = new ArrayList<>();

        setMenuRenovationInfo();

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(MenuRenovationActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void setAdapter() {
        // added data from arraylist to adapter class.
        ServiceRecyclerAdapter adapter=new ServiceRecyclerAdapter(serviceDataArrayList);
        recyclerService.setItemAnimator(new DefaultItemAnimator());

        // setting grid layout manager to implement grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);

        // at last set adapter to recycler view.
        recyclerService.setLayoutManager(layoutManager);
        recyclerService.setAdapter(adapter);
    }

    private void setMenuRenovationInfo() {
        JSONObject params = new JSONObject();
        String url = ApiConnect.BASE_URL +"/showLayanan";
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
                            serviceDataArrayList.add(new ServiceMenuData(service.getString("layanan_name"), R.drawable.white_solid, service.getInt("layanan_id")));
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
                Toast.makeText(MenuRenovationActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}