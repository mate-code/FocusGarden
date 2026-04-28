package com.matecode.focusgarden.statistics;

import com.matecode.focusgarden.db.session.SessionWithCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RawDataToChartDataMapper {
    public static List<ChartDataPoint> map(List<SessionWithCategory> rawData) {

        List<ChartDataPoint> chartDataPoints = new ArrayList<>();

        for (SessionWithCategory rawEntry: rawData) {

            String categoryName = rawEntry.getCategoryName();
            int categoryColor = rawEntry.getCategoryColor();
            long sessionStart = rawEntry.getSessionStart();
            long sessionEnd = rawEntry.getSessionEnd();

            Map<Integer, Integer> sessionDuration = CalculateMinutesPerHour.calculate(sessionStart, sessionEnd);

            ChartDataPoint chartDataPoint = new ChartDataPoint(categoryName, categoryColor, sessionDuration);
            chartDataPoints.add(chartDataPoint);
        }
        return chartDataPoints;
    }
}
