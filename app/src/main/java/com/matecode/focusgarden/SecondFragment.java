package com.matecode.focusgarden;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.matecode.focusgarden.Category.CategoryViewModel;
import com.matecode.focusgarden.databinding.FragmentSecondBinding;

import java.util.Locale;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private TimerViewModel timerViewModel;
    private CategoryViewModel selectedCategory;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        timerViewModel = new ViewModelProvider(requireActivity()).get(TimerViewModel.class);
        selectedCategory = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnCategory.setText(selectedCategory.getSelectedCategory().getValue());

        binding.btnStopFocusTimer.setOnClickListener(new View.OnClickListener() {   // anonymous class override
            @Override
            public void onClick(View view) {
                timerViewModel.stopTimer();
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

            if (timerViewModel.getFinished()){
                timerViewModel.stopTimer();
                Toast.makeText(requireParentFragment().getContext(), "Great, new plant in your garden was born", Toast.LENGTH_LONG).show();
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