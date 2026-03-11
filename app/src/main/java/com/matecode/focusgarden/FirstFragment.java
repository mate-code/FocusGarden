package com.matecode.focusgarden;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.matecode.focusgarden.databinding.FragmentFirstBinding;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.pbFocusTimer.setIndeterminate(false);
        binding.pbFocusTimer.setMax(100);
        binding.pbFocusTimer.setProgress(75);

        binding.tvFocusTime.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);    // recenter textView with declared focus time

        AtomicReference<MaterialTimePicker> picker = new AtomicReference<>();

        binding.tvFocusTime.setOnClickListener(v -> {   // define on click listener
             picker.set(new MaterialTimePicker.Builder()    // build time picker object
                     .setTitleText("Select Time")
                     .setTimeFormat(TimeFormat.CLOCK_24H)
                     .setHour(0)
                     .setMinute(24)
                     .build());

            picker.get().show(getParentFragmentManager(), "time_picker");     // show time picker

            picker.get().addOnPositiveButtonClickListener(vv -> {     // apply timer picker changes when user approved (clicked OK on timePicker)
                int hour = picker.get().getHour();
                int minute = picker.get().getMinute();
                binding.tvFocusTime.setText(String.format(Locale.US,"%02d:%02d", hour, minute));
            });
        });

        binding.btnStartFocusTimer.setOnClickListener(v ->{
            int hour = picker.get().getHour();
            int minute = picker.get().getMinute();

            long milis = (hour * 60L + minute) * 60 * 1000;

            new CountDownTimer(milis, 1000) {
                public void onTick(long ms) {
                    long h = ms / 1000 / 60 / 60 % 60;
                    long m = ms / 1000 / 60;
                    binding.tvFocusTime.setText(String.format(Locale.US,"%02d:%02d", h, m));

                    long percentage = 100 - ms * 100 / milis;
                    binding.pbFocusTimer.setProgress((int)percentage, true);
                    int sth = binding.pbFocusTimer.getProgress();


                    //Toast.makeText(getParentFragment().getContext(), String.valueOf(binding.pbFocusTimer.getProgress()), Toast.LENGTH_SHORT).show();
                }

                public void onFinish() {
                    Toast.makeText(getParentFragment().getContext(), "Finished !!!", Toast.LENGTH_SHORT).show();
                }
            }.start();
        });

        //binding.buttonFirst.setOnClickListener(v ->
        //        NavHostFragment.findNavController(FirstFragment.this)
        //                .navigate(R.id.action_FirstFragment_to_SecondFragment)
        //);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}