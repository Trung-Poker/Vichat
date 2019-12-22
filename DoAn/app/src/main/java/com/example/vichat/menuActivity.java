package com.example.vichat;

import android.content.Intent;
import android.os.Bundle;

import com.example.vichat.ui.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class menuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_message, R.id.navigation_contact, R.id.navigation_friend, R.id.navigation_notification, R.id.navigation_user_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        final Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        System.out.println(email);
        //Intent intent2 = new Intent(this, UserProfileFragment.class );
        //intent2.putExtra("_id",userId);
        UserProfileFragment a = new UserProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        a.setArguments(bundle);

    }
}
