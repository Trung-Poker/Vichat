package com.example.vichat.ui.Contact;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Activity.MessageActivity;
import com.example.vichat.Model.Contact;
import com.example.vichat.Model.UserChat;
import com.example.vichat.Networking.UrlImage;
import com.example.vichat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context mContext;
    private List<Contact> mUser;

    public ContactAdapter(Context mContext, List<Contact> mUser) {
        this.mContext = mContext;
        this.mUser = mUser;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false);
        return new ContactAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        final Contact user = mUser.get(position);
        holder.username.setText(user.getUsername());
        if (user.getAvatar().equals("")) {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Picasso.get().load(UrlImage.getUrlImage()+user.getAvatar()).into(holder.profile_image);
            //Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
        }
        holder.add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }); //lam sau
        /*
        if (isChat) {
            showLastMessage(user.getId(), holder.last_msg);
        } else {
            holder.last_msg.setVisibility(View.GONE);
        }
        if (isChat) {
            if (user.getStatus().equals("online")) {
                holder.img_on.setVisibility(View.VISIBLE);
                holder.img_off.setVisibility(View.GONE);
            } else {
                holder.img_on.setVisibility(View.GONE);
                holder.img_off.setVisibility(View.VISIBLE);
            }
        } else {
            holder.img_on.setVisibility(View.GONE);
            holder.img_off.setVisibility(View.GONE);
        }
            //xét có onl ko
         */
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("UserId", user.getUserId());
                intent.putExtra("user_name",user.getUsername());
                intent.putExtra("Url_avatar", user.getAvatar());
                mContext.startActivity(intent);
            }
        }); */
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public ImageView profile_image;
        public Button add_friend;

        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            add_friend = itemView.findViewById(R.id.button_add_friend);
        }
    }
}