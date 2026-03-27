package com.matecode.focusgarden.Garden;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.matecode.focusgarden.R;
import com.matecode.focusgarden.databinding.FragmentGardenBinding;

import java.util.ArrayList;
import java.util.List;

public class GardenFragment extends Fragment {

    private FragmentGardenBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentGardenBinding.inflate(inflater, container, false);

        RecyclerView recyclerView = binding.recyclerView;
        int colCount = 6;

        GridLayoutManager layoutManager = new GridLayoutManager(requireContext().getApplicationContext(), colCount);
        recyclerView.setLayoutManager(layoutManager);

        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 120; i++) {
            data.add("Tile " + i);
        }

        GardenFragmentToGardenTileAdapter adapter = new GardenFragmentToGardenTileAdapter(data);
        recyclerView.setAdapter(adapter);

        int spacingInDp = 8;
        int spacingInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                spacingInDp,
                getResources().getDisplayMetrics()
        );

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(colCount, spacingInPx, true));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        binding.btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Navigation.findNavController(view).navigate(R.id.action_GardenFragment_to_FirstFragment);
            }
        });
    }

}
