package com.sae.tukangin.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sae.tukangin.R;
import com.sae.tukangin.activities.DetailChatActivity;
import com.sae.tukangin.utils.ChatData;

import java.time.LocalDate;
import java.util.ArrayList;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.MyViewHolder>{
    private final ArrayList<ChatData> chatDataList;

    public ChatRecyclerAdapter(ArrayList<ChatData> chatDataList) {
        this.chatDataList = chatDataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvChatSender, tvChatLastMessage;
        private final ConstraintLayout constChat;

        public MyViewHolder(final View view) {
            super(view);
            tvChatSender = view.findViewById(R.id.tvChatSender);
            tvChatLastMessage = view.findViewById(R.id.tvChatLastMessage);
            constChat = view.findViewById(R.id.constId);
        }
    }

    @NonNull
    @Override
    public ChatRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat, parent, false);
        return new ChatRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRecyclerAdapter.MyViewHolder holder, int position) {
        String sender = chatDataList.get(position).getTukang_name();
        holder.tvChatSender.setText(sender);
        String lastMessage = chatDataList.get(position).getLayanan_name();
        holder.tvChatLastMessage.setText(lastMessage);
        holder.constChat.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailChatActivity.class);
            intent.putExtra("id_chat", chatDataList.get(position).getId_chat());
            intent.putExtra("tukang_name", chatDataList.get(position).getTukang_name());
            intent.putExtra("tukang_id", chatDataList.get(position).getTukang_id());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return chatDataList.size();
    }
}
