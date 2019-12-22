package com.example.vichat.ui.Friend;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vichat.Activity.MessageActivity;
import com.example.vichat.Model.UserChat;
import com.example.vichat.R;
import com.example.vichat.ui.Message.MessageFragment;

import java.util.List;

public class FriendAdapter extends ViewModel {

    private MutableLiveData<String> mText;

    public FriendAdapter() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Contact fragment");
    }


    public LiveData<String> getText() {
        return mText;
    }


}

