package com.matecode.focusgarden.statistics;

import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BarChartStrategy implements ChartStrategy{

    @Override
    public void render(BarChart barChart, List<ChartDataPoint> data) {

        Map<String, BarDataSet> map = new HashMap<>();

        for (ChartDataPoint cdp : data) {

            List<BarEntry> entries = cdp.getData()
                    .entrySet()
                    .stream()
                    .map(e -> new BarEntry(e.getKey(), e.getValue()))
                    .collect(Collectors.toList());

            if (!map.containsKey(cdp.getCategoryName())){
                BarDataSet barDataSet = new BarDataSet(entries, cdp.getCategoryName());
                barDataSet.setColor(cdp.getCategoryColor());
                map.put(cdp.getCategoryName(), barDataSet);
            } else {
                for (BarEntry entry: entries) {
                    map.get(cdp.getCategoryName()).addEntry(entry);
                }
            }
        }

        barChart.setData(new BarData(map.values().toArray(new IBarDataSet[0])));

        new BarChartAxisBuilder(barChart)
                .setXAxis(0, 24)
                .setYAxis(0, 60)
                .setXGranularity(1f)
                .setXPosition(XAxis.XAxisPosition.BOTTOM)
                .disableRightAxis()
                .build();

        barChart.invalidate();

    }
}
