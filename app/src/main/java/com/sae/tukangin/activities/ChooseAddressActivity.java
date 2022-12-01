package com.sae.tukangin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sae.tukangin.R;
import com.sae.tukangin.utils.ServiceMenuData;

import java.util.ArrayList;

public class ChooseAddressActivity extends AppCompatActivity {
    private ArrayList<ServiceMenuData> serviceMenuDataArrayList;
    private RecyclerView recyclerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_address);
    }
}