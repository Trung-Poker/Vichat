package com.example.vichat.ui.Contact;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Model.ChatList;
import com.example.vichat.Model.Contact;
import com.example.vichat.Model.UserChat;
import com.example.vichat.R;

import java.util.List;

import static com.example.vichat.Activity.DangNhapActivity.MyPREFERENCES;

public class ContactFragment extends Fragment {

    private ContactAdapter contactAdapter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Contact> mUser;
    SharedPreferences sharedpreferences;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
             View view =   inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
             return view;

    }
}