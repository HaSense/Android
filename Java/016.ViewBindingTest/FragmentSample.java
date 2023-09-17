package com.example.viewbindingtest;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewbindingtest.databinding.FragmentSampleBinding;


public class FragmentSample extends Fragment {

    private FragmentSampleBinding binding;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSampleBinding.inflate(inflater, container, false);
        binding.btnFragment.setOnClickListener(view -> {
            binding.txtFragment.setText("Fragment 버튼이 눌러졌습니다.");
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }


}
