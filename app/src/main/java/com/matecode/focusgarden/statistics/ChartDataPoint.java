package com.matecode.focusgarden.statistics;

import java.util.Map;

public class ChartDataPoint {
    private String categoryName;
    private int categoryColor;
    private Map<Integer, Integer> data;

    public ChartDataPoint(String categoryName, int categoryColor, Map<Integer, Integer> data) {
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
        this.data = data;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryColor() {
        return categoryColor;
    }

    public Map<Integer, Integer> getData() {
        return data;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryColor(int categoryColor) {
        this.categoryColor = categoryColor;
    }

    public void setData(Map<Integer, Integer> data) {
        this.data = data;
    }
}
