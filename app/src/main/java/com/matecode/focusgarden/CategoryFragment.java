package com.matecode.focusgarden;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.matecode.focusgarden.databinding.FragmentCategoryBinding;

import java.util.Arrays;
import java.util.List;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        List<String> data = Arrays.asList("Card 1", "Card 2", "Card 3");

        CategoryFragmentToCategoryItemAdapter adapter = new CategoryFragmentToCategoryItemAdapter(data);
        binding.rvCategory.setLayoutManager(new LinearLayoutManager(requireParentFragment().getContext()));
        binding.rvCategory.setAdapter(adapter);
    }

}
