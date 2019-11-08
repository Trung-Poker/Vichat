package com.example.vichat.ui.Message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Model.User;
import com.example.vichat.Model.UserChat;
import com.example.vichat.R;

import java.util.List;

public class MessageFragment extends Fragment {

    //public MessageFragment(){};
    private RecyclerView recyclerView;
    private UserAdapter UserAdapter;
    private List<UserChat> mUser;
    private List<User> usersList;

    @Override
    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}