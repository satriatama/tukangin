package com.sae.tukangin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.sae.tukangin.fragments.HomeFragment;
import com.sae.tukangin.fragments.ChatFragment;
import com.sae.tukangin.fragments.OrderFragment;
import com.sae.tukangin.R;
import com.sae.tukangin.fragments.ProfileFragment;
import com.sae.tukangin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String token = sharedpreferences.getString("token", null);
        String name = sharedpreferences.getString("name", null);
        String id_user1 = sharedpreferences.getString("id", null);
        Integer id_user = Integer.parseInt(id_user1);
        System.out.println("ID USER" + id_user);
        System.out.println(token + " " + name);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().getExtras() != null) {
            String fragment = getIntent().getExtras().getString("fragment", "home");
            if (fragment.equals("order")) {
                replaceFragment(new OrderFragment(id_user));
                binding.bottomNav.setSelectedItemId(R.id.order);
            } else if (fragment.equals("chat")) {
                replaceFragment(new ChatFragment());
                binding.bottomNav.setSelectedItemId(R.id.chat);
            } else if (fragment.equals("profile")) {
                replaceFragment(new ProfileFragment());
                binding.bottomNav.setSelectedItemId(R.id.profile);
            } else {
                replaceFragment(new HomeFragment(name, token));
                binding.bottomNav.setSelectedItemId(R.id.home);
            }
        } else {
            replaceFragment(new HomeFragment(name, token));
            binding.bottomNav.setSelectedItemId(R.id.home);
        }


        binding.bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment(name,token));
            } else if (item.getItemId() == R.id.order) {
                replaceFragment(new OrderFragment(id_user));
            } else if (item.getItemId() == R.id.chat) {
                replaceFragment(new ChatFragment());
            } else if (item.getItemId() == R.id.profile) {
                replaceFragment(new ProfileFragment());
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, fragment);
        fragmentTransaction.commit();
    }

}