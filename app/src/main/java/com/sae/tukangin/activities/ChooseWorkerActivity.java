package com.sae.tukangin.activities;

import static com.sae.tukangin.activities.LoginActivity.MyPREFERENCES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sae.tukangin.ApiConnect;
import com.sae.tukangin.R;
import com.sae.tukangin.adapters.ServiceRecyclerAdapter;
import com.sae.tukangin.adapters.WorkerRecyclerAdapter;
import com.sae.tukangin.utils.ServiceMenuData;
import com.sae.tukangin.utils.WorkerData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChooseWorkerActivity extends AppCompatActivity {
    private ArrayList<WorkerData> workerDataArrayList;
    private RecyclerView recyclerWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_worker);

        recyclerWorker =  findViewById(R.id.recyclerPilihTukang);

        workerDataArrayList = new ArrayList<>();

        setWorkerInfo();
        setAdapter();
    }

    private void setAdapter() {
        // added data from arraylist to adapter class.
        WorkerRecyclerAdapter adapter = new WorkerRecyclerAdapter(workerDataArrayList);

        // setting grid layout manager to implement grid view.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // at last set adapter to recycler view.
        recyclerWorker.setLayoutManager(layoutManager);
        recyclerWorker.setAdapter(adapter);
    }

    private void setWorkerInfo() {
        JSONObject params = new JSONObject();
        String url = ApiConnect.BASE_URL +"/showTukang";
        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        try {
            params.put("kategori_id",sharedPreferences.getString("kategori_id", null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("success").equals("true")) {
                        JSONArray data = response.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject worker = data.getJSONObject(i);
                            String id = worker.getString("tukang_id");
                            String name = worker.getString("tukang_name");
                            String rating = worker.getString("rating");
                            String jmlhPesanan = worker.getString("jumlah_pesanan");
                            int integer = getIntent().getIntExtra("order_id",0);
                            workerDataArrayList.add(new WorkerData(Integer.parseInt(id), name, Integer.parseInt(jmlhPesanan), Integer.parseInt(rating), R.drawable.white_solid, integer));
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
        // added data to array list
//        serviceMenuDataArrayList.add(new ServiceMenuData("Pengecatan Tembok", R.drawable.white_solid));
//        serviceMenuDataArrayList.add(new ServiceMenuData("Penataan Ruangan", R.drawable.white_solid));
//        serviceMenuDataArrayList.add(new ServiceMenuData("Pembersihan rumah", R.drawable.white_solid));
//        serviceMenuDataArrayList.add(new ServiceMenuData("Pemasangan Furniture", R.drawable.white_solid));
    }
}