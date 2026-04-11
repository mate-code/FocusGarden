package com.matecode.focusgarden.Category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.matecode.focusgarden.R;
import com.matecode.focusgarden.databinding.FragmentCategoryBinding;

import java.util.Arrays;
import java.util.List;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;
    private CategoryViewModel selectedCategory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        selectedCategory = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);

        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        List<String> data = Arrays.asList("Work", "Study", "Fun");      // It should be load from Phone memory or from Cloud DB

        CategoryFragmentToCategoryItemAdapter adapter = new CategoryFragmentToCategoryItemAdapter(data);
        binding.rvCategory.setLayoutManager(new LinearLayoutManager(requireParentFragment().getContext()));
        binding.rvCategory.setAdapter(adapter);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Navigation.findNavController(view).navigate(R.id.action_CategoryFragment_to_FirstFragment);
            }
        });

        binding.btnSelect.setOnClickListener(v -> {
            int selectedItem = adapter.getSelectedItem();
            if (selectedItem < 0 || selectedItem >= data.size()){
                Toast.makeText(requireParentFragment().getContext(), "Choose focus category first", Toast.LENGTH_SHORT).show();
            } else {
                selectedCategory.getSelectedCategory().setValue(data.get(selectedItem));
                Navigation.findNavController(v).navigate(R.id.action_CategoryFragment_to_FirstFragment);
            }
        });

    }

}
