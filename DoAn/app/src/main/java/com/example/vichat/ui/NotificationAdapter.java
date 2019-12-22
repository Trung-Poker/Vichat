package com.example.vichat.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Model.Notifications;
import com.example.vichat.Networking.UrlImage;
import com.example.vichat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context mContext;
    private List<Notifications> mNotification;
    Boolean isRead;

    public NotificationAdapter(Context mContext, List<Notifications> mNotification, Boolean isRead) {
        this.mContext = mContext;
        this.mNotification = mNotification;
        this.isRead = isRead;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_notification, parent, false);
        return new NotificationAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Notifications nor = mNotification.get(position);
        holder.username.setText(nor.getUsername());
        if (nor.getAvatar().equals("")) {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Picasso.get().load(UrlImage.getUrlImage() + nor.getAvatar()).into(holder.profile_image);
            //Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
        }
        holder.content.setText(nor.getContent());
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


        holder.last_msg.setText(user.getLastMessage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("UserId", user.getUserId());
                intent.putExtra("user_name",user.getUsername());
                intent.putExtra("Url_avatar", user.getAvatar());
                mContext.startActivity(intent);
            }
        });
    }

         */
    }
        @Override
        public int getItemCount () {
            return mNotification.size();
        }
    public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView username;
            public ImageView profile_image;
            public TextView content;

            public ViewHolder(View itemView) {
                super(itemView);
                username = itemView.findViewById(R.id.username);
                profile_image = itemView.findViewById(R.id.profile_image);
                content = itemView.findViewById(R.id.context);
            }
        }


}
