package com.sae.tukangin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sae.tukangin.R;
import com.sae.tukangin.utils.ChatData;

import java.time.LocalDate;
import java.util.ArrayList;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.MyViewHolder>{
    private final ArrayList<ChatData> chatDataList;

    public ChatRecyclerAdapter(ArrayList<ChatData> chatDataList) {
        this.chatDataList = chatDataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvChatSender, tvChatLastMessage, tvChatDate;

        public MyViewHolder(final View view) {
            super(view);
            tvChatSender = view.findViewById(R.id.tvChatSender);
            tvChatLastMessage = view.findViewById(R.id.tvChatLastMessage);
            tvChatDate = view.findViewById(R.id.tvChatDate);
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
        String sender = chatDataList.get(position).getSender();
        holder.tvChatSender.setText(sender);

        String lastMessage = chatDataList.get(position).getLastMessage();
        holder.tvChatLastMessage.setText(lastMessage);

        LocalDate date = chatDataList.get(position).getDate();
        holder.tvChatDate.setText(date.toString());
    }

    @Override
    public int getItemCount() {
        return chatDataList.size();
    }
}
