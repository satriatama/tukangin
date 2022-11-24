package com.sae.tukangin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sae.tukangin.R;
import com.sae.tukangin.adapters.ServiceRecyclerAdapter;
import com.sae.tukangin.utils.ServiceMenuData;

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
        setAdapter();

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
        serviceDataArrayList.add(new ServiceMenuData("Atap Rumah",R.drawable.white_solid));
        serviceDataArrayList.add(new ServiceMenuData("Lantai",R.drawable.white_solid));
        serviceDataArrayList.add(new ServiceMenuData("Pipa Saluran Air",R.drawable.white_solid));
        serviceDataArrayList.add(new ServiceMenuData("Halaman Rumah",R.drawable.white_solid));
        serviceDataArrayList.add(new ServiceMenuData("Langit-langit",R.drawable.white_solid));
        serviceDataArrayList.add(new ServiceMenuData("Kelistrikan",R.drawable.white_solid));
    }
}