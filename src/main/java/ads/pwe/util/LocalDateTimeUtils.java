package ads.pwe.util;

import java.time.LocalDateTime;

public class LocalDateTimeUtils {
    
    public static boolean isTimestampEntrePeriodos(
        LocalDateTime timestamp,
        LocalDateTime inicioPeriodo,
        LocalDateTime fimPeriodo
    ) {
        return inicioPeriodo.isEqual(timestamp) || fimPeriodo.isEqual(timestamp) || (inicioPeriodo.isBefore(timestamp) && fimPeriodo.isAfter(timestamp));
    }

}
