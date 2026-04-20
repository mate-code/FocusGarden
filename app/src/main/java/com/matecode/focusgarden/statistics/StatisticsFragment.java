package com.matecode.focusgarden.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.matecode.focusgarden.R;
import com.matecode.focusgarden.databinding.FragmentStatisticsBinding;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {
    private FragmentStatisticsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BarChart barChart = binding.barChart;

        // Sample data
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, 10));
        entries.add(new BarEntry(2, 20));
        entries.add(new BarEntry(3, 15));
        entries.add(new BarEntry(4, 30));

        // Dataset
        BarDataSet dataSet = new BarDataSet(entries, "My Data");

        // Final data
        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        // Refresh chart
        barChart.invalidate();


        binding.btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_StatisticsFragment_to_GardenFragment);
            }
        });
    }
}
