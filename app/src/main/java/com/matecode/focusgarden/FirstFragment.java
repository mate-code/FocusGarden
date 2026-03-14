package com.matecode.focusgarden;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.matecode.focusgarden.databinding.FragmentFirstBinding;

import java.util.Locale;
import java.util.logging.Logger;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    private int tvGetTime(TextView tv, int index) {
        String[] data = tv.getText().toString().split(":");
        if (index > data.length - 1) return -1;
        try {
            int num = Integer.parseInt(data[index]);
            if (num < 0) return -1;
            return num;
        } catch (NumberFormatException e){
            logger.warning("String is not a number: %s");
            return -1;
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.pbFocusTimer.setIndeterminate(false);
        binding.pbFocusTimer.setMax(100);
        binding.pbFocusTimer.setProgress(75);
        binding.pbFocusTimer.setSecondaryProgress(100);     // set background ring to be fully filled

        binding.tvFocusTime.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);    // recenter textView with declared focus time


        binding.tvFocusTime.setOnClickListener(v -> {   // define on click listener
            int hour = tvGetTime(binding.tvFocusTime, 0);
            int minute = tvGetTime(binding.tvFocusTime, 1);

            MaterialTimePicker picker = new MaterialTimePicker.Builder()    // build time picker object
                    .setTitleText("Select Time")
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(hour)
                    .setMinute(minute)
                    .build();

            picker.show(getParentFragmentManager(), "time_picker");     // show time picker

            picker.addOnPositiveButtonClickListener(vv -> {     // apply timer picker changes when user approved (clicked OK on timePicker)
                binding.tvFocusTime.setText(String.format(
                        Locale.US,
                        "%02d:%02d",
                        picker.getHour(),
                        picker.getMinute()
                ));
            });

        });



        binding.btnStartFocusTimer.setOnClickListener(v -> {

            Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_SecondFragment);

/*

            int hour = tvGetTime(binding.tvFocusTime, 0);
            int minute = tvGetTime(binding.tvFocusTime, 1);

            long milis = (hour * 60L + minute) * 60 * 1000;     // convert time from time picker into miliseconds

            new CountDownTimer(milis, 1000) {
                public void onTick(long ms) {
                    long h = ms / (1000 * 60 * 60);
                    long m = (ms / (1000 * 60)) % 60;
                    long s = (ms / 1000) % 60;
                    binding.tvFocusTime.setText(String.format(Locale.US,"%02d:%02d:%02d", h, m, s));    // temporary

                    long percentage = 100 - ms * 100 / milis;
                    binding.pbFocusTimer.setProgress((int)percentage, true);
                }

                public void onFinish() {
                    Toast.makeText(requireParentFragment().getContext(), "Finished !!!", Toast.LENGTH_SHORT).show();
                }
            }.start();

 */
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}