package com.matecode.focusgarden.garden;

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
    private GardenViewModel gardenViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentGardenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GardenViewModelFactory factory = new GardenViewModelFactory();    // create factory to define size of gardenMap
        gardenViewModel = new ViewModelProvider(requireActivity(), factory).get(GardenViewModel.class);
        int colCount = gardenViewModel.getCols();

        RecyclerView recyclerView = binding.recyclerView;

        GridLayoutManager layoutManager = new GridLayoutManager(requireContext().getApplicationContext(), colCount);
        recyclerView.setLayoutManager(layoutManager);

        GardenFragmentToGardenTileAdapter adapter = new GardenFragmentToGardenTileAdapter();
        recyclerView.setAdapter(adapter);

        int spacingInDp = 8;
        int spacingInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                spacingInDp,
                getResources().getDisplayMetrics()
        );

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(colCount, spacingInPx, true));

        binding.btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Navigation.findNavController(view).navigate(R.id.action_GardenFragment_to_FirstFragment);
            }
        });

        binding.btnShowStats.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Navigation.findNavController(view).navigate(R.id.action_GardenFragment_to_StatisticsFragment);
            }
        });


        gardenViewModel.getGardenMapForObservers().observe(getViewLifecycleOwner(), gardenMapList -> {
            adapter.setItems(gardenMapList); // refresh that tile in RecyclerView
        });

    }

}
