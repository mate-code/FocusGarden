package com.matecode.focusgarden.statistics;

public class ChartStrategyFactory {
    public static ChartStrategy create(ChartType type) {
        switch(type) {
            case BAR: return new BarChartStrategy();
            case PIE: return new PieChartStrategy();
            default: throw new IllegalArgumentException("Chart type not implemented");
        }
    }
}
