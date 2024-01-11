package cn.edu.scnu.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampFormat {
    private static String pattern = "yyyy-MM-dd";
    private static SimpleDateFormat simpleDateFormat;

    public static String formatString(Timestamp timestamp) {
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(pattern);
        }
        return simpleDateFormat.format(timestamp);
    }
}
