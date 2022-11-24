package com.sae.tukangin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.sae.tukangin.R;
import com.sae.tukangin.adapters.OrderRecyclerAdapter;
import com.sae.tukangin.adapters.PaymentRecyclerAdapter;
import com.sae.tukangin.utils.OrderData;

import java.time.LocalDate;
import java.util.ArrayList;

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
            startActivity(intent);
        });

        recyclerPayment = findViewById(R.id.recyclerPayment);
        orderDataList = new ArrayList<>();

        setAdapter();
        setPembayaranInfo();
    }

    private void setAdapter() {
        PaymentRecyclerAdapter adapter = new PaymentRecyclerAdapter(orderDataList);
        recyclerPayment.setItemAnimator(new DefaultItemAnimator());
        recyclerPayment.setAdapter(adapter);
    }

    private void setPembayaranInfo() {
        orderDataList.add(new OrderData("order4", "Renovasi Rumah", "Saluran Air", "Bogor", 3, 150000, LocalDate.now()));

    }
}