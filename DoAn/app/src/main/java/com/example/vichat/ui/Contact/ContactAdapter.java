package com.example.vichat.ui.Contact;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Activity.DangNhapActivity;
import com.example.vichat.Activity.MainActivity;
import com.example.vichat.Activity.MessageActivity;
import com.example.vichat.Model.ChatList;
import com.example.vichat.Model.Contact;
import com.example.vichat.Model.Message;
import com.example.vichat.Model.Results;
import com.example.vichat.Model.UserChat;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.Networking.UrlImage;
import com.example.vichat.R;
import com.example.vichat.menuActivity;
import com.example.vichat.ui.Message.MessageFragment;
import com.example.vichat.ui.Message.UserAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.vichat.Activity.DangNhapActivity.MyPREFERENCES;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context mContext;
    private List<Contact> mUser;
    private String Token;
    private String flag = "";
    SharedPreferences sharedPreferences;

    public ContactAdapter(Context mContext, List<Contact> mUser,String Token) {
        this.mContext = mContext;
        this.mUser = mUser;
        this.Token = Token;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_contact_cancel, parent, false);
        return new ContactAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ContactAdapter.ViewHolder holder, final int position) {
        final Contact user = mUser.get(position);
        holder.username.setText(user.getUsername());
        if (user.getAvatar().equals("")) {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Picasso.get().load(UrlImage.getUrlImage()+user.getAvatar()).into(holder.profile_image);
            //Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            String key = "";
            @Override
            public void onClick(View v) {
                key = displayAlertDialog(holder.username.getText().toString(),user.getContactId(),Token);
                if(key == "ok"){
                    mUser.remove(position);
                    notifyItemRemoved(position);
                    Intent intent = new Intent(mContext,menuActivity.class);
                    Activity a = (Activity) mContext;
                    a.startActivity(intent);
                }
            }
        });
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

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public ImageView profile_image;
        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            button = itemView.findViewById(R.id.button);
        }
    }
    public String displayAlertDialog(String username, final String contactid, final String token){
        flag = "";
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final TextView check = alertLayout.findViewById(R.id.check);
        check.setText("Bạn có muốn hủy kết bạn với "+ username + " không");

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

            Call<Results> call = requestApi.deleteContact(token, contactid);
                call.enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {
                    try {
                        Results a =  response.body();
                        int status = a.getStatus();
                        if (status == 200) {
                            flag = "ok";
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
        return flag;
    }
}