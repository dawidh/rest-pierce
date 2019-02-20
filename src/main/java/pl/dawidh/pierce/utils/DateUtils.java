package pl.dawidh.pierce.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtils {
    public static LocalTime sqlTimeToLocalTime(Time time){
        return time.toLocalTime();
    }

    public static Time localTimeToSqlTime(LocalTime localTime){
        return Time.valueOf(localTime);
    }

    public static LocalDateTime sqlTimestampToLocalDateTime(Timestamp timestamp){
        return timestamp.toLocalDateTime();
    }

    public static Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime){
        return Timestamp.valueOf(localDateTime);
    }
}
