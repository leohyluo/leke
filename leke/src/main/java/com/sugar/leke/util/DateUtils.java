package com.sugar.leke.util;

import java.time.LocalDate;

public class DateUtils {

    public static String today() {
        LocalDate localDate = LocalDate.now();
        String result =  localDate.toString();
        return result;
    }

    public static void main(String[] args) {
        today();
    }
}
