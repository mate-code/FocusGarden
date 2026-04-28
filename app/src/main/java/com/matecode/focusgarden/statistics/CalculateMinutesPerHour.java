package com.matecode.focusgarden.statistics;

import com.matecode.focusgarden.utils.DataTimeConverter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CalculateMinutesPerHour {

    public static Map<Integer, Integer> calculate(Long sessionStart, Long sessionEnd) {
        LocalDateTime start = DataTimeConverter.toLocalDateTime(sessionStart);
        LocalDateTime end = DataTimeConverter.toLocalDateTime(sessionEnd);
        int sHour = start.getHour();
        int eHour = end.getHour();
        int sMinute = start.getMinute();
        int eMinute = end.getMinute();

        Map<Integer, Integer> dict = new HashMap<>();

        if (sHour == eHour) {
            dict.put(sHour, eMinute - sMinute);
        } else {
            for (int i = sHour; i <= eHour; i++) {
                if (i == sHour) {
                    dict.put(i,60 - sMinute);
                } else if (i == eHour) {
                    dict.put(i, eMinute);
                } else {
                    dict.put(i, 60);
                }
            }
        }

        return dict;

    }
}
