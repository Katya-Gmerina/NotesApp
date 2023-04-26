package com.example.notesapp;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.notesapp.databinding.ActivityMainBinding;
import com.example.notesapp.fragment.MainFragment;

public class MainActivity extends FragmentActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_frag, new MainFragment()).commit();
    }
}