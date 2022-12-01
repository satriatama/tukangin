package com.sae.tukangin.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sae.tukangin.R;
import com.sae.tukangin.activities.MenuRenovationActivity;
import com.sae.tukangin.activities.PersiapanOrder;
import com.sae.tukangin.utils.ServiceMenuData;

import java.util.ArrayList;

public class ServiceRecyclerAdapter extends RecyclerView.Adapter<ServiceRecyclerAdapter.RecyclerViewHolder> {

    private final ArrayList<ServiceMenuData> decorationMenuArrayList;

    public ServiceRecyclerAdapter(ArrayList<ServiceMenuData> serviceMenuDataArrayList) {
        this.decorationMenuArrayList = serviceMenuDataArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_service, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        ServiceMenuData serviceMenuData = decorationMenuArrayList.get(position);
        holder.tvServiceName.setText(serviceMenuData.getTitle());
        holder.ivServiceImage.setImageResource(serviceMenuData.getImgid());
        holder.itemView.setOnClickListener(v -> {
            Integer id = serviceMenuData.getId();
            Intent intent = new Intent(v.getContext(), PersiapanOrder.class);
            intent.putExtra("layanan_id", id.toString());
            intent.putExtra("layanan_nama", serviceMenuData.getTitle());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return decorationMenuArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvServiceName;
        private final ImageView ivServiceImage;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            ivServiceImage = itemView.findViewById(R.id.ivServiceImage);
        }
    }
}