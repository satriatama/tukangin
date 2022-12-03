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
import com.sae.tukangin.utils.MessageData;

import java.time.LocalDate;
import java.util.ArrayList;

public class ChatDetailAdapter extends RecyclerView.Adapter<ChatDetailAdapter.MyViewHolder>{
    private final ArrayList<MessageData> messageDataList;

    public ChatDetailAdapter(ArrayList<MessageData> messageDataList) {
        this.messageDataList = messageDataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageTukang, messageUser;

        public MyViewHolder(final View view) {
            super(view);
            messageTukang = view.findViewById(R.id.textView76);
            messageUser = view.findViewById(R.id.textView80);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messageDataList.get(position).getIs_user().equals("0")) {
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public ChatDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat_tukang, parent, false);
            return new ChatDetailAdapter.MyViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat_user, parent, false);
            return new ChatDetailAdapter.MyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatDetailAdapter.MyViewHolder holder, int position) {
        if (messageDataList.get(position).getIs_user().equals("0")) {
            holder.messageTukang.setText(messageDataList.get(position).getMessage());
        } else {
            holder.messageUser.setText(messageDataList.get(position).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageDataList.size();
    }
}
