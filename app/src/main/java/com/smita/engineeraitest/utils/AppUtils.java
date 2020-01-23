package com.smita.engineeraitest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppUtils {

    public static String convertDateToStr(Date date,String reqDateFormat){
        return new SimpleDateFormat(reqDateFormat, Locale.ENGLISH).format(date);
    }

}
