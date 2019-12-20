package com.example.vichat.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Model.Message;
import com.example.vichat.Model.UrlImage;
import com.example.vichat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private int lastPosition = -1;
    private Context mContext;
    private List<Message> mChat;
    private String imageurl;
    private String UserId;

    public MessageAdapter(Context mContext, List<Message> messages, String imageurl, String UserId) {
        this.mContext = mContext;
        this.mChat = messages;
        this.imageurl = imageurl;
        this.UserId = UserId;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Message chat = mChat.get(position);
        holder.show_message.setText(chat.getText());
        if (imageurl.equals(null)) {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Picasso.get().load("http://192.168.0.106:8017/download/images/"+imageurl).into(holder.profile_image);
        }
        /*if (position == mChat.size() - 1){//kiểm tra lấy ra tin nhắn cuối cùng
            if (chat.getIsseen()){
                holder.txt_seen.setText("Đã xem");
            }
            else {
                holder.txt_seen.setText("Đã nhận");
            }
        }else{
            holder.txt_seen.setVisibility(View.GONE);
        }*/
    }


    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView show_message;
        public ImageView profile_image;
        public TextView txt_seen;

        public ViewHolder(View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
            txt_seen = itemView.findViewById(R.id.txt_seen);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message chat = mChat.get(position);
        if(chat.getSenderId().equals(UserId)){
            return MSG_TYPE_LEFT;
        }else{
            return MSG_TYPE_RIGHT;
        }
    }
}