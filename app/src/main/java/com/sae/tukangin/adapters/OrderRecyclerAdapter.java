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
import com.sae.tukangin.activities.DetailOrderActivity;
import com.sae.tukangin.activities.DetailPaymentActivity;
import com.sae.tukangin.utils.OrderData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.MyViewHolder>{
    private final ArrayList<OrderData> orderDataList;

    public OrderRecyclerAdapter(ArrayList<OrderData> orderDataList) {
        this.orderDataList = orderDataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCategoryService, tvOrderName, tvOrderDateEnd, lihatDetail;

        public MyViewHolder(final View view) {
            super(view);
            tvCategoryService = view.findViewById(R.id.tvCategoryService);
            tvOrderName = view.findViewById(R.id.tvOrderName);
            tvOrderDateEnd = view.findViewById(R.id.tvOrderDateEnd);
            lihatDetail = view.findViewById(R.id.textView35);
        }
    }

    @NonNull
    @Override
    public OrderRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order, parent, false);
        return new OrderRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerAdapter.MyViewHolder holder, int position) {
        String name = orderDataList.get(position).getLayanan_name();
        holder.tvOrderName.setText(name);
        String category = orderDataList.get(position).getKategori_name();
        holder.tvCategoryService.setText(category);
        String dateEnd = orderDataList.get(position).getOrder_end();
        holder.tvOrderDateEnd.setText(dateEnd);

        holder.lihatDetail.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailOrderActivity.class);
            String id = orderDataList.get(position).getOrder_id();
            String id_user = orderDataList.get(position).getUser_id();
            intent.putExtra("order_id", id);
            intent.putExtra("user_id", id_user);
            view.getContext().startActivity(intent);
        });

    }
        @Override
        public int getItemCount () {
            return orderDataList.size();
        }
}
