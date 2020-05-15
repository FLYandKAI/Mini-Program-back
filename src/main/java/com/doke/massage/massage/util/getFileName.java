package com.doke.massage.massage.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class getFileName {
    public static String getFileName(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = simpleDateFormat.format(date);
        return fileName;
    }
}
