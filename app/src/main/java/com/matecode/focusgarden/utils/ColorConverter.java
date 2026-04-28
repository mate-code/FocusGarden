package com.matecode.focusgarden.utils;

import android.graphics.Color;

import androidx.room.TypeConverter;

public class ColorConverter {

    @TypeConverter
    public static Integer fromColor(int color) {
        return color;
    }

    @TypeConverter
    public static int toColor(Integer value) {
        return value == null ? Color.BLACK : value;
    }
}