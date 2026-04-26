package com.matecode.focusgarden.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for CalculateMinutesPerHour class
 */
public class CalculateMinutesPerHourTest {
    @Test
    public void lessThanOneHourSessionTest() {
        Long sessionStart = 1777184403000L; // Sunday, April 26, 2026 at 6:20:03 AM
        Long sessionEnd = 1777186259000L; // Sunday, April 26, 2026 at 6:50:59 AM
        Map<Integer, Integer> dictTest = new HashMap<>(Map.of(
                6, 30
        ));
        Map<Integer, Integer> dictResult = CalculateMinutesPerHour.calculate(sessionStart, sessionEnd);
        assertEquals(dictTest, dictResult);
    }

    @Test
    public void moreThanOneHourSessionTest() {
        Long sessionStart = 1777194630000L; //  Sunday, April 26, 2026 at 9:10:30 AM
        Long sessionEnd = 1777198810000L; // Sunday, April 26, 2026 at 10:20:10 AM
        Map<Integer, Integer> dictTest = new HashMap<>(Map.of(
                9, 50,
                10, 20
        ));
        Map<Integer, Integer> dictResult = CalculateMinutesPerHour.calculate(sessionStart, sessionEnd);
        assertEquals(dictTest, dictResult);
    }

    @Test
    public void moreThanTwoHourSessionTest() {
        Long sessionStart = 1777198810000L; //  Sunday, April 26, 2026 at 9:10:30 AM
        Long sessionEnd = 1777207250000L; // Sunday, April 26, 2026 at 12:40:50 PM
        Map<Integer, Integer> dictTest = new HashMap<>(Map.of(
                10, 40,
                11, 60,
                12, 40
        ));
        Map<Integer, Integer> dictResult = CalculateMinutesPerHour.calculate(sessionStart, sessionEnd);
        assertEquals(dictTest, dictResult);
    }
}