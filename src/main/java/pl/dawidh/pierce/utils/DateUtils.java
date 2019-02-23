package pl.dawidh.pierce.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateUtils {
    public static LocalDateTime sqlTimestampToLocalDateTime(Timestamp timestamp){
        return timestamp.toLocalDateTime();
    }
}
