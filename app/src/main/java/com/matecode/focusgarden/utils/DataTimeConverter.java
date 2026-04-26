package com.matecode.focusgarden.utils;

import androidx.room.TypeConverter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.Instant;

public class DataTimeConverter {

    @TypeConverter
    public static Long fromLocalDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    @TypeConverter
    public static LocalDateTime toLocalDateTime(Long millis) {
        if (millis == null) return null;
        return Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDateTime();
    }
}