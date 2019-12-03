package com.example.vichat.ui.Contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Model.ChatList;
import com.example.vichat.Model.UserChat;
import com.example.vichat.R;
import com.example.vichat.ui.Message.UserAdapter;

import java.util.List;

public class ContactFragment extends Fragment {

    private ContactViewModel contactViewModel;
    private RecyclerView recyclerView;
    private com.example.vichat.ui.Message.UserAdapter UserAdapter;
    private List<UserChat> mUser;
    private List<ChatList> usersList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
             View view =   inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
             return view;

    }
}