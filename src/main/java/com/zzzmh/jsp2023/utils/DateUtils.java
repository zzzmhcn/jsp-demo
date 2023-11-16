package com.zzzmh.jsp2023.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 * @author zzzmh
 * @email admin@zzzmh.cn
 * @date 2023-11-15 17:37:00
 */
public class DateUtils {

    /**
     * 格式化LocalDateTime到String
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime){
        return localDateTime == null ? "" : localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
