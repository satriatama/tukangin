package com.sae.tukangin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sae.tukangin.utils.OfferData;
import com.sae.tukangin.R;

import java.util.ArrayList;

public class OfferRecyclerAdapter extends RecyclerView.Adapter<OfferRecyclerAdapter.MyViewHolder> {
    private final ArrayList<OfferData> offerDataList;

    public OfferRecyclerAdapter(ArrayList<OfferData> offerDataList) {
        this.offerDataList = offerDataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvOfferName;

        public MyViewHolder(final View view) {
            super(view);
            tvOfferName = view.findViewById(R.id.tvOfferName);
        }
    }

    @NonNull
    @Override
    public OfferRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_offer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferRecyclerAdapter.MyViewHolder holder, int position) {
        String name = offerDataList.get(position).getName();
        holder.tvOfferName.setText(name);
    }

    @Override
    public int getItemCount() {
        return offerDataList.size();
    }
}
