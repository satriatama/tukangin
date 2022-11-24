package com.sae.tukangin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sae.tukangin.R;
import com.sae.tukangin.utils.OrderData;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.MyViewHolder>{
    private final ArrayList<OrderData> orderDataList;

    public OrderRecyclerAdapter(ArrayList<OrderData> orderDataList) {
        this.orderDataList = orderDataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCategoryService, tvOrderName, tvOrderDateEnd;

        public MyViewHolder(final View view) {
            super(view);
            tvCategoryService = view.findViewById(R.id.tvCategoryService);
            tvOrderName = view.findViewById(R.id.tvOrderName);
            tvOrderDateEnd = view.findViewById(R.id.tvOrderDateEnd);
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
        String name = orderDataList.get(position).getName();
        holder.tvOrderName.setText(name);

        String category = orderDataList.get(position).getCategoryService();
        holder.tvCategoryService.setText(category);

        LocalDate dateEnd = orderDataList.get(position).getDateEnd();
        holder.tvOrderDateEnd.setText(dateEnd.toString());
    }

    @Override
    public int getItemCount() {
        return orderDataList.size();
    }
}
