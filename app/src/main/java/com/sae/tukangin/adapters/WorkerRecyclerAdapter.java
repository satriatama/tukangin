package com.sae.tukangin.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.sae.tukangin.activities.ChooseWorkerActivity;
import com.sae.tukangin.activities.MainActivity;
import com.sae.tukangin.activities.MenuRenovationActivity;
import com.sae.tukangin.activities.PaymentActivity;
import com.sae.tukangin.activities.PersiapanOrder;
import com.sae.tukangin.fragments.OrderFragment;
import com.sae.tukangin.utils.ServiceMenuData;
import com.sae.tukangin.utils.WorkerData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WorkerRecyclerAdapter extends RecyclerView.Adapter<WorkerRecyclerAdapter.RecyclerViewHolder> {

    private final ArrayList<WorkerData> workerArrayList;

    public WorkerRecyclerAdapter(ArrayList<WorkerData> workerArrayList) {
        this.workerArrayList = workerArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_worker, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        WorkerData workerData = workerArrayList.get(position);
        holder.tvWorkerName.setText(workerData.getNama());
        holder.tvWorkerJumlahLayanan.setText(String.valueOf(workerData.getJumlahLayanan()));
        holder.tvWorkerRating.setText(String.valueOf(workerData.getRating()));
        holder.ivWorkerImage.setImageResource(workerData.getImgId());
        holder.itemView.setOnClickListener(v -> {
            //change tukang order
            String url = ApiConnect.BASE_URL +"/changeTukangOrder";
            Integer tukang_id = workerData.getId();
            Integer order_id = workerData.getOrder_id();
            System.out.println("tukang_id: " + tukang_id);
            System.out.println("order_id: " + order_id);
            JSONObject params = new JSONObject();
            try {
                params.put("tukang_id", tukang_id);
                params.put("order_id", order_id);
            }catch (JSONException e){
                System.out.println(e);
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        intent.putExtra("fragment", "order");
                        v.getContext().startActivity(intent);
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
            RequestQueue queue = Volley.newRequestQueue(v.getContext());
            queue.add(request);
        });
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return workerArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvWorkerName, tvWorkerJumlahLayanan, tvWorkerRating;
        private final ImageView ivWorkerImage;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWorkerName = itemView.findViewById(R.id.textView92);
            tvWorkerJumlahLayanan = itemView.findViewById(R.id.textView96);
            tvWorkerRating = itemView.findViewById(R.id.textView99);
            ivWorkerImage = itemView.findViewById(R.id.imageView54);
        }
    }
}