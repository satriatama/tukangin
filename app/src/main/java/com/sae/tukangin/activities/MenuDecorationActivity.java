package com.sae.tukangin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.sae.tukangin.R;
import com.sae.tukangin.utils.ServiceMenuData;
import com.sae.tukangin.adapters.ServiceRecyclerAdapter;

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
        // added data to array list
        serviceMenuDataArrayList.add(new ServiceMenuData("Pengecatan Tembok", R.drawable.white_solid));
        serviceMenuDataArrayList.add(new ServiceMenuData("Penataan Ruangan", R.drawable.white_solid));
        serviceMenuDataArrayList.add(new ServiceMenuData("Pembersihan rumah", R.drawable.white_solid));
        serviceMenuDataArrayList.add(new ServiceMenuData("Pemasangan Furniture", R.drawable.white_solid));
    }
}