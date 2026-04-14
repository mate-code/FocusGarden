package com.matecode.focusgarden;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.util.Log;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.matecode.focusgarden.Category.CategoryViewModel;
import com.matecode.focusgarden.Garden.GardenTileStatusEnum;
import com.matecode.focusgarden.Garden.GardenViewModel;
import com.matecode.focusgarden.Garden.GardenViewModelFactory;
import com.matecode.focusgarden.databinding.FragmentSecondBinding;

import java.util.Locale;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private TimerViewModel timerViewModel;
    private CategoryViewModel selectedCategory;
    private GardenViewModel gardenViewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        timerViewModel = new ViewModelProvider(requireActivity()).get(TimerViewModel.class);
        selectedCategory = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);

        GardenViewModelFactory factory = new GardenViewModelFactory();    // create factory to define size of gardenMap
        gardenViewModel = new ViewModelProvider(requireActivity(), factory).get(GardenViewModel.class);

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Stop shared timer (shared between fragments) when user click on android phone back button
        requireActivity().getOnBackPressedDispatcher().addCallback(
            getViewLifecycleOwner(),
            new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    timerViewModel.stopTimer();
                    requireActivity().getSupportFragmentManager().popBackStack();
                }
            }
        );

        binding.btnCategory.setText(selectedCategory.getSelectedCategory().getValue());

        binding.btnStopFocusTimer.setOnClickListener(new View.OnClickListener() {   // anonymous class override
            @Override
            public void onClick(View view) {
                timerViewModel.stopTimer();

                gardenViewModel.addPlant(GardenTileStatusEnum.DEAD);

                Navigation.findNavController(view).navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        timerViewModel.getTime().observe(getViewLifecycleOwner(), time -> {
            long h = time / (1000 * 60 * 60);
            long m = (time / (1000 * 60)) % 60;
            long s = (time / 1000) % 60;
            binding.tvFocusTime.setText(String.format(Locale.US,"%02d:%02d:%02d", h, m, s));    // temporary

            long percentage = 100 - time * 100 / timerViewModel.getDeclaredTime();
            binding.pbFocusTimer.setProgress((int)percentage, true);

            // If Timer was finished show Toast communicate, add new plant into garden and navigate to Base App View
            if (timerViewModel.getFinished()){
                timerViewModel.stopTimer();
                Toast.makeText(requireParentFragment().getContext(), "Great, new plant in your garden was born", Toast.LENGTH_LONG).show();

                gardenViewModel.addPlant(GardenTileStatusEnum.LIVE);

                Navigation.findNavController(view).navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        timerViewModel.startTimer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}