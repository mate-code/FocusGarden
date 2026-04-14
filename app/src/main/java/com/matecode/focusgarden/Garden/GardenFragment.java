package com.matecode.focusgarden.Garden;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.matecode.focusgarden.R;
import com.matecode.focusgarden.databinding.FragmentGardenBinding;

public class GardenFragment extends Fragment {

    private FragmentGardenBinding binding;
    private GardenViewModel gardenMap;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        int colCount = 6;
        int rowCount = 1;

        GardenViewModelFactory factory = new GardenViewModelFactory(rowCount, colCount);    // create factory to define size of gardenMap
        gardenMap = new ViewModelProvider(requireActivity(), factory).get(GardenViewModel.class);

        binding = FragmentGardenBinding.inflate(inflater, container, false);

        RecyclerView recyclerView = binding.recyclerView;

        GridLayoutManager layoutManager = new GridLayoutManager(requireContext().getApplicationContext(), colCount);
        recyclerView.setLayoutManager(layoutManager);

        GardenFragmentToGardenTileAdapter adapter = new GardenFragmentToGardenTileAdapter();
        recyclerView.setAdapter(adapter);

        gardenMap.getGardenMapForObservers().observe(getViewLifecycleOwner(), gardenMapList -> {
            adapter.setItems(gardenMapList); // refresh that tile in RecyclerView
        });

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
