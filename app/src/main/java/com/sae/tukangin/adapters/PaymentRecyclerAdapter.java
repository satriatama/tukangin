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

public class PaymentRecyclerAdapter extends RecyclerView.Adapter<PaymentRecyclerAdapter.MyViewHolder>{
    private final ArrayList<OrderData> orderDataList;

    public PaymentRecyclerAdapter(ArrayList<OrderData> orderDataList) {
        this.orderDataList = orderDataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCategoryService, tvOrderName, tvPaymentDateEnd;

        public MyViewHolder(final View view) {
            super(view);
            tvCategoryService = view.findViewById(R.id.tvCategoryService);
            tvOrderName = view.findViewById(R.id.tvOrderName);
            tvPaymentDateEnd = view.findViewById(R.id.tvPaymentDateEnd);
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
        String name = orderDataList.get(position).getName();
        holder.tvOrderName.setText(name);

        String category = orderDataList.get(position).getCategoryService();
        holder.tvCategoryService.setText(category);

        LocalDate dateEnd = orderDataList.get(position).getDateEnd();
        holder.tvPaymentDateEnd.setText(dateEnd.toString());
    }

    @Override
    public int getItemCount() {
        return orderDataList.size();
    }
}
