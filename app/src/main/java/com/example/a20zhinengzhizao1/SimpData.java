package com.example.a20zhinengzhizao1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpData {
    public static String Simp(Date date, String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }
}
