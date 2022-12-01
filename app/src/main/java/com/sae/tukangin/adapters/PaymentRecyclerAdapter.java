package com.sae.tukangin.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sae.tukangin.ApiConnect;
import com.sae.tukangin.R;
import com.sae.tukangin.activities.DetailPaymentActivity;
import com.sae.tukangin.activities.MainActivity;
import com.sae.tukangin.activities.PaymentActivity;
import com.sae.tukangin.utils.OrderData;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentRecyclerAdapter extends RecyclerView.Adapter<PaymentRecyclerAdapter.MyViewHolder> {
    private final ArrayList<OrderData> orderDataList;

    public PaymentRecyclerAdapter(ArrayList<OrderData> orderDataList) {
        this.orderDataList = orderDataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCategoryService, tvOrderName, tvPaymentDateEnd, btlknPesanan, byrPesanan, btnBack;


        public MyViewHolder(final View view) {
            super(view);
            tvCategoryService = view.findViewById(R.id.tvCategoryService);
            tvOrderName = view.findViewById(R.id.tvOrderName);
            tvPaymentDateEnd = view.findViewById(R.id.tvPaymentDateEnd);
            btlknPesanan = view.findViewById(R.id.textView65);
            byrPesanan = view.findViewById(R.id.textView35);
            btnBack = view.findViewById(R.id.btnBack);
        }
    }

    @NonNull
    @Override
    public PaymentRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_payment, parent, false);
        return new PaymentRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentRecyclerAdapter.MyViewHolder holder, int position) {
        String layanan_name = orderDataList.get(position).getLayanan_name();
        holder.tvOrderName.setText(layanan_name);
        String category = orderDataList.get(position).getKategori_name();
        holder.tvCategoryService.setText(category);
        String dateEnd = orderDataList.get(position).getOrder_end();
        holder.tvPaymentDateEnd.setText(dateEnd);

        holder.btlknPesanan.setOnClickListener(view -> {
            //change tukang order
            String url = ApiConnect.BASE_URL +"/batalkanPesanan";
            JSONObject params = new JSONObject();
            try {
                params.put("order_id", orderDataList.get(position).getId());
            } catch (JSONException e) {
                System.out.println(e);
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Intent intent = new Intent(view.getContext(), PaymentActivity.class);
                        view.getContext().startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                }
            });
            RequestQueue queue = Volley.newRequestQueue(view.getContext());
            queue.add(request);
        });

        holder.byrPesanan.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailPaymentActivity.class);
            intent.putExtra("id", orderDataList.get(position).getId());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orderDataList.size();
    }
}
