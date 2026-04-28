package com.matecode.focusgarden.statistics;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

public class BarChartAxisBuilder {
    private final BarChart barChart;
    private final XAxis xAxis;
    private final YAxis yAxis;


    public BarChartAxisBuilder(BarChart barChart) {
        this.barChart = barChart;
        this.xAxis = barChart.getXAxis();
        this.yAxis = barChart.getAxisLeft();
    }

    public BarChartAxisBuilder setXAxis(float min, float max) {
        xAxis.setAxisMinimum(min);
        xAxis.setAxisMaximum(max);
        return this;
    }

    public BarChartAxisBuilder setYAxis(float min, float max) {
        yAxis.setAxisMinimum(min);
        yAxis.setAxisMaximum(max);
        return this;
    }

    public BarChartAxisBuilder setXGranularity(float val) {
        xAxis.setGranularity(val);
        xAxis.setGranularityEnabled(true);
        return this;
    }

    public BarChartAxisBuilder setXPosition(XAxis.XAxisPosition position) {
        xAxis.setPosition(position);
        return this;
    }

    public BarChartAxisBuilder disableRightAxis() {
        barChart.getAxisRight().setEnabled(false);
        return this;
    }

    public BarChart build() {
        return barChart;
    }

}
