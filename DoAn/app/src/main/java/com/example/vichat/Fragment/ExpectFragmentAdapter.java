package com.example.vichat.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Model.Contact;
import com.example.vichat.Model.Results;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.Networking.UrlImage;
import com.example.vichat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ExpectFragmentAdapter extends RecyclerView.Adapter<ExpectFragmentAdapter.ViewHolder>{
    private Context mContext;
    private List<Contact> mUser;
    private String Token;

    public ExpectFragmentAdapter(Context mContext, List<Contact> mUser,String Token) {
        this.mContext = mContext;
        this.mUser = mUser;
        this.Token = Token;
    }

    @NonNull
    @Override
    public ExpectFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_expect_friend, parent, false);
        return new ExpectFragmentAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ExpectFragmentAdapter.ViewHolder holder, final int position) {
        final Contact user = mUser.get(position);
        holder.username.setText(user.getUsername());
        if (user.getAvatar().equals("")) {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Picasso.get().load(UrlImage.getUrlImage()+user.getAvatar()).into(holder.profile_image);
            //Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialog(holder.username.getText().toString(),user.getUserId(),Token);
                holder.button.setVisibility(View.INVISIBLE);
                holder.button_cancel.setVisibility(View.INVISIBLE);
            }
        });

        //lam sau
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public ImageView profile_image;
        public Button button,button_cancel;

        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            button = itemView.findViewById(R.id.button);
            button_cancel = itemView.findViewById(R.id.button_cancel);
        }
    }
    public void displayAlertDialog(final String username, final String contactid, final String token){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final TextView check = alertLayout.findViewById(R.id.check);
        check.setText("Bạn có muốn kết bạn với "+ username + " không?");

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Xác nhận");
        builder.setView(alertLayout);
        builder.setCancelable(true);
        builder.create();
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Retrofit retrofit = APIClient.getClient();

                final RequestApi requestApi = retrofit.create(RequestApi.class);
                System.out.println(token);
                System.out.println(contactid);
                Call<Results> call = requestApi.ApproveContact(token, contactid);
                call.enqueue(new Callback<Results>() {
                    @Override
                    public void onResponse(Call<Results> call, Response<Results> response) {
                        try {
                            Results a =  response.body();
                            int status = a.getStatus();
                            if (status == 200) {
                                System.out.println("da xong");
                                check.setText("Đã gửi lời mời kết bạn tới "+ username + ".");
                            } else {
                                System.out.println("Fail");
                            }
                        }catch (Exception e)
                        {
                            System.out.println("error");
                        }
                    }
                    @Override
                    public void onFailure(Call<Results> call, Throwable t) {

                    }
                });
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
