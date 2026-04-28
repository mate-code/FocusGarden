package com.matecode.focusgarden.statistics;

import android.view.View;

import com.github.mikephil.charting.charts.BarChart;

import java.util.List;

public interface ChartStrategy {

    void render(BarChart barChart, List<ChartDataPoint> data);
}
